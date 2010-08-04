package com.bsoft.szdc.send.clinic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.bsoft.szdc.send.control.ConditionEntity;
import com.bsoft.szdc.send.db.DBProccess;
import com.bsoft.szdc.send.db.JDBCConn;
import com.bsoft.szdc.send.entity.TableEntity;
import com.bsoft.szdc.send.impl.XMLEltBuilderImpl;
import com.bsoft.szdc.send.interf.XMLEltBuilder;
import com.bsoft.szdc.send.util.Constant;
import com.bsoft.szdc.send.util.QueryHospitalConfig;
import com.bsoft.szdc.send.util.UtilTools;

public class BRMZJYUpload implements Job
{

    private final String CONFIG_PATH = Constant.SQLSCRIPT_MZJY;

    private String hospitalCode = QueryHospitalConfig.ReadHospitalCode();

    private String hospitalName = QueryHospitalConfig.ReadHospitalName();

    private Logger mLog = null;

    protected HashMap<String, TableEntity> mTableHM = null;

    private HashMap<String, String> mColType = null;

    private Connection mConn = null;

    private Statement mStmt = null;

    private ResultSet mRs = null;

    private ConditionEntity mCe = null;

    protected Element mMessage = null;

    protected List<?> mTableEles = null;

    private String mLastFlag = "";

    private String mKey = "";

    private String mPTabName = "";

    private Document mDoc = null;

    private Connection mCConn = null;

    private String mLogicidname = "LOGICID";

    private String mAssayrecordguid = "ASSAYRECORDGUID";

    private String mGUID = "?";

    private String mLogicidval = "";

    private List<String> mExceptionFilter = null;

    private String mHZ = "";

    private HashMap<String, String> mDifTarg;

    private boolean isDifTarg = false;

    private String mDifColName = "";

    private DBProccess mDBP = null;

    private long mSleepTime = 0;

    private UtilTools mUT;

    private int count = 0;

    private int nologicid = 0;


    public BRMZJYUpload()
    {
        mLog = LogManager.getLogger(this.getClass());
        mCe = ConditionEntity.getInstance("MZJYFE");
        mDifTarg = new HashMap<String, String>();
        mDBP = new DBProccess();
        mUT = new UtilTools();
    }


    protected boolean init()
    {
        boolean flag = false;
        try
        {
            mDoc = new SAXReader().read(Constant.DBASE_CONFIG_MZJY);
            Element ele = mDoc.getRootElement();
            mHZ = ele.attributeValue("HZ");
            mSleepTime = Long.parseLong(ele.attributeValue("sleepTime", "500"));
            mTableHM = new HashMap<String, TableEntity>();
            mColType = new HashMap<String, String>();
            Iterator<?> iter = ele.elementIterator();

            while (iter.hasNext())
            {
                Element tab = (Element) iter.next();
                TableEntity te = new TableEntity();
                te.setTableName(tab.attributeValue("name") + mHZ);
                te.setPK(tab.attributeValue("pk"));
                te.setFK(tab.attributeValue("fk"));
                te.setCondition(tab.attributeValue("condition"));
                te.setErrorTableName(tab.attributeValue("errorname"));
                te.setTOTabmeName(tab.attributeValue("timeout"));
                te.setRelated(tab.attributeValue("related"));
                te.setValue(tab.attributeValue("value"));
                String[] filter = tab.attributeValue("filter").split("\\,");
                List<String> fl = new ArrayList<String>();
                for (int i = 0; i < filter.length; i++)
                {
                    fl.add(filter[i]);
                }
                te.setFilterList(fl);

                if (tab.attribute("query") != null)
                {
                    String[] query = tab.attributeValue("query").split("\\,");
                    List<String> qu = new ArrayList<String>();
                    for (int i = 0; i < query.length; i++)
                    {
                        qu.add(query[i]);
                    }
                    te.setQueryCol(qu);
                }

                List<String> colsname = new ArrayList<String>();
                HashMap<String, String> coltype = new HashMap<String, String>();
                List<?> collist = tab.elements("col");
                for (int i = 0; i < collist.size(); i++)
                {
                    Element col = (Element) collist.get(i);
                    String colname = col.attributeValue("name");
                    String valtype = col.attributeValue("type");
                    colsname.add(colname);
                    coltype.put(colname, valtype);
                    mColType.put(colname, valtype);
                }

                te.setColName(colsname);
                te.setColType(coltype);

                mTableHM.put(te.getTableName(), te);
            }

            mExceptionFilter = new ArrayList<String>();

            Document exceptionDoc = new SAXReader()
                .read("config/exception filter.xml");
            Element root = exceptionDoc.getRootElement();
            List<?> exceptionList = root.elements("unit");
            for (int i = 0; i < exceptionList.size(); i++)
            {
                Element tempele = (Element) exceptionList.get(i);
                mExceptionFilter.add(tempele.element("msg").getText());
            }
            mConn = JDBCConn.getConnection();
            mCConn = JDBCConn.getBakConnection();
            if (mConn != null && mCConn != null)
            {
                flag = true;
            }
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
            mLog.error(e.getMessage());
            flag = false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            mLog.error(e.getMessage());
            flag = false;
        }
        return flag;
    }


    // 释放资源
    private void finalized()
    {

        try
        {
            Thread.currentThread();
            Thread.sleep(mSleepTime);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        try
        {
            mCConn.commit();
            if (mRs != null)
                mRs.close();
            if (mStmt != null)
                mStmt.close();
            if (mConn != null)
                mConn.close();
            if (mCConn != null)
                mCConn.close();
        }
        catch (SQLException e)
        {
            mLog.error("释放数据库资源出现异常：" + e.getMessage());
        }
        mRs = null;
        mStmt = null;
        mConn = null;
        mCConn = null;
        if (mTableHM != null)
            mTableHM.clear();
        mTableHM = null;
    }


    // 查询数据
    private boolean queryData()
    {
        boolean flag = false;
        try
        {
            String script = mUT.getQueryScript(CONFIG_PATH);
            mStmt = mConn.createStatement();
            mRs = mStmt.executeQuery(script);
            flag = true;
        }
        catch (SQLException e)
        {
            mLog.error("查询数据时发生异常：" + e.getMessage());
            flag = false;
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
            mLog.error("读取SQL脚本时发生异常：" + ioe.getMessage());
            flag = false;
        }
        // System.out.println(flag);
        return flag;
    }


    // 填充节点
    private boolean fillData()
    {
        boolean flag = false;
        Element elt = null;
        try
        {
            for (int i = 0; i < mTableEles.size(); i++)
            {

                elt = (Element) mTableEles.get(i);
                elt = fill(elt);
            }
            flag = true;
        }
        catch (NumberFormatException e)
        {
            mLog.error("数据格式转换出现异常." + e.getMessage() + "=:" + mLastFlag);
            flag = false;
        }
        catch (SQLException e)
        {
            mLog.error("从数据集中取数据时出现异常:" + e.getMessage() + "=:" + mLastFlag);
            flag = false;
        }
        catch (Exception e)
        {
            mLog.error("读取数据出现异常" + e.getMessage() + "=:" + mLastFlag);
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }


    private Element fill(Element ele) throws SQLException,
        NumberFormatException
    {
        Element tmp = ele;
        List<?> attributes = tmp.attributes();
        for (int i = 0; i < attributes.size(); i++)
        {
            String colname = ((Attribute) attributes.get(i)).getName();

            String type = (String) mColType.get(colname);
            String tpKey = mKey;

            if (tpKey.indexOf(".") != -1)
            {
                tpKey = tpKey.substring(tpKey.indexOf(".") + 1, tpKey.length());
            }

            mLastFlag = mRs.getString(tpKey);
            if (mLastFlag == null || mLastFlag.trim().length() == 0)
            {
                throw new NumberFormatException("该条数据内容异常:索引时间为空,进行忽略此行数据.");
            }

            String tmpValue = mRs.getString(colname);
            if ("门诊".equals(tmpValue) || "特诊".equals(tmpValue))
            {
                tmpValue = "0";
            }
            if ("住院".equals(tmpValue))
            {
                tmpValue = "1";
            }
            try
            {
                tmpValue = mUT.formatValue(type, tmpValue);
            }
            catch (NumberFormatException e)
            {
                mLog.error("数据格式化时出现异常. column:" + colname + ";value:"
                        + tmpValue);
                throw e;
            }

            tmp.addAttribute(colname, tmpValue);

        }
        // System.out.println("xml:" + tmp.asXML());
        return tmp;
    }


    /**
     * @author RogEr.Wee 本方法用于初始化重要列表对象
     */
    private boolean initDiftargt()
    {
        boolean flag = false;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            stmt = mCConn.createStatement();
            rs = stmt
                .executeQuery("SELECT DIFTARGETCODE,DIFTARGETNAME FROM DC_DIFTARGETLIST");
            while (rs.next())
            {
                mDifTarg.put(rs.getString("DIFTARGETNAME"), rs
                    .getString("DIFTARGETCODE"));
            }
            flag = true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            flag = false;
        }
        finally
        {
            try
            {
                rs.close();
                stmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            rs = null;
            stmt = null;
        }
        return flag;
    }


    /**
     * @author RogEr.Wee 本方法用于遍历当前记录及游标位置的每一列，并将重要指标列的值插入到重要指标表中
     */
    private boolean insertDifTarg()
    {
        boolean flag = false;
        String diftarg_insert = "insert into dc_diftargt"
                + "(LOGICID,DIFTARGTCODE,DIFTARGTNAME,DIFTARGTVALUE,"
                + "DIFTARGTDATETIME,ISNEWDATE) values('" + mLogicidval + "',";
        try
        {
            String colcode = (String) mDifTarg.get(mDifColName);
            diftarg_insert = diftarg_insert + colcode + ",'"
                    + mRs.getString("OBSERVATIONSUB_NAME") + "','"
                    + mRs.getString("OBSERVATIONVALUE") + "','"
                    + mRs.getString("OBSERVATIONDATETIME") + "',1)";
            flag = DBProccess.executeUpdate(mCConn, diftarg_insert, true);
            // System.out.println(diftarg_insert);
        }
        catch (SQLException e)
        {
            flag = false;
            e.printStackTrace();
            for (int ei = 0; ei < e.getStackTrace().length - 1; ei++)
                mLog.error((e.getStackTrace()[ei]).toString());
        }
        return flag;
    }


    /**
     * @author RogEr.Wee 该方法用于在整个数据入库流程执行完成以后 对重要指标表进行更新 指定的条件是 LOGICID 指标编号 的时间
     */
    private void refreshDifTarg()
    {
        String update = "update dc_diftargt set isnewdate=0 where logicid='"
                + mLogicidval + "' and DIFTARGTCODE="
                + (String) mDifTarg.get(mDifColName);
        DBProccess.executeUpdate(mCConn, update, true);
    }


    private void execute()
    {
        int insert = 0;
        int error = 0;
        mMessage = producted();
        if (!initDiftargt())
            return;
        if (queryData())
        {
            if (mRs == null)
            {
                mLog.debug("数据集是空的.");
            }
            else
            {
                try
                {
                    while (mRs.next())
                    {
                        if (fillData())
                        {
                            if (insertdata())
                            {
                                insert++;
                            }
                            else
                            {
                                error++;
                            }
                        }
                        else
                        {
                            error++;
                            mLog.error("封装数据出现异常，继续下一条数据封装.");
                            continue;
                        }
                        mGUID = "?";
                        if (isDifTarg
                                && (mLogicidval != null && mLogicidval.trim()
                                    .length() != 0))
                        {
                            refreshDifTarg();
                            if (!insertDifTarg())
                                mLog.error("重要指标入库失败." + mMessage.asXML());
                        }
                        isDifTarg = false;
                        refreshConfig();

                        try
                        {
                            Thread.currentThread();
                            Thread.sleep(10);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                catch (SQLException sql)
                {
                    mLog.error(sql.getMessage());
                }
                catch (UnknownHostException e)
                {
                    e.printStackTrace();
                }
            }
        }
        String msg = "<mhwsj><hosp ip='"
                + UtilTools.getHostIP()
                + "'>["
                + DateFormat.getDateTimeInstance(2, 2, Locale.CHINA).format(
                    new Date()) + "]附二院检验结果数据采集模块数据采集完成，数据截止时间：" + mLastFlag
                + "success:" + count + ",error:" + (error - nologicid)
                + "</hosp></mhwsj>";
        mLog.info(msg);
    }


    // 更新文件config/sqlscript/cliniccf.prop中的kval值，根据数据的clinicdatetime
    private boolean refreshConfig()
    {
        boolean flag = false;
        InputStream is = null;
        OutputStream os = null;
        Properties prop = null;
        try
        {
            prop = new Properties();
            is = new FileInputStream(new File(CONFIG_PATH));
            prop.load(is);
            is.close();
            prop.setProperty("script", prop.getProperty("script"));
            prop.setProperty("arge", prop.getProperty("arge"));
            prop.setProperty("value", prop.getProperty("value"));
            prop.setProperty("key", prop.getProperty("key"));
            mLastFlag = mLastFlag.substring(0, mLastFlag.indexOf("."));
            prop.setProperty("kval", mLastFlag);
            os = new FileOutputStream(new File(CONFIG_PATH));
            prop.store(os, null);
            os.close();
            flag = true;
        }
        catch (IOException ioe)
        {
            mLog.error("更新脚本文件时出现异常：" + ioe.getMessage());
            flag = false;
        }
        finally
        {
            try
            {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
            }
            catch (IOException e)
            {
            }
            is = null;
            os = null;
            prop = null;
        }
        return flag;
    }


    public void execute(JobExecutionContext arg0) throws JobExecutionException
    {
        synchronized (mCe)
        {
            if (!init())
            {
                mLog.error("程序初始化失败.");
                return;
            }
            execute();
        }
        finalized();
    }


    private Element producted()
    {
        XMLEltBuilder xmleb = new XMLEltBuilderImpl(mTableHM);
        Element elt = xmleb.toXMLEltBuild();
        mPTabName = xmleb.getParentTableName();
        mTableEles = xmleb.getElts();
        mKey = xmleb.getKeyName();
        return elt;
    }


    private String queryAssayrecordguid()
    {
        String assayrecordguid = "";

        String sql = "select " + mAssayrecordguid + " from DC_ASSAYRECORD"
                + mHZ + " where LOGICID='" + mLogicidval
                + "' and OBSERVATIONID='"
                + mMessage.attributeValue("OBSERVATIONID") + "'";
        // System.out.println(sql);
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            stmt = mCConn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null && rs.next())
            {
                assayrecordguid = rs.getString(mAssayrecordguid);
                // System.out.println(assayrecordguid);
            }
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle.getMessage());
        }
        finally
        {
            try
            {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            rs = null;
            stmt = null;
        }
        return assayrecordguid;
    }


    // *******************************************************************

    // 根据节点数进行多张表的数据入库
    private boolean insertdata() throws SQLException, UnknownHostException
    {
        boolean isExsist;

        if ("0".equals(mMessage.attributeValue("TYPE")))
        {
            mLogicidval = queryLogicID("dc_clinic", mMessage);
        }
        if ("1".equals(mMessage.attributeValue("TYPE")))
        {
            mLogicidval = queryLogicID("DC_ADMISSION", mMessage);
        }
        if (mLogicidval == null || mLogicidval.trim().length() == 0)
        {
            mLogicidval = "not found";
            nologicid++;
        }
        else
        {
            count++;
        }
        // System.out.println("logicid = "+mLogicidval);
        mGUID = queryAssayrecordguid();
        if (mGUID.trim().length() == 0)
        {
            mGUID = mMessage.attributeValue("OBSERVATIONID");
            isExsist = false;
        }
        else
        {
            isExsist = true;
        }

        List<String> sqlList = new ArrayList<String>();

        for (int i = mTableEles.size() - 1; i >= 0; i--)
        {
            Element tmpTab = (Element) mTableEles.get(i);
            String sql = insert(tmpTab, isExsist);
            if (sql.trim().length() == 0)
            {
                if (tmpTab.getName().equals(mPTabName))
                    continue;
                else
                    return false;
            }
            if (sql.indexOf("?") != -1)
            {
                sql = sql.replaceAll("\\?", mGUID);
            }
            sqlList.add(sql);
        }
        if (sqlList.size() > 0)
        {
            if (mDBP.executeUpdate(mCConn, sqlList, false, mExceptionFilter))
            {
                // System.out.println("executeUpdate  ");
                mCConn.commit();
                return true;
            }
            else
            {
                mCConn.rollback();
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    // 根据门诊信息中的卡号查找虚拟卡号 logicid
    private String queryLogicID(String s, Element message)
    {
        String logicid = "";
        boolean flag = false;

        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            stmt = mCConn.createStatement();
            String sql = "";
            if ("dc_clinic".equals(s))
            {
                sql = "select logicid as logicid from dc_clinic where JZBS='"
                        + mMessage.attributeValue("CLINICID") + "'";
            }
            if ("DC_ADMISSION".equals(s))
            {
                sql = "select logicid as logicid from DC_ADMISSION where MRID='"
                        + mMessage.attributeValue("CLINICID") + "'";
            }
            // System.out.println("logicid_sql:" + sql);
            rs = stmt.executeQuery(sql);

            if (rs.next())
            {
                logicid = rs.getString("logicid");
            }
            else
            {
                if ("DC_ADMISSION".equals(s))
                {
                    flag = true;
                }
            }
            if (flag)
            {
                sql = "select logicid as logicid from DC_MEDICARECORDS where MRID='"
                        + mMessage.attributeValue("CLINICID") + "'";
                rs = stmt.executeQuery(sql);
                if (rs.next())
                {
                    logicid = rs.getString("logicid");
                }
            }
            // System.out.println(" queryLogicID logicid_sql:" + sql);
        }
        catch (SQLException e)
        {
            mLog.error(e);
        }
        try
        {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        rs = null;
        stmt = null;

        return logicid;
    }


    // 数据入库
    // 先到dc_clinic表中查出clinicguid 然后再入到dc_clinic部分的数据就不入库
    // 如果没有clinicguid则生成一个将dc_clinic部分的数据入库
    private String insert(Element ele, boolean flag)
        throws UnknownHostException
    {
        String tabName = ele.getName();

        String sql = "insert into " + tabName + "(";
        String val = " values(";

        if (("DC_ASSAYRECORD" + mHZ).equals(tabName) && flag)
            return "";

        if (((TableEntity) mTableHM.get(tabName)).getColName().contains(
            mAssayrecordguid))
        {
            sql = sql + mAssayrecordguid + ",";
            val = val + "'" + mGUID + "',";
        }

        if (((TableEntity) mTableHM.get(tabName)).getColName().contains(
            mLogicidname))
        {
            sql = sql + mLogicidname + ",";
            val = val + "'" + mLogicidval + "',";
        }
        // System.out.println("insert logicid = "+ mLogicidval);

        if (((TableEntity) mTableHM.get(tabName)).getColName().contains(
            "HOSPITALCODE"))
        {
            sql = sql + "HOSPITALCODE" + ",";
            val = val + "'" + hospitalCode + "',";
            // System.out.println("HOSPITALCODE");
        }
        if (((TableEntity) mTableHM.get(tabName)).getColName().contains(
            "HOSPITALNAME"))
        {
            sql = sql + "HOSPITALNAME" + ",";
            val = val + "'" + hospitalName + "',";
            // System.out.println("HOSPITALNAME");
        }

        List<?> list = ele.attributes();
        // System.out.println(list.size());
        for (int i = 0; i < list.size(); i++)
        {
            Attribute att = (Attribute) list.get(i);
            String colname = att.getName();

            String type = (String) mColType.get(colname);
            String colval = att.getText();

            colval = colval.replaceAll("'", "\"");
            if ("String".equals(type))
            {
                val = val + "'" + colval + "',";
            }
            else if ("BigDecimal".equals(type))
            {
                if ("门诊".equals(colval) || "特诊".equals(colval))
                {
                    colval = "0";
                }
                if ("住院".equals(colval))
                {
                    colval = "1";
                }
                val = val + colval + ",";
            }
            else if ("Timestamp".equals(type))
            {
                val = val + "to_timestamp('" + colval
                        + "','yyyy-mm-dd hh24:mi:ssxff'),";
            }
            else
            {
                mLog.error("数据结构描述文件中有不支持的类型描述.type:" + type);
                continue;
            }

            sql = sql + colname + ",";
        }
        sql = sql.substring(0, sql.length() - 1) + ")"
                + val.substring(0, val.length() - 1) + ")";
        // System.out.println(sql);
        if ("not found".equals(mLogicidval))
        {
            sql = "";
        }
        return sql;
    }


    public static void main(String[] args) throws JobExecutionException,
        DocumentException
    {
        JDBCConn.init();
        BRMZJYUpload cfupload = new BRMZJYUpload();
        cfupload.execute(null);
    }
}

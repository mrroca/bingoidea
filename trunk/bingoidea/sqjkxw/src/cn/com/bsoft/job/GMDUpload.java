package cn.com.bsoft.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import cn.com.bsoft.contrl.ConditionEntity;
import cn.com.bsoft.db.DBProccess;
import cn.com.bsoft.db.JDBCConn;
import cn.com.bsoft.entity.TableEntity;
import cn.com.bsoft.util.Constant;
import cn.com.bsoft.util.QueryIdentityIdHelper;
import cn.com.bsoft.util.SystemInit;
import cn.com.bsoft.util.UtilTools;
import cn.com.bsoft.xmlbuild.XMLEltBuilder;
import cn.com.bsoft.xmlbuild.impl.XMLEltBuilderImpl;

public class GMDUpload implements Job
{

    private final String CONFIG_PATH = Constant.SQLSCRIPT_GMD;

    private Logger mLog = null;

    // 用于存放tableentity的容器
    protected HashMap<String, TableEntity> mTableHM = null;

    // 存放总的列项的类型
    private HashMap<String, String> mColType = null;

    private long mSleepTime = 0;

    private Connection mConn = null;

    private Statement mStmt = null;

    private ResultSet mRs = null;

    private ConditionEntity mCe = null;

    // DC_CLINIC 部分的节点 方便右面的流程进行查询CLINICGUID 和 LOGICID
    protected Element mMessage = null;

    protected List<?> mTableEles = null;

    private String mLastFlag = "";

    private String mKey = "";

    private String mPTabName = "";

    private Document mDoc = null;

    private Connection mCConn = null;

    private String mLogicidname = "LOGICID";

    private String mLogicidval = "?";

    private List<String> mExceptionFilter = null;

    private String mHZ = "";

    private QueryIdentityIdHelper mQIIH = null;

    private UtilTools mUT;


    public GMDUpload()
    {
        mTableEles = new ArrayList<Object>();
        mLog = LogManager.getLogger(this.getClass());
        mQIIH = new QueryIdentityIdHelper();
        // 得到一个条件对象 用于控制同一线程启动的个数
        mCe = ConditionEntity.getInstance("GMD");
    }


    protected boolean init()
    {
        // System.out.println("----------------------");
        boolean flag = false;
        mUT = new UtilTools();
        try
        {
            mDoc = new SAXReader().read(Constant.DBASE_CONFIG_GMD);
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
                // System.out.println("filter==" + filter);

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
                .read("config/exceptionfilter.xml");
            Element root = exceptionDoc.getRootElement();
            List<?> exceptionList = root.element("unit").elements();
            for (int i = 0; i < exceptionList.size(); i++)
            {
                Element tempele = (Element) exceptionList.get(i);
                mExceptionFilter.add(tempele.getText());
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
            mCConn.commit();
            if (mCConn != null)
                mCConn.close();
        }
        catch (SQLException e)
        {
            mLog.error("释放数据库资源出现异常." + e.getMessage());
        }

        mRs = null;
        mStmt = null;
        mConn = null;
        mCConn = null;

        if (mTableHM != null)
            mTableHM.clear();
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
            mLog.error("查询数据时发生异常." + e.getMessage());
            flag = false;
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
            mLog.error("读取SQL脚本时发生异常." + ioe.getMessage());
            flag = false;
        }
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
                // System.out.println(elt.asXML());
                elt = fill(elt);
            }
            flag = true;
        }
        catch (NumberFormatException e)
        {
            mLog.error("数据格式转换出现异常." + e.getMessage() + "=:" + elt.asXML());
            flag = false;
        }
        catch (SQLException e)
        {
            mLog.error("从数据集中取数据时出现异常." + e.getMessage() + "=:" + elt.asXML());
            flag = false;
        }
        catch (Exception e)
        {
            mLog.error("读取数据出现异常" + e.getMessage());
            flag = false;
        }
        return flag;
    }


    /**
     * 
     * @param ele
     * @return
     * @throws SQLException
     * @throws NumberFormatException
     */
    private Element fill(Element ele) throws SQLException,
        NumberFormatException
    {
        Element tmp = ele;
        // System.out.println(ele.asXML());
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

        return tmp;
    }


    private void execute()
    {
        long start = System.currentTimeMillis();
        int insert = 0;
        int error = 0;
        mMessage = producted();
        // System.out.println("xml:"+mMessage.asXML());
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
                    int lz = 0;
                    while (mRs.next())
                    {
                        String sPid = mRs.getString("PID");
                        String sType = mRs.getString("PIDTYPE");
                        if (sType.equals("1") && sPid.trim().length() != 18
                                && sPid.trim().length() != 15)
                            continue;
                        if (fillData())
                        {
                            if (insertdata(lz, mMessage))
                            {
                                lz++;
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
                        if (lz == 200)
                        {
                            lz = 0;
                        }
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
            }
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        String msg = "<szwsj><hosip ip='"
                + UtilTools.getHostIP()
                + "'>["
                + DateFormat.getDateTimeInstance(2, 2, Locale.CHINA).format(
                    new Date()) + "] 社区自助小屋骨密度数据采集完成:" + "success:" + insert
                + ",error:" + error + " 耗时：" + duration + " ms</hosip></szwsj>";
        mLog.info(msg);
    }


    // 更新文件config/sqlscript/cliniccf.prop中的kval值，根据最后一条数据的clinicdatetime
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
            prop.setProperty("kval", mLastFlag);
            os = new FileOutputStream(new File(CONFIG_PATH));
            prop.store(os, null);
            os.close();
            flag = true;
        }
        catch (IOException ioe)
        {
            mLog.error("更新脚本文件时出现异常." + ioe.getMessage());
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
        mKey = xmleb.getKeyName();
        mTableEles = xmleb.getElts();
        // System.out.println("xml:"+elt.asXML());
        return elt;
    }


    // *******************************************************************

    // 根据节点数进行多张表的数据入库
    private boolean insertdata(int lz, Element ele) throws SQLException
    {
        boolean isExsist;
        mLogicidval = queryLogicID(mMessage);
        if (mLogicidval == null || mLogicidval.trim().length() == 0)
        {
            return false;
        }
        isExsist = true;

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
            sqlList.add(sql);
            // System.out.println("sql --> " + sql);
        }

        DBProccess dbp = new DBProccess();
        if (dbp.executeUpdate(mCConn, sqlList, false, mExceptionFilter))
        {
            mCConn.commit();
            return true;
        }
        else
        {
            mCConn.rollback();
            return false;
        }
    }


    // 根据门诊信息中的卡号查找虚拟卡号 logicid
    private String queryLogicID(Element ele)
    {

        return mQIIH.queryLogicID(ele, mCConn);
    }


    // 数据入库
    private String insert(Element ele, boolean flag)
    {
        String tabName = ele.getName();
        String sql = "insert into " + tabName + "(";
        String val = " values(";
        if (("DC_MEDICARECORDS" + mHZ).equals(tabName) && flag)// 此条件为判断附表使用,此程序中不会执行
        {
            return "";
        }
        else
        {
            if (((TableEntity) mTableHM.get(tabName)).getColName().contains(
                mLogicidname))
            {
                sql = sql + mLogicidname + ",";
                val = val + "'" + mLogicidval + "',";
            }
            List<?> list = ele.attributes();
            for (int i = 0; i < list.size(); i++)
            {
                Attribute att = (Attribute) list.get(i);
                String colname = att.getName();
                if (((TableEntity) mTableHM.get(tabName)).getQueryCol()
                    .contains(colname))
                    continue;
                String type = (String) mColType.get(colname);
                String colval = "";
                colval = att.getText();
                colval = colval.replaceAll("'", "\"");
                if ("String".equals(type))
                {
                    val = val + "'" + colval + "',";
                }
                else if ("BigDecimal".equals(type))
                {
                    val = val + colval + ",";
                }
                else if ("Timestamp".equals(type))
                {
                    if ("null".equals(colval) || colval == null)
                    {
                        val = val + "null,";
                    }
                    else
                    {
                        val = val + "to_timestamp('" + colval
                                + "','yyyy-mm-dd hh24:mi:ssxff'),";
                    }
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

            // System.out.println("sql >>>: " + sql);
            return sql;
        }
    }


    public static void main(String[] args) throws JobExecutionException,
        DocumentException
    {
        SystemInit.init();
        GMDUpload cf = new GMDUpload();
        JDBCConn.init();
        cf.execute(null);
    }
}

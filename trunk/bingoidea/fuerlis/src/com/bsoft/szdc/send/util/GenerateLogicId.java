package com.bsoft.szdc.send.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GenerateLogicId
{

    private static Logger mLog = LogManager.getLogger(GenerateLogicId.class);


    public static synchronized String generateLogicId(Connection conn,
        String hospCode, String pid, String pidtype, String regdate,
        String name, String sex, String idCard, String birthday)
    {
        Statement stmt = null;
        String logicid = "";
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        Timestamp t = Timestamp.valueOf(regdate);
        String registerDate = dateFormat.format(t);

        String Cond = hospCode + "." + registerDate + ".";
        ResultSet rs = null;
        long num = 0;
        String maxid = null;
        try
        {
            stmt = conn.createStatement();
            rs = stmt
                .executeQuery("select max(logicid) as maxlogicid from DC_CARD where LOGICID like '"
                        + Cond + "%' ");

            if (rs != null && rs.next())
            {
                maxid = rs.getString("maxlogicid");
                if (maxid == null || maxid.length() == 0)
                {
                    num = 0;
                }
                else
                {
                    String[] items = maxid.split("\\.");
                    num = Integer.parseInt(items[2]);
                }
            }
        }
        catch (SQLException e)
        {
            mLog.error(e);
            e.printStackTrace();
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
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        rs = null;
        stmt = null;
        num++;
        java.text.DecimalFormat df = new java.text.DecimalFormat("00000");
        String strNum = df.format(num);
        logicid = Cond + strNum;

        String inscardstr = "insert into dc_card (LOGICID,PID,PIDTYPE,FLAG,APPCARDDATETIME) values ("
                + "'"
                + logicid
                + "', '"
                + pid
                + "' , "
                + pidtype
                + ", 1,to_date('"
                + regdate.substring(0, regdate.indexOf("."))
                + "','yyyy-mm-dd hh24:mi:ss') )";
        String fdate = "";
        if (birthday == null || birthday.equalsIgnoreCase("")
                || birthday.equalsIgnoreCase("null"))
        {
            String bday = "";
            if (pid.length() == 15)
                bday = "19" + pid.substring(6, 12);
            else
                bday = pid.substring(6, 14);
            fdate = "to_date('" + bday + "','yyyymmdd')";
        }
        else
        {
            String bday = birthday.substring(0, 10);
            fdate = "to_date('" + bday + "','yyyy-mm-dd')";
        }

        String inspatient = " insert into dc_patient(LOGICID, NAME,SEX,IDENTITYTYPE,IDENTITYCARD,BIRTHDAY) values ("
                + " '"
                + logicid
                + "', '"
                + name
                + "', "
                + sex
                + " , 1, '"
                + idCard + "'," + fdate + " )";

        try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate(inscardstr);
            stmt.executeUpdate(inspatient);
        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }
        finally
        {
            try
            {
                if (stmt != null)
                    stmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            stmt = null;
        }

        return logicid;
    }
}

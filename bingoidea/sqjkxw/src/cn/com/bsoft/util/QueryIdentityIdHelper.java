package cn.com.bsoft.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Element;

public class QueryIdentityIdHelper
{

    private Logger mLog = LogManager.getLogger(QueryIdentityIdHelper.class);


    public QueryIdentityIdHelper()
    {
    }


    // 通用查询方法 用于查询logicid 包括memcache的查询、维护 和 向数据库的数据请求
    public String queryLogicID(Element ele, Connection targetDBConnection)
    {
        String logicid = null;
        if (logicid == null)
        {
            Statement stmt = null;
            ResultSet rs = null;
            try
            {
                stmt = targetDBConnection.createStatement();
                String sql = "select logicid as logicid from dc_card where dc_card.pidtype='"
                        + ele.attributeValue("PIDTYPE")
                        + "' and  pid='"
                        + ele.attributeValue("PID") + "'";
                // System.out.println("logicid_sql:"+sql);
                rs = stmt.executeQuery(sql);

                if (rs.next())
                {
                    logicid = rs.getString("logicid");

                }
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
        }
        return logicid;
    }
}

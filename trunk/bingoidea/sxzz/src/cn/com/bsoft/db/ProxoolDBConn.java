package cn.com.bsoft.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

import cn.com.bsoft.util.SystemInitImpl;

public class ProxoolDBConn
{
    public Connection getConnection()
    {
        Connection conn = null;
        try
        {
            JAXPConfigurator.configure("config/proxool.xml", false);
            conn = DriverManager.getConnection("proxool.xml-sxzz");
        }
        catch (ProxoolException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }


    public static void main(String[] args)
    {
        SystemInitImpl.init();
        ProxoolDBConn pxdbc = new ProxoolDBConn();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from zy_yspb";
        try
        {
            stmt = pxdbc.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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
        }
    }
}

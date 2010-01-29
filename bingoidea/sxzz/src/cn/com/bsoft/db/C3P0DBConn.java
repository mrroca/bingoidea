package cn.com.bsoft.db;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cn.com.bsoft.util.Constant;
import cn.com.bsoft.util.SystemInitImpl;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0DBConn
{

    private static ComboPooledDataSource ds = null;
    private static Logger log = LogManager.getLogger(C3P0DBConn.class);


    public static void getConfig() throws FileNotFoundException
    {
        File file = new File(Constant.DBCONNECT_CONFIG);
        if (!file.exists())
        {
            throw new FileNotFoundException("miss file."
                    + Constant.DBCONNECT_CONFIG);
        }
        InputStream is = new FileInputStream(file);
        Properties p = new Properties();
        try
        {
            p.load(is);
        }
        catch (IOException e)
        {
            log.error(e);
        }
        ds = new ComboPooledDataSource();
        try
        {
            ds.setDriverClass(p.getProperty("driverClass"));
        }
        catch (PropertyVetoException e)
        {
            log.error(e);
        }
        ds.setJdbcUrl(p.getProperty("jdbcUrl"));
        ds.setUser(p.getProperty("user"));
        ds.setPassword(p.getProperty("password"));
        ds.setAcquireIncrement(Integer.parseInt(p
            .getProperty("acquireIncrement")));
        ds.setAcquireRetryAttempts(Integer.parseInt(p
            .getProperty("acquireRetryAttempts")));
        ds.setAcquireRetryDelay(Integer.parseInt(p
            .getProperty("acquireRetryDelay")));
        ds.setAutoCommitOnClose(new Boolean(p.getProperty("autoCommitOnClose"))
            .booleanValue());
        ds.setBreakAfterAcquireFailure(new Boolean(p
            .getProperty("breakAfterAcquireFailure")).booleanValue());
        ds.setCheckoutTimeout(Integer
            .parseInt(p.getProperty("checkoutTimeout")));
        ds.setIdleConnectionTestPeriod(Integer.parseInt(p
            .getProperty("idleConnectionTestPeriod")));
        ds.setInitialPoolSize(Integer
            .parseInt(p.getProperty("initialPoolSize")));
        ds.setMaxIdleTime(Integer.parseInt(p.getProperty("maxIdleTime")));
        ds.setMaxPoolSize(Integer.parseInt(p.getProperty("maxPoolSize")));
        ds.setMinPoolSize(Integer.parseInt(p.getProperty("minPoolSize")));
        ds.setMaxStatements(Integer.parseInt(p.getProperty("maxStatements")));
        ds.setNumHelperThreads(Integer.parseInt(p
            .getProperty("numHelperThreads")));
        ds.setTestConnectionOnCheckin(new Boolean(p
            .getProperty("testConnectionOnCheckin")).booleanValue());
    }


    public static synchronized Connection getConnection()
    {
        try
        {
            getConfig();
        }
        catch (FileNotFoundException e1)
        {
            log.error(e1);
        }
        Connection con = null;
        try
        {
            con = ds.getConnection();
        }
        catch (SQLException e)
        {
            log.error(e);
        }
        return con;
    }


    public static void main(String[] args)
    {
        SystemInitImpl.init();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        con = C3P0DBConn.getConnection();
        String sql = "select * from zy_yspb";
        try
        {
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException e)
        {
            log.error(e);
        }
    }
}

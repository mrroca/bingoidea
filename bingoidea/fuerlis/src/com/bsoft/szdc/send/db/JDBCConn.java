package com.bsoft.szdc.send.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bsoft.szdc.send.util.Constant;
import com.bsoft.szdc.send.util.Encryption;

public class JDBCConn
{

    private static boolean isInit = false;

    private static Document mDoc = null;


    public static void init() throws DocumentException
    {
        if (!isInit)
        {
            mDoc = new SAXReader().read(Constant.DBCONNECT_CONFIG);
        }
    }


    public static Connection getConnection()
    {
        Connection conn = null;
        Element root = mDoc.getRootElement();
        String driver = root.element("driver").getText();
        String url = root.element("url").getText();
        String username = root.element("username").getText();
        String password = root.element("password").getText();
        username = Encryption.decrypt(username);
        password = Encryption.decrypt(password);
        try
        {

            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("conn=" + conn);
            conn.setAutoCommit(false);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return conn;
    }


    public static Connection getBakConnection()
    {
        Connection conn = null;
        Element root = mDoc.getRootElement();
        String driver = root.element("center_driver").getText();
        String url = root.element("center_url").getText();
        String username = root.element("center_username").getText();
        String password = root.element("center_password").getText();
        username = Encryption.decrypt(username);
        password = Encryption.decrypt(password);
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return conn;
    }


    public static void main(String[] args) throws SQLException
    {
        try
        {
            JDBCConn.init();
        }
        catch (DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection conn = JDBCConn.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from admission");
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i < rsmd.getColumnCount(); i++)
        {
            System.out.println(rsmd.getColumnName(i));
        }
        rs.close();
        stmt.close();
        conn.close();
    }

}

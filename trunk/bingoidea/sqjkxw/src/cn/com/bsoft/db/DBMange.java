package cn.com.bsoft.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.com.bsoft.util.Constant;
import cn.com.bsoft.util.Encryption;

public class DBMange {

	private static boolean isInit = false;

	private static Document mDoc = null;

	public static void init() throws DocumentException {
		if (!isInit) {
			mDoc = new SAXReader().read(Constant.MOREDBCONNECT_CONFIG);
		}
	}

	public static void initGT() throws DocumentException {
		if (!isInit) {
			mDoc = new SAXReader().read(Constant.MOREDBCONNECT_CONFIG);
		}
	}

	public static Connection getConnection() throws DocumentException {
		Connection conn = null;
		init();
		Element root = mDoc.getRootElement();
		String driver = root.element("driver").getText();
		String url = root.element("url").getText();
		String username = root.element("username").getText();
		String password = root.element("password").getText();
		username = Encryption.decrypt(username);
		password = Encryption.decrypt(password);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return conn;
	}

	public static Connection getBakConnection() {
		Connection conn = null;
		Element root = mDoc.getRootElement();
		String driver = root.element("center_driver").getText();
		String url = root.element("center_url").getText();
		String username = root.element("center_username").getText();
		String password = root.element("center_password").getText();
		username = Encryption.decrypt(username);
		password = Encryption.decrypt(password);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return conn;
	}
}

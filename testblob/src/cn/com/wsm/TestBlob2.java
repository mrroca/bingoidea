package cn.com.wsm;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBlob2 {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, IOException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@172.25.220.101:1521:szdc", "szdc", "bsoft");
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		ResultSet rs = st
				.executeQuery("select BLOBATTR from TESTBLOB where ID=1");
		if (rs.next()) {
			java.sql.Blob blob = rs.getBlob("BLOBATTR");
			InputStream inStream = blob.getBinaryStream();
			int blobsize = (int) blob.length();
			byte[] data = new byte[blobsize];
			while (inStream.read(data) != -1) {
			}
			System.out.println("--------" + new String(data,"utf-8")+"---------");
		}
		con.commit();
		con.close();
	}

}

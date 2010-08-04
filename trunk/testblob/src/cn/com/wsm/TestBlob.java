package cn.com.wsm;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBlob {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		//通过JDBC获得数据库连接
	     Class.forName("oracle.jdbc.driver.OracleDriver");
	     Connection con = DriverManager.getConnection("jdbc:oracle:thin:@172.25.220.101:1521:szdc", "szdc", "bsoft");
	     con.setAutoCommit(false);
	     Statement st = con.createStatement();
	     //插入一个空对象empty_blob()
	     st.execute("insert into TESTBLOB (ID, NAME, BLOBATTR) values (1, 'thename', empty_blob())");
	     //锁定数据行进行更新，注意“for update”语句
	     ResultSet rs = st.executeQuery("select BLOBATTR from TESTBLOB where ID=1 for update");
	     if (rs.next())
	     {
	         //得到java.sql.Blob对象后强制转换为oracle.sql.BLOB
	         oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob("BLOBATTR");
	         @SuppressWarnings("deprecation")
	         OutputStream outStream = blob.getBinaryOutputStream();
	         //data是传入的byte数组，定义：byte[] data
	         byte[] data = "aaa".getBytes();
	         outStream.write(data, 0, data.length);
		     outStream.flush();
		     outStream.close();
	     }

	     con.commit();
	     con.close();

	}

}

package cn.com.bsoft.proxy.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import cn.com.bsoft.proxy.db.JDBCConn;


public class Test {
	
	private static Logger log = LogManager.getLogger(Test.class);
	
	public static void QueryData() {
		Connection mConn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) as count from dc_card where rownum<=2";
		mConn = JDBCConn.getBakConnection();
		try {
			stmt = mConn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("查询中心表：dc_card 条件：rownum<=2  得到结果："
						+ rs.getString("count"));
				if ("2".equals(rs.getString("count"))) {
					System.out.println("查询正确！");
				}else{
					System.out.println("查询失败！");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				mConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		log.info("打印日志成功！");
		System.out.println("所得医院名称：   " + QueryHospitalConfig.ReadHospitalName());
		System.out.println("所得医院代码：   " + QueryHospitalConfig.ReadHospitalCode());
		try {
			JDBCConn.init();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Test.QueryData();
		System.out.println("请查询日志输出是否正常./logs/log4j.log");
	}
}

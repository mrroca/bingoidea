package cn.com.bsoft.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DBProccess {

	private Logger mLog = LogManager.getLogger(DBProccess.class);

	public static synchronized boolean executeUpdate(Connection conn,
			String sql, boolean autoCommit) {
		boolean flag = false;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			if (autoCommit) {
				conn.commit();
			}
			flag = true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			flag = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}
			stmt = null;
		}
		return flag;
	}

	private boolean execute(Connection conn, String sql, boolean autocommit) {
		boolean flag = false;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			if (autocommit) {
				conn.commit();
			}
			flag = true;
		} catch (SQLException sqle) {
			mLog.error(sqle);
			mLog.error("error sql:" + sql);
			try {
				conn.rollback();
			} catch (Exception e) {
			}
			flag = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}
			stmt = null;
		}
		return flag;
	}

	private boolean execute(Connection conn, String sql, boolean autocommit,
			List<?> excep) {
		boolean flag = false;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			if (autocommit) {
				conn.commit();
			}
			flag = true;
		} catch (SQLException sqle) {
			for (int i = 0; i < excep.size(); i++) {
				if (sqle.getMessage().substring(0, 9).equalsIgnoreCase(
						(String) excep.get(i))) {
					flag = true;
					break;
				}

			}
			if (!flag) {
				mLog.error(sqle);
				mLog.error("error sql:" + sql);
				try {
					conn.rollback();
				} catch (Exception e) {
				}
				flag = false;
			}
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}
			stmt = null;
		}
		return flag;
	}

	public boolean executeUpdate(Connection conn, List<?> list,
			boolean autoCommit) {
		boolean flag = true;
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			String sql = (String) iter.next();
			if (!execute(conn, sql, false)) {
				flag = false;
				break;
			}
		}
		if (autoCommit && flag) {
			try {
				conn.commit();
			} catch (SQLException e) {
			}
		}
		return flag;
	}

	public boolean executeUpdate(Connection conn, List<?> list,
			boolean autoCommit, List<?> excepList) {

		boolean flag = true;
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			String sql = (String) iter.next();
			// System.out.println("===sql====" + sql);
			if (!execute(conn, sql, false, excepList)) {
				flag = false;
				break;
			}
		}
		if (autoCommit && flag) {
			try {
				conn.commit();
			} catch (SQLException e) {
				mLog.error(e);
			}
		}
		return flag;
	}

	public static synchronized boolean executeUpdate(Connection conn,
			String sql, boolean autoCommit, List<?> excep) {
		boolean flag = false;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			if (autoCommit) {
				conn.commit();
			}
			flag = true;
		} catch (SQLException sqle) {
			try {
				conn.rollback();
			} catch (Exception e) {
			}
			flag = false;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}
			stmt = null;
		}
		return flag;
	}
}

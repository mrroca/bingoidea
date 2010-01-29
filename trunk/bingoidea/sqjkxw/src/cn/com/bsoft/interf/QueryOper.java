package cn.com.bsoft.interf;

import java.sql.ResultSet;
import java.sql.Statement;

public abstract interface QueryOper {

	public abstract ResultSet query(String scriptPath, Statement stat);

	public abstract String queryLogicId(String pid, String pidtype);

}

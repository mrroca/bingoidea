package org.users.vo;

import org.apache.ibatis.session.SqlSession;
import org.common.utils.SqlMapUtils;

public class UserDAO {
	private static final String SQL_CMD_USER_GET_COUNT = "Users.getCountOfUsers";
	private static final String SQL_CMD_USER_GET_USER = "Users.getUserByPK";
	
	private SqlSession session = null;
	
	public UserDAO(){
		session = SqlMapUtils.getInstance().getSession();
	}
	
	public int insert(UserModel user) throws Exception{
		return 0;
	}
	
	public int update(UserModel user) throws Exception{
		return 0;
	}
	
	public int getUsersCount(UserModel param) throws Exception{
		int result = -1;
		
		Object obj = session.selectOne(SQL_CMD_USER_GET_COUNT, param);
		if (obj instanceof Integer){
			result = (Integer)obj;
		}
		
		return result;
	}
	
	public UserModel getUserByPK(UserModel param) throws Exception{
		UserModel user = new UserModel();
		Object obj = session.selectOne(SQL_CMD_USER_GET_USER, param);
		if (obj instanceof UserModel){
			user = (UserModel)obj;
		}
		
		return user;
	}
	
}	

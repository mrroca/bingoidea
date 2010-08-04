package org.ibatis3.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.users.vo.UserDAO;
import org.users.vo.UserModel;

public class TestUserDAO {	
	private UserDAO dao = null;
	@Before
	public void setUp() throws Exception {
		dao = new UserDAO();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUsersCount() {
		UserModel searchModel = new UserModel();
		searchModel.setUserName("yuzp");
		try {
			int result = dao.getUsersCount(searchModel);
			assertEquals(1, result);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByPK(){
		UserModel searchModel = new UserModel();
		searchModel.setUserId(2);
		try {
			UserModel userResult = dao.getUserByPK(searchModel);
			assertEquals("Yang Fan", userResult.getName());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package org.ibatis3.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newsnotice.dao.NewsNoticeDAO;
import org.newsnotice.domain.NewsNoticeModel;

public class TestNewsNoticeDAO {
	private NewsNoticeDAO dao = null;
	
	@Before
	public void setUp() throws Exception {
		dao = new NewsNoticeDAO();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testGetNewsNoticeByCondition() {
		NewsNoticeModel param = new NewsNoticeModel();
		param.setId(249L);
		try {
			NewsNoticeModel result = dao.getNewsNoticeByCondition(param);
			System.out.println("NN Id = " + result.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

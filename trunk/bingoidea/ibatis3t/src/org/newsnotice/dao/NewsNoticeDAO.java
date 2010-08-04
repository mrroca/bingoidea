package org.newsnotice.dao;

import org.apache.ibatis.session.SqlSession;
import org.common.utils.SqlMapUtils;
import org.newsnotice.domain.NewsNoticeModel;

public class NewsNoticeDAO {
	
	private static final String CMD_SQL_GET_NEWS_NOTICE = "NewsNotice.getNewsNotice";
	
	private SqlSession session = null;
	
	public NewsNoticeDAO(){
		session = SqlMapUtils.getInstance().getSession();
	}
	
	public NewsNoticeModel getNewsNoticeByCondition(NewsNoticeModel param) throws Exception{
		Object obj = session.selectOne(CMD_SQL_GET_NEWS_NOTICE, param);
		NewsNoticeModel nn = new NewsNoticeModel();
		if (obj instanceof NewsNoticeModel){
			nn = (NewsNoticeModel)obj;
		}
		
		return nn;
	}
}

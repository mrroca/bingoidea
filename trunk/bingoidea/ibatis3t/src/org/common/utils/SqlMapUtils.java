package org.common.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SqlMapUtils {
	private static final Logger logger = LogManager.getLogger(SqlMapUtils.class);
		
	private static final String CONFIG_FILE_PATH = "conf/SqlmapConfiguration.xml";
	
	private SqlSessionFactory sqlSessionFactory = null;
	private SqlSession session = null;
	private static SqlMapUtils instance = null;
	
	private SqlMapUtils(){
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(CONFIG_FILE_PATH);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development_oracle");
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			logger.fatal("IO Exception occured while reading the Configuration File \n" + e);
		} catch (Exception e){
			logger.fatal("Exception occured.");
			e.printStackTrace();
		} finally {
		
			try{
				if (reader != null){
					reader.close();
				}
			}catch(Exception e){
				logger.fatal("Close reader failed.\n" + e);
			}
		}
	}
	
	public static synchronized SqlMapUtils getInstance(){
		if (instance == null ){
			synchronized(SqlMapUtils.class){
				instance = new SqlMapUtils();
			}
		}
		
		return instance;
	}
	 
	public SqlSession getSession(){
		if (session == null){
			throw new RuntimeException("Create SqlSession failed.");
		}
		
		return session;
	}
	
	 
	 
}

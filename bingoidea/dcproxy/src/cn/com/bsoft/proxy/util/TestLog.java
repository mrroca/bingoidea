package cn.com.bsoft.proxy.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger; //import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class TestLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		DOMConfigurator.configure(Constant.LOG4J_CONFIG);
		// PropertyConfigurator.configure("config/log4j.properties");

		Logger log = null;

		// System.setProperty("log4j.configuration", "config/log4j.properties");
		// LogManager.resetConfiguration();
		log = LogManager.getLogger(TestLog.class);
		//System.out.println(TestLog.class.getClass().getResource("/"));
		log.error("OK!error");
		log.info("OK!info");
		log.debug("OK!debug");
		
		long end = System.currentTimeMillis();
		System.out.println(start);
		System.out.println(end);
		System.out.println((end-start)+"ms");
	}

}

package cn.com.bsoft.util;

import org.apache.log4j.xml.DOMConfigurator;

public class SystemInit {

	public static void init() {

		DOMConfigurator.configure(Constant.LOG4J_CONFIG);

	}
}

package cn.com.bsoft.proxy.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import cn.com.bsoft.proxy.db.JDBCConn;
import cn.com.bsoft.proxy.util.Constant;
import cn.com.bsoft.proxy.util.LogConfig;
import cn.com.bsoft.proxy.util.UtilTools;

public class Scheulder {

	public Scheulder() {

	}

	private static final Log log = LogFactory.getLog(Scheulder.class);

	private static Log getLog() {
		return Scheulder.log;
	}

	public static void start(SchedulerFactory sf) {
		try {
			JDBCConn.init();
			new UtilTools();
			Scheduler scheduler = sf.getScheduler();
			scheduler.start();
			getLog().info("log Start working...");
			System.out.println("Start working...");
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (DocumentException de) {
			de.printStackTrace();
			System.err.println("程序初始化异常:" + de.getMessage());
			return;
		}
	}

	public static void main(String args[]) {
		try {
			LogConfig.getLogConfig();
			SchedulerFactory sf = new StdSchedulerFactory(
					Constant.QUARTZ_CONFIG);
			start(sf);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

package cn.com.bsoft.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import cn.com.bsoft.db.JDBCConn;
import cn.com.bsoft.util.Constant;
import cn.com.bsoft.util.SystemInit;
import cn.com.bsoft.util.UtilTools;

public class Scheduler {

	public Scheduler() {

	}

	private static final Log log = LogFactory.getLog(Scheduler.class);

	private static Log getLog() {
		return Scheduler.log;
	}

	public static void start(SchedulerFactory sf) {
		try {
			JDBCConn.init();
			new UtilTools();
			org.quartz.Scheduler scheduler = sf.getScheduler();
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
			SystemInit.init();
			SchedulerFactory sf = new StdSchedulerFactory(
					Constant.QUARTZ_CONFIG);
			start(sf);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

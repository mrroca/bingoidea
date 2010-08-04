package cn.wsm.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class TimerTest {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat fTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d1 = fTime.parse("2010/12/30 14:10:00");
		Timer timer = new Timer();

		MyTask myTask1 = new MyTask();
		MyTask myTask2 = new MyTask();
		myTask2.setInfo("myTask-2");
		timer.schedule(myTask1, d1, 5000);//从程序运行时开始执行，每间隔5秒执行一次
		timer.scheduleAtFixedRate(myTask2, d1, 5000);//从指定的时间开始，并运行  从开始以来的时间/间隔数 次
//		while (true) {
//			try {
//				byte[] info = new byte[1024];
//				int len = System.in.read(info);
//				String strInfo = new String(info, 0, len, "GBK");// 从控制台读出信息
//				if (strInfo.charAt(strInfo.length() - 1) == ' ') {
//					strInfo = strInfo.substring(0, strInfo.length() - 2);
//				}
//				if (strInfo.startsWith("Cancel-1")) {
//					myTask1.cancel();// 退出单个任务
//					// 其实应该在这里判断myTask2是否也退出了,是的话就应该break.但是因为无法在包外得到
//					// myTask2的状态,所以,这里不能做出是否退出循环的判断.
//				} else if (strInfo.startsWith("Cancel-2")) {
//					myTask2.cancel();
//				} else if (strInfo.startsWith("Cancel-All")) {
//					timer.cancel();// 退出Timer
//					break;
//				} else {
//					// 只对myTask1作出判断,偷个懒^_^
//					myTask1.setInfo(strInfo);
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		//}
	}
}
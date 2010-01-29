package cn.com.bsoft.report.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;
import javax.net.ServerSocketFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import cn.com.bsoft.util.Constant;
import cn.com.bsoft.util.SystemInit;

/**
 * 用于接收各个医院前置机数据采集程序完成情况报告的服务端 传输类型为TCP/IP
 */

public class SenderReportServer {

	private static ServerSocket mSS = null;

	private static Properties mProp = null;

	private static Document mHospIp = null;

	private static InetAddress clientIp = null;

	private Logger mLog = LogManager.getLogger(this.getClass());

	public SenderReportServer() throws ReportServerException {
		try {
			init();
		} catch (Exception e) {
			reportError(e);
			throw new ReportServerException(e);
		}
	}

	private boolean init() throws ReportServerException {
		boolean flag = false;
		mProp = System.getProperties();

		try {
			mHospIp = new SAXReader().read(Constant.HOSP_IP);
			List<?> hosps = mHospIp.selectNodes("root/hosip");
			for (int i = 0; i < hosps.size(); i++) {
				Element ele = (Element) hosps.get(i);
				File file = new File(Constant.REPORT_DIRCTORY
						+ ele.getTextTrim() + ele.attributeValue("ip") + ".rep");
				if (!file.exists())
					file.createNewFile();
				Controler.setEntity(ele.attributeValue("ip"), file);
			}
		} catch (DocumentException e1) {
			reportError(e1);
			flag = false;
			throw new ReportServerException(e1);
		} catch (IOException e) {
			reportError(e);
			flag = false;
			throw new ReportServerException(e);
		}

		InputStream is = null;
		try {
			is = new FileInputStream(new File(Constant.SERVER_CFG));
			mProp.load(is);
			int port = Integer.parseInt(mProp.getProperty("bind_port"));
			mSS = ServerSocketFactory.getDefault().createServerSocket(port);
			flag = true;
		} catch (IOException ioe) {
			reportError(ioe);
			flag = false;
			throw new ReportServerException(ioe);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				reportError(e);
				flag = false;
				throw new ReportServerException(e);
			}
		}
		return flag;
	}

	private void reportError(Exception e) {
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++)
			mLog.error(ste[i].toString());
	}

	public void recvMsg() {
		if (mSS != null)
			while (true) {
				try {
					Socket sok = mSS.accept();
					clientIp = sok.getInetAddress();
					Oper oper = new SenderReportServer.Oper(sok);
					Thread thread = new Thread(oper);
					thread.start();
				} catch (IOException e) {
					reportError(e);
				}
			}
	}

	public class Oper implements Runnable {

		private Socket mSok = null;

		public Oper() {
		}

		public Oper(Socket s) {
			mSok = s;
		}

		public void setSocket(Socket s) {
			mSok = s;
		}

		private boolean writeReport(String msg) {
			try {
				Document doc = DocumentHelper.parseText(msg);
				String hosp = doc.getRootElement().element("hosip")
						.attributeValue("ip");
				Node hospNode = mHospIp.getRootElement().selectSingleNode(
						"//hosip[@ip='" + hosp + "']");
				if (hospNode == null)
					return false;
				String report = doc.getRootElement().element("hosip").getText();
				File file = (File) Controler.getEntity(hosp);
				synchronized (file) {
					FileOutputStream fos = new FileOutputStream(file, true);
					fos.write(report.getBytes("gb2312"));
					fos.write(System.getProperty("line.separator").getBytes());
					fos.flush();
					fos.close();
				}
				return true;
			} catch (DocumentException e) {
				String s = "";
				if (clientIp != null) {
					s = "主机名：" + clientIp.getHostName() + " IP地址："
							+ clientIp.getHostAddress();
				}
				mLog.info("捕获非法数据:" + msg);
				if (!s.equals("")) {
					mLog.info("数据来源为:[" + s + "]");
				}
				return false;
			} catch (IOException e) {
				mLog.info("文件读写出错"+e.getMessage());
				return false;
			}
		}

		private void sendMsg(OutputStream os, String msg) throws IOException {
			os.write(msg.getBytes("gb2312"));
			os.flush();
		}

		private byte[] RecvMsg(InputStream is, int length) throws IOException {
			byte[] buf = new byte[length];
			is.read(buf, 0, length);
			return buf;
		}

		public void run() {
			DataInputStream dis = null;
			DataOutputStream dos = null;
			try {
				dis = new DataInputStream(mSok.getInputStream());
				dos = new DataOutputStream(mSok.getOutputStream());
				int buf_size = Integer.parseInt(new String(RecvMsg(dis, 16))
						.trim());
				sendMsg(dos, "ready");
				String msg = new String(RecvMsg(dis, buf_size), "gb2312");
				sendMsg(dos, "done");
				writeReport(msg);
			} catch (IOException e) {
				mLog.info("IO操作异常：" + e.getMessage());
			} catch (NumberFormatException e) {
				String s = "";
				if (clientIp != null) {
					s = "主机名：" + clientIp.getHostName() + " IP地址："
							+ clientIp.getHostAddress();
				}
				mLog.info("非法数据转换错误:" + e.getMessage());
				if (!s.equals("")) {
					mLog.info("数据来源为:[" + s + "]");
				}
			}
		}
	}

	public static void main(String[] args) throws ReportServerException {
		SystemInit.init();
		SenderReportServer srs = new SenderReportServer();
		srs.recvMsg();
	}
}
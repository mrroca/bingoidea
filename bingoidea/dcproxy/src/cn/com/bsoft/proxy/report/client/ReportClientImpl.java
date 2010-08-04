package cn.com.bsoft.proxy.report.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.net.SocketFactory;

import cn.com.bsoft.proxy.util.Constant;

public class ReportClientImpl implements ReportClient {

	private Properties mProp = null;

	private String mServerIp = null;

	private String mServerPort = null;

	public ReportClientImpl() throws IOException {
		mProp = System.getProperties();
		File file = new File(Constant.REPORT_CLIENT_CFG);
		InputStream is = new FileInputStream(file);
		mProp.load(is);
		mServerIp = mProp.getProperty("server_ip");
		mServerPort = mProp.getProperty("server_port");
		is.close();
	}

	/**
	 * setSoTimeout(10000)的含义是:当进行read()读数据操作时,最多阻塞10秒钟.时间一到(不再等待读下去了),
	 * 抛出SocketTimeoutException异常
	 * */
	public void sendReport(String msg) throws IOException {
		Socket sok = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			SocketFactory sf = SocketFactory.getDefault();
			sok = sf.createSocket(mServerIp, Integer.parseInt(mServerPort));
			sok.setSoTimeout(1500);

			dos = new DataOutputStream(sok.getOutputStream());
			dis = new DataInputStream(sok.getInputStream());

			byte[] msgs = msg.getBytes("gb2312");
			// System.out.println(String.valueOf(msgs.length).getBytes());
			dos.write(String.valueOf(msgs.length).getBytes());
			dos.flush();

			while (true) {
				byte[] r = new byte[5];
				dis.read(r, 0, r.length);
				if ("ready".equals(new String(r).trim()))
					break;
			}

			dos.write(msgs);
			dos.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dis != null)
				dis.close();
			if (dos != null)
				dos.close();
			if (sok != null && !sok.isClosed()) {
				sok.shutdownInput();
				sok.shutdownOutput();
				sok.close();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		ReportClientImpl rci = new ReportClientImpl();
		String msg = "<szwsj><hosip ip='172.25.6.12'>数据采集完成   success:1039,error:0</hosip></szwsj>";
		rci.sendReport(msg);
	}
}

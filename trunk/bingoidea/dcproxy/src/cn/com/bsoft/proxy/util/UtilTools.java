package cn.com.bsoft.proxy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

public class UtilTools {

	private static String mHostIP = "";

	static {
		try {
			mHostIP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public UtilTools() {
	}

	public static String getHostIP() {
		if (mHostIP == null || mHostIP.trim().length() == 0)
			try {
				mHostIP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		return mHostIP;
	}

	public String getQueryScript(String filePath) throws FileNotFoundException,
			IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException("缺少必要的脚本文件." + filePath);
		}
		InputStream is = new FileInputStream(file);
		Properties prop = new Properties();
		try {
			prop.load(is);
			is.close();
		} catch (IOException e) {
			throw e;
		}
		String script = prop.getProperty("script");
		String arge = prop.getProperty("arge");
		List<String> argeList = new ArrayList<String>();
		if (arge.indexOf(",") != -1) {
			String[] arges = arge.split(",");
			for (int i = 0; i < arges.length; i++) {
				argeList.add(arges[i]);
			}
		} else {
			argeList.add(arge);
		}
		String value = prop.getProperty("value");
		List<String> valueList = new ArrayList<String>();
		if (arge.indexOf(",") != -1) {
			String[] values = value.split(",");
			for (int i = 0; i < values.length; i++) {
				valueList.add(values[i]);
			}
		} else {
			valueList.add(value);
		}
		for (int i = 0; i < argeList.size(); i++) {
			String tmp = (String) argeList.get(i);
			String tmp2 = (String) valueList.get(i);
			script = script.replaceAll("\\" + tmp, tmp2);
		}
		String key = prop.getProperty("key");
		String kval = prop.getProperty("kval");
		script = script.replaceAll("\\" + key, kval);

		return script;
	}

	public static String getRandomLong() {

		if (mHostIP == null || mHostIP.trim().length() == 0)
			try {
				mHostIP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

		String i = Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR) + ""
				+ Calendar.getInstance(Locale.CHINA).get(Calendar.MONTH) + ""
				+ Calendar.getInstance(Locale.CHINA).get(Calendar.DAY_OF_MONTH)
				+ ""
				+ Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR_OF_DAY)
				* 13 + ""
				+ Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE) * 7
				+ "" + Calendar.getInstance(Locale.CHINA).get(Calendar.SECOND)
				* 3;
		return mHostIP
				+ Math.abs((new Random(Long.parseLong(i)).nextLong()))
				+ ""
				+ Long.toHexString(
						new Random(System.currentTimeMillis()).nextLong())
						.substring(3, 6);
	}

	public String formatValue(String type, String colval)
			throws NumberFormatException {
		if ("String".equals(type)) {
			if (colval == null || colval.trim().length() == 0
					|| "null".equals(colval)) {
				return "";
			} else
				return colval;
		} else if ("Integer".equals(type) || "BigDecimal".equals(type)) {
			if (colval == null || colval.trim().length() == 0
					|| "null".equals(colval.trim().toLowerCase())) {
				return "null";
			} else {
				BigDecimal db = new BigDecimal(Float.valueOf(colval));
				return db.toString();
			}
		} else if ("Timestamp".equals(type) || "Date".equals(type)
				|| "null".equals(colval.trim().toLowerCase())) {
			if (colval == null || colval.trim().length() == 0) {
				return "null";
			} else {
				Timestamp str = Timestamp.valueOf(colval);
				return str.toString();
			}
		}
		return colval;
	}
}

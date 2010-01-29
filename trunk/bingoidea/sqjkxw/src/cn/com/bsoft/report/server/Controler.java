package cn.com.bsoft.report.server;

import java.util.HashMap;

public class Controler {

	private static HashMap<String, Object> mHM = new HashMap<String, Object>();

	public static void setEntity(String key, Object obj) {
		mHM.put(key, obj);
	}

	public static Object getEntity(String key) {
		return mHM.get(key);
	}

}

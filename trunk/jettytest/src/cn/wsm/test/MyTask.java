package cn.wsm.test;

import java.util.TimerTask;

public class MyTask extends TimerTask {
	String info = "^_^";

	@Override
	public void run() {
		System.out.println(getInfo());
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}

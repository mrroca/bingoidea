package cn.com.bsoft.proxy.report.client;

import java.io.IOException;

public interface ReportClient {

	public void sendReport(String msg) throws IOException;

}

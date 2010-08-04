package cn.wsm.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class TestUploadCheckRecords {

	public static final String HTTP_URL = "http://127.0.0.1:8080/servlet/testservlet";

	public static void main(String[] args) {
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(HTTP_URL);
		// RequestEntity entity = null;
		StringRequestEntity entity = null;

		try {
			entity = new StringRequestEntity("<message>hello</message>", "text/xml", "GB2312");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		postMethod.addRequestHeader("hi.adp", "hellointerface");

		// postMethod.addRequestHeader("Content-Type",
		// "text/html; charset=GB2312");
		postMethod.setRequestEntity(entity);

		int returnCode = 0;
		try {
			returnCode = client.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		System.out.println("Return code[" + returnCode + "]");

		if (returnCode == HttpServletResponse.SC_OK) {
			Header[] headers = postMethod.getResponseHeaders();
			for (int i = 0; i < headers.length; i++) {
				System.out.println("Header[" + headers[i].getName()+ "] value[" + headers[i].getValue() + "].");
			}
		}

		try {
			System.out.println("Return msgId["+ postMethod.getResponseBodyAsString() + "]");
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		postMethod.releaseConnection();
	}
}

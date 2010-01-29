package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PostAFile {
    private static String url ="http://90.0.12.20:8088/NationWideAdmin/test/GetRequest.jsp";

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        client.setConnectionTimeout(8000);

        // Send any XML file as the body of the POST request
        File f = new File("students.xml");
        System.out.println("File Length = " + f.length());

        postMethod.setRequestBody(new FileInputStream(f));
        postMethod.setRequestHeader("Content-type","text/xml; charset=ISO-8859-1");

        //int statusCode1 = client.executeMethod(postMethod);
        //打印结果页面
        String response = new String(postMethod.getResponseBodyAsString().getBytes("8859_1"));
        //打印返回的信息
        System.out.println("===================================");
        System.out.println("返回的求内容:");
        System.out.println(response);
        System.out.println("===================================");

        System.out.println("statusLine>>>" + postMethod.getStatusLine());
        postMethod.releaseConnection();
    }
}

package org.apache.commons.httpclient.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;

public class SubmitHttpForm {

    private static String url ="http://90.0.12.20:8088/NationWideAdmin/test/httpClientLogin.jsp";

    public static void main(String[] args) {

        //Instantiate an HttpClient
        HttpClient client = new HttpClient();

        //Instantiate a GET HTTP method
        //HttpMethod method = new GetMethod(url);
        //Instantiate a POST HTTP method
        PostMethod method = new PostMethod(url);

        //Define name-value pairs to set into the QueryString
        NameValuePair nvp1 = new NameValuePair("firstName", "fname");
        NameValuePair nvp2 = new NameValuePair("lastName", "lname");
        NameValuePair nvp3 = new NameValuePair("email", "email@email.com");

        method.setQueryString(new NameValuePair[] {nvp1, nvp2, nvp3});

        try {
            int statusCode = client.executeMethod(method);

            System.out.println("QueryString>>> " + method.getQueryString());
            System.out.println("Status Text>>> " + HttpStatus.getStatusText(statusCode));

            //Get data as a String
            //打印结果页面
            String response = new String(method.getResponseBodyAsString().getBytes("8859_1"));
            //打印返回的信息
            System.out.println("===================================");
            System.out.println("返回的求内容:");
            System.out.println(response);
            System.out.println("===================================");

            //OR as a byte array
            byte[] res = method.getResponseBody();

            //write to file
            FileOutputStream fos = new FileOutputStream("donepage.html");
            fos.write(res);

            //release connection
            method.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

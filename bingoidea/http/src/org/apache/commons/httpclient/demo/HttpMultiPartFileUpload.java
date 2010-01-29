package org.apache.commons.httpclient.demo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.MultipartPostMethod;

/*
 6． 通过HTTP上传文件
 httpclient使用了单独的一个HttpMethod子类来处理文件的上传，这个类就是MultipartPostMethod，该类已经封装了文件上传的细节，我们要做的仅仅是告诉它我们要上传文件的全路径即可
*/

@SuppressWarnings("deprecation")
public class HttpMultiPartFileUpload {
    private static String url = "http://90.0.12.20:8088/NationWideAdmin/test/ProcessFileUpload.jsp";

    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();
        MultipartPostMethod mPost = new MultipartPostMethod(url);
        client.setConnectionTimeout(8000);

        // Send any XML file as the body of the POST request
        File f1 = new File("students.xml");
        File f2 = new File("academy.xml");
        File f3 = new File("academyRules.xml");

        System.out.println("File1 Length = " + f1.length());
        System.out.println("File2 Length = " + f2.length());
        System.out.println("File3 Length = " + f3.length());

        mPost.addParameter(f1.getName(), f1);
        mPost.addParameter(f2.getName(), f2);
        mPost.addParameter(f3.getName(), f3);

        //int statusCode1 = client.executeMethod(mPost);

        System.out.println("statusLine>>>" + mPost.getStatusLine());


        //打印结果页面
        String response = new String(mPost.getResponseBodyAsString().getBytes("8859_1"));
        //打印返回的信息
        System.out.println("===================================");
        System.out.println("返回的求内容:");
        System.out.println(response);
        System.out.println("===================================");


        mPost.releaseConnection();
    }
}

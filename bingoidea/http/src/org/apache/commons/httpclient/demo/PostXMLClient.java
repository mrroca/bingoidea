package org.apache.commons.httpclient.demo;

import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 5． 提交XML格式参数

 提交XML格式的参数很简单，仅仅是一个提交时候的ContentType问题，下面的例子演示从文件文件中读取XML信息并提交给服务器的过程，该过程可以用来测试Web服务。

 * 用来演示提交XML格式数据的例子
*/

public class PostXMLClient {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {

        File input = new File("test.xml");
        PostMethod post = new PostMethod("http://90.0.12.20:8088/NationWideAdmin/test/PostXMLClient.jsp");

        // 设置请求的内容直接从文件中读取
        post.setRequestBody(new FileInputStream(input));

        if (input.length() < Integer.MAX_VALUE) {
            post.setRequestContentLength(input.length());
        }else {
            post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
        }
        // 指定请求内容的类型

        post.setRequestHeader("Content-type", "text/xml; charset=GBK");

        HttpClient httpclient = new HttpClient();
        int result = httpclient.executeMethod(post);
        System.out.println("Response status code: " + result);
        System.out.println("Response body: ");
        System.out.println(post.getResponseBodyAsString());

        post.releaseConnection();
    }

}

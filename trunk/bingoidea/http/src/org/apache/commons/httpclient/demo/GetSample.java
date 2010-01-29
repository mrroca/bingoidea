package org.apache.commons.httpclient.demo;

import java.io.*;
import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

//利用Apache的HttpClient实现http方式的文件下载

/*
 HttpClient 是 Apache Jakarta Common 下的子项目，大家只要到百度搜索“HttpClient 入门”，会有很多文章来介绍HttpClient 的。在这我就不多说了，下面把所需要的一一罗列，在此只是简单的实现，具体可参看Apache 官方说明，或者与我一起讨论。

 点击下载后解压即可获得所需要的3个jar包：
 1、commons-httpclient-3.0.1.jar
 2、commons-logging-1.1.jar
 3、commons-codec-1.3.jar

 本例为获得百度主页的信息并将其生成Test_baidu.html。
当然也可以直接获得文件
*/

public class GetSample {
    public static void main(String[] args) {
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //设置代理服务器地址和端口
        //httpClient.getHostConfiguration().setProxy("90.0.12.21",808);
        //创建GET方法的实例
        GetMethod getMethod = new GetMethod("http://www.baidu.com");
        //GetMethod getMethod = new GetMethod("http://10.164.80.52/dav/5000/moban.rar");
        //使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + getMethod.getStatusLine());
            }
            //读取内容
            byte[] responseBody = getMethod.getResponseBody();

            String serverfile = "Test_baidu.html";
            //String serverfile = "d:\\moban.rar";
            OutputStream serverout = new FileOutputStream(serverfile);

            serverout.write(responseBody);
            serverout.flush();
            serverout.close();

            //处理内容
            //System.out.println(new String(responseBody));
            System.out.println("OK!");
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            getMethod.releaseConnection();
        }
    }
}

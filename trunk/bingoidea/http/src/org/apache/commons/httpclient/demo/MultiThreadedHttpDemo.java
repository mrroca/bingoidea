package org.apache.commons.httpclient.demo;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

/*
8． 多线程模式下使用httpclient

多线程同时访问httpclient，例如同时从一个站点上下载多个文件。对于同一个HttpConnection同一个时间只能有一个线程访问，为了保证多线程工作环境下不产生冲突，httpclient使用了一个多线程连接管理器的类：MultiThreadedHttpConnectionManager，要使用这个类很简单，只需要在构造HttpClient实例的时候传入即可，代码如下：
MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
HttpClient client = new HttpClient(connectionManager);

以后尽管访问client实例即可。
*/

public class MultiThreadedHttpDemo {
    public static void main(String[] args) {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient client = new HttpClient(connectionManager);
        //设置代理服务器地址和端口
        //client.getHostConfiguration().setProxy("90.0.12.21",808);
        //使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
        HttpMethod method = new GetMethod("http://java.sun.com");
        //使用POST方法
        //HttpMethod method = new PostMethod("http://java.sun.com");
        try {
            client.executeMethod(method);
        } catch (IOException ex) {
        }
        //打印服务器返回的状态
        System.out.println("===================================");
        System.out.println("返回的状态：");
        System.out.println(method.getStatusLine());
        System.out.println("===================================");
        //打印返回的信息
        System.out.println("===================================");
        System.out.println("返回的求内容:");
        try {
            System.out.println(method.getResponseBodyAsString());
        } catch (IOException ex1) {
        }
        System.out.println("===================================");
        //释放连接
        method.releaseConnection();
    }
}

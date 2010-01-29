package org.apache.commons.httpclient.demo;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

/*
8�� ���߳�ģʽ��ʹ��httpclient

���߳�ͬʱ����httpclient������ͬʱ��һ��վ�������ض���ļ�������ͬһ��HttpConnectionͬһ��ʱ��ֻ����һ���̷߳��ʣ�Ϊ�˱�֤���̹߳��������²�������ͻ��httpclientʹ����һ�����߳����ӹ��������ࣺMultiThreadedHttpConnectionManager��Ҫʹ�������ܼ򵥣�ֻ��Ҫ�ڹ���HttpClientʵ����ʱ���뼴�ɣ��������£�
MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
HttpClient client = new HttpClient(connectionManager);

�Ժ󾡹ܷ���clientʵ�����ɡ�
*/

public class MultiThreadedHttpDemo {
    public static void main(String[] args) {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient client = new HttpClient(connectionManager);
        //���ô����������ַ�Ͷ˿�
        //client.getHostConfiguration().setProxy("90.0.12.21",808);
        //ʹ��GET�����������������Ҫͨ��HTTPS���ӣ���ֻ��Ҫ������URL�е�http����https
        HttpMethod method = new GetMethod("http://java.sun.com");
        //ʹ��POST����
        //HttpMethod method = new PostMethod("http://java.sun.com");
        try {
            client.executeMethod(method);
        } catch (IOException ex) {
        }
        //��ӡ���������ص�״̬
        System.out.println("===================================");
        System.out.println("���ص�״̬��");
        System.out.println(method.getStatusLine());
        System.out.println("===================================");
        //��ӡ���ص���Ϣ
        System.out.println("===================================");
        System.out.println("���ص�������:");
        try {
            System.out.println(method.getResponseBodyAsString());
        } catch (IOException ex1) {
        }
        System.out.println("===================================");
        //�ͷ�����
        method.releaseConnection();
    }
}

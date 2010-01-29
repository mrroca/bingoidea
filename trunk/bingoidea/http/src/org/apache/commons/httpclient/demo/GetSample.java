package org.apache.commons.httpclient.demo;

import java.io.*;
import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

//����Apache��HttpClientʵ��http��ʽ���ļ�����

/*
 HttpClient �� Apache Jakarta Common �µ�����Ŀ�����ֻҪ���ٶ�������HttpClient ���š������кܶ�����������HttpClient �ġ������ҾͲ���˵�ˣ����������Ҫ��һһ���У��ڴ�ֻ�Ǽ򵥵�ʵ�֣�����ɲο�Apache �ٷ�˵������������һ�����ۡ�

 ������غ��ѹ���ɻ������Ҫ��3��jar����
 1��commons-httpclient-3.0.1.jar
 2��commons-logging-1.1.jar
 3��commons-codec-1.3.jar

 ����Ϊ��ðٶ���ҳ����Ϣ����������Test_baidu.html��
��ȻҲ����ֱ�ӻ���ļ�
*/

public class GetSample {
    public static void main(String[] args) {
        //����HttpClient��ʵ��
        HttpClient httpClient = new HttpClient();
        //���ô����������ַ�Ͷ˿�
        //httpClient.getHostConfiguration().setProxy("90.0.12.21",808);
        //����GET������ʵ��
        GetMethod getMethod = new GetMethod("http://www.baidu.com");
        //GetMethod getMethod = new GetMethod("http://10.164.80.52/dav/5000/moban.rar");
        //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        try {
            //ִ��getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + getMethod.getStatusLine());
            }
            //��ȡ����
            byte[] responseBody = getMethod.getResponseBody();

            String serverfile = "Test_baidu.html";
            //String serverfile = "d:\\moban.rar";
            OutputStream serverout = new FileOutputStream(serverfile);

            serverout.write(responseBody);
            serverout.flush();
            serverout.close();

            //��������
            //System.out.println(new String(responseBody));
            System.out.println("OK!");
        } catch (HttpException e) {
            //�����������쳣��������Э�鲻�Ի��߷��ص�����������
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //���������쳣
            e.printStackTrace();
        } finally {
            //�ͷ�����
            getMethod.releaseConnection();
        }
    }
}

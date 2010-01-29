package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import java.io.*;

public class GetOtherUrlDate {
    public static void main(String[] args) {
        //GetOtherUrlDate getotherurldate = new GetOtherUrlDate();
        HttpClient hc = new HttpClient();
        //���ô����������ַ�Ͷ˿�
        //hc.getHostConfiguration().setProxy("90.0.12.21",808);

        HttpMethod hm = new GetMethod("http://www.sina.com.cn");
        hm.addRequestHeader("Content-Type", "text/html;charset=utf-8"); //���������ַ����룬��������

        int statusCode = -1;
        byte[] result = null;

        try {
            statusCode = hc.executeMethod(hm);
            if (statusCode != HttpStatus.SC_OK) { //�жϷ���
                System.out.println("get failure!");
                return;
            }

            if (hm.getResponseBody() != null) { //��ȡҳ������
                result = hm.getResponseBody(); //hm.getStatusLine()�D�Dhttp״̬��������
            }

        } catch (HttpException e1) {
            e1.printStackTrace();
        } catch (java.io.IOException e2) {
            e2.printStackTrace();
        }

        hm.releaseConnection();
        String data = null;

        if (result != null) {
            try {
                //data = new String(result, "UTF-8"); //�ַ���������
                data = new String(result, "GB2312"); //�ַ���������
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            //System.out.println(data.substring(0, 500)); //�������
            int begin = data.indexOf("product"); //��ȡָ��ҳ��ĳ�����ֵ�����

            System.out.println("===============================");
            System.out.println("product:" + begin);
            System.out.println("===============================");

            if (begin > -1) { //���1000���ַ�
                System.out.println(data.substring(begin,begin + 1000));
                //System.out.println(Strings.convertHTML(data.substring(begin,begin + 1000)));
            }
        }
    }
}

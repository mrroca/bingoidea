package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import java.io.*;

public class GetOtherUrlDate {
    public static void main(String[] args) {
        //GetOtherUrlDate getotherurldate = new GetOtherUrlDate();
        HttpClient hc = new HttpClient();
        //设置代理服务器地址和端口
        //hc.getHostConfiguration().setProxy("90.0.12.21",808);

        HttpMethod hm = new GetMethod("http://www.sina.com.cn");
        hm.addRequestHeader("Content-Type", "text/html;charset=utf-8"); //这里设置字符编码，避免乱码

        int statusCode = -1;
        byte[] result = null;

        try {
            statusCode = hc.executeMethod(hm);
            if (statusCode != HttpStatus.SC_OK) { //判断返回
                System.out.println("get failure!");
                return;
            }

            if (hm.getResponseBody() != null) { //获取页面数据
                result = hm.getResponseBody(); //hm.getStatusLine()――http状态和请求结果
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
                //data = new String(result, "UTF-8"); //字符编码设置
                data = new String(result, "GB2312"); //字符编码设置
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            //System.out.println(data.substring(0, 500)); //测试输出
            int begin = data.indexOf("product"); //获取指定页面某个部分的内容

            System.out.println("===============================");
            System.out.println("product:" + begin);
            System.out.println("===============================");

            if (begin > -1) { //输出1000个字符
                System.out.println(data.substring(begin,begin + 1000));
                //System.out.println(Strings.convertHTML(data.substring(begin,begin + 1000)));
            }
        }
    }
}

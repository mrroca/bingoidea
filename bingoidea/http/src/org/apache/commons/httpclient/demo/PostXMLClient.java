package org.apache.commons.httpclient.demo;

import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 5�� �ύXML��ʽ����

 �ύXML��ʽ�Ĳ����ܼ򵥣�������һ���ύʱ���ContentType���⣬�����������ʾ���ļ��ļ��ж�ȡXML��Ϣ���ύ���������Ĺ��̣��ù��̿�����������Web����

 * ������ʾ�ύXML��ʽ���ݵ�����
*/

public class PostXMLClient {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {

        File input = new File("test.xml");
        PostMethod post = new PostMethod("http://90.0.12.20:8088/NationWideAdmin/test/PostXMLClient.jsp");

        // �������������ֱ�Ӵ��ļ��ж�ȡ
        post.setRequestBody(new FileInputStream(input));

        if (input.length() < Integer.MAX_VALUE) {
            post.setRequestContentLength(input.length());
        }else {
            post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
        }
        // ָ���������ݵ�����

        post.setRequestHeader("Content-type", "text/xml; charset=GBK");

        HttpClient httpclient = new HttpClient();
        int result = httpclient.executeMethod(post);
        System.out.println("Response status code: " + result);
        System.out.println("Response body: ");
        System.out.println(post.getResponseBodyAsString());

        post.releaseConnection();
    }

}

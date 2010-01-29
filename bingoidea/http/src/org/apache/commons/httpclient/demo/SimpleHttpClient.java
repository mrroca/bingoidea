package org.apache.commons.httpclient.demo;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

/*
 2�� ��GET����POST��ʽ����ҳ�ύ����

 ��ʵǰ��һ����򵥵�ʾ���������Ѿ����������ʹ��GET����POST��ʽ������һ��ҳ�棬��С����֮��ͬ���Ƕ����ύʱ�趨ҳ������Ĳ���������֪�������GET������ʽ����ô���в�����ֱ�ӷŵ�ҳ���URL�������ʺ���ҳ���ַ������ÿ��������&���������磺http://java.sun.com?name=liudong&mobile=123456�����ǵ�ʹ��POST����ʱ�ͻ���΢��һ����鷳����С�ڵ�������ʾ����β�ѯ�ֻ��������ڵĳ���

*/

/**
 * �ύ������ʾ
 * �ó������ӵ�һ�����ڲ�ѯ�ֻ����������ص�ҳ��
 * �Ա��ѯ�����1330227���ڵ�ʡ���Լ�����
 * @author Liudong
 */

public class SimpleHttpClient {

    public static void main(String[] args) throws IOException{

        HttpClient client = new HttpClient();
        //���ô����������ַ�Ͷ˿�
        //client.getHostConfiguration().setProxy("90.0.12.21",808);

        client.getHostConfiguration().setHost("www.imobile.com.cn", 80, "http");
        HttpMethod method = getPostMethod(); //ʹ��POST��ʽ�ύ����
        //HttpMethod method = getPostMethod(); //ʹ��GET��ʽ�ύ����
        client.executeMethod(method);

        //��ӡ���������ص�״̬
        System.out.println("===================================");
        System.out.println("���ص�״̬��");
        System.out.println(method.getStatusLine());
        System.out.println("===================================");

        //��ӡ���ҳ��
        String response = new String(method.getResponseBodyAsString().getBytes("8859_1"));
        //��ӡ���ص���Ϣ
        System.out.println("===================================");
        System.out.println("���ص�������:");
        System.out.println(response);
        System.out.println("===================================");

        method.releaseConnection();
    }

    /**
     * ʹ��POST��ʽ�ύ����
     * @return
     */
    private static HttpMethod getPostMethod() {
        PostMethod post = new PostMethod("/search2005.php");
        NameValuePair simcard = new NameValuePair("searchkeyword", "1330227");
        post.setRequestBody(new NameValuePair[] {simcard});
        return post;
    }
}

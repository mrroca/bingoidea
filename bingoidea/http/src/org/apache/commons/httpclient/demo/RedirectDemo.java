package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.GetMethod;
import java.io.*;

/*
 3�� ����ҳ���ض���

 ��JSP/Servlet�����response.sendRedirect��������ʹ��HTTPЭ���е��ض�����ơ�����JSP�е�<jsp:forward ��>���������ں������ڷ�������ʵ��ҳ�����ת��Ҳ����˵Ӧ��������������Ҫ��ת��ҳ������ݲ����ظ��ͻ��ˣ���ǰ���Ƿ���һ��״̬�룬��Щ״̬��Ŀ���ֵ���±�Ȼ��ͻ��˶�ȡ��Ҫ��ת����ҳ���URL�����¼����µ�ҳ�档��������һ�����̣��������Ǳ�̵�ʱ���Ҫͨ��HttpMethod.getStatusCode()�����жϷ���ֵ�Ƿ�Ϊ�±��е�ĳ��ֵ���ж��Ƿ���Ҫ��ת������Ѿ�ȷ����Ҫ����ҳ����ת�ˣ���ô����ͨ����ȡHTTPͷ�е�location��������ȡ�µĵ�ַ��

 ״̬��
  ��ӦHttpServletResponse�ĳ���
  ��ϸ����

 301
  SC_MOVED_PERMANENTLY
  ҳ���Ѿ������Ƶ�����һ���µ�ַ

 302
  SC_MOVED_TEMPORARILY
  ҳ����ʱ�ƶ�������һ���µĵ�ַ

 303
  SC_SEE_OTHER
  �ͻ�������ĵ�ַ����ͨ�������URL������

 307
  SC_TEMPORARY_REDIRECT
  ͬSC_MOVED_TEMPORARILY


*/

public class RedirectDemo {
    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        //���ô����������ַ�Ͷ˿�
        //client.getHostConfiguration().setProxy("90.0.12.21", 808);
        //client.getHostConfiguration().setHost("www.imobile.com.cn", 80, "http");

        PostMethod post = new PostMethod("http://90.0.12.20:8088/NationWideAdmin/test/RedirectDemo.jsp");

        try {
            client.executeMethod(post);
        } catch (IOException ex) {
        }

        System.out.println(post.getStatusLine().toString());

        post.releaseConnection();

        //����Ƿ��ض���

        int statuscode = post.getStatusCode();
/*
         ״̬��
          ��ӦHttpServletResponse�ĳ���
          ��ϸ����

         301
          SC_MOVED_PERMANENTLY
          ҳ���Ѿ������Ƶ�����һ���µ�ַ

         302
          SC_MOVED_TEMPORARILY
          ҳ����ʱ�ƶ�������һ���µĵ�ַ

         303
          SC_SEE_OTHER
          �ͻ�������ĵ�ַ����ͨ�������URL������

         307
          SC_TEMPORARY_REDIRECT
          ͬSC_MOVED_TEMPORARILY
*/
        System.out.println("����Ƿ��ض���Ĵ��룺"+statuscode);
        System.out.println("HttpStatus.SC_MOVED_TEMPORARILY��"+HttpStatus.SC_MOVED_TEMPORARILY);
        System.out.println("HttpStatus.SC_MOVED_PERMANENTLY��"+HttpStatus.SC_MOVED_PERMANENTLY);
        System.out.println("HttpStatus.SC_SEE_OTHER��"+HttpStatus.SC_SEE_OTHER);
        System.out.println("HttpStatus.SC_TEMPORARY_REDIRECT��"+HttpStatus.SC_TEMPORARY_REDIRECT);
        if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) ||
            (statuscode == HttpStatus.SC_MOVED_PERMANENTLY) ||
            (statuscode == HttpStatus.SC_SEE_OTHER) ||
            (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {

            //��ȡ�µ�URL��ַ
            Header header = post.getResponseHeader("location");
            if (header != null) {
                String newuri = header.getValue();
                if ((newuri == null) || (newuri.equals(""))) {
                    newuri = "/";
                }

                GetMethod redirect = new GetMethod(newuri);

                try {
                    client.executeMethod(redirect);
                } catch (IOException ex1) {
                }

                System.out.println("Redirect:" +redirect.getStatusLine().toString());

                redirect.releaseConnection();
            } else {
                System.out.println("Invalid redirect");
            }
        }
    }
}

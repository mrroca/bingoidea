package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.*;
import org.apache.commons.httpclient.methods.*;

/*
 4�� ģ�������û����Ϳ�����е�¼

 ��С��Ӧ��˵��HTTP�ͻ��˱��������������⣬�ܶ���վ�����ݶ�ֻ�Ƕ�ע���û��ɼ��ģ���������¾ͱ���Ҫ��ʹ����ȷ���û����Ϳ����¼�ɹ��󣬷����������Ҫ��ҳ�档��ΪHTTPЭ������״̬�ģ�Ҳ�������ӵ���Ч��ֻ���ڵ�ǰ�����������ݽ��������Ӿ͹ر��ˡ������������Ϊ�˱����û��ĵ�¼��Ϣ����ʹ�õ�Cookie���ơ���JSP/ServletΪ���������������һ��JSP������Servlet��ҳ��ʱ��Ӧ�÷������᷵��һ����������Ϊjsessionid����ͬӦ�÷��������죩��ֵ��һ���ϳ���Ψһ�ַ�����Cookie������ַ���ֵҲ���ǵ�ǰ���ʸ�վ��ĻỰ��ʶ���������ÿ���ʸ�վ�������ҳ��ʱ��Ҫ����jsessionid������Cookie��Ϣ��Ӧ�÷��������ݶ�ȡ����Ự��ʶ����ȡ��Ӧ�ĻỰ��Ϣ��
 ������Ҫ�û���¼����վ��һ�����û���¼�ɹ���Ὣ�û����ϱ����ڷ������ĻỰ�У����������ʵ�������ҳ��ʱ��Ӧ�÷�����������������ϵ�Cookie�ж�ȡ��ǰ�����Ӧ�ĻỰ��ʶ�Ի�ö�Ӧ�ĻỰ��Ϣ��Ȼ��Ϳ����ж��û������Ƿ�����ڻỰ��Ϣ�У�����������������ҳ�棬������ת����¼ҳ����Ҫ���û������ʺźͿ�����е�¼�������һ��ʹ��JSP������վ�ڴ����û���¼�ıȽ�ͨ�õķ�����
 ����һ��������HTTP�Ŀͻ������������Ҫ����һ���ܱ�����ҳ��ʱ�ͱ���ģ������������Ĺ��������Ⱦ��������¼ҳ�棬Ȼ���ȡCookieֵ���ٴ������¼ҳ�沢�����¼ҳ�����ÿ���������������������������ҳ�档��Ȼ�ڳ���һ��������������������Ҫ������Cookie��Ϣ�Ա���������жϵ�ǰ�����Ƿ��Ѿ�ͨ����֤��˵����ô�࣬���������ʹ��httpclient�Ļ�����������һ�д��붼�������ӣ���ֻ��Ҫ�ȴ��ݵ�¼��Ϣִ�е�¼���̣�Ȼ��ֱ�ӷ�����Ҫ��ҳ�棬������һ����ͨ��ҳ��û���κ�������Ϊ��HttpClient�Ѿ������������и����������ˣ�̫���ˣ����������ʵ��������һ�����ʵĹ��̡�
*/

/**
 * ������ʾ��¼����ʾ��
 * @author Liudong
 */

public class FormLoginDemo {

    static final String LOGON_SITE = "90.0.12.20";
    static final int LOGON_PORT = 8088;

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);

        //ģ���¼ҳ��login.jsp->main.jsp

        PostMethod post = new PostMethod("/NationWideAdmin/test/FormLoginDemo.jsp");

        NameValuePair name = new NameValuePair("name", "wsq");
        NameValuePair pass = new NameValuePair("password", "1");
        post.setRequestBody(new NameValuePair[] {name, pass});

        //int status = client.executeMethod(post);
        System.out.println(post.getResponseBodyAsString());

        post.releaseConnection();

        //�鿴cookie��Ϣ

        CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
        Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/NationWideAdmin/test/", false,client.getState().getCookies());

        if (cookies.length == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.length; i++) {
                System.out.println(cookies[i].toString());
            }
        }

        //���������ҳ��main2.jsp

        GetMethod get = new GetMethod("/NationWideAdmin/test/FormLoginDemo1.jsp");
        client.executeMethod(get);
        //System.out.println(get.getResponseBodyAsString());
        //��ӡ���ҳ��
        String response = new String(get.getResponseBodyAsString().getBytes("8859_1"));
        //��ӡ���ص���Ϣ
        System.out.println("===================================");
        System.out.println("���ص�������:");
        System.out.println(response);
        System.out.println("===================================");

        get.releaseConnection();
    }
}

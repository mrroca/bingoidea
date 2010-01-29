package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;

/*
 7�� ����������֤��ҳ��

 ���Ǿ���������������ҳ�棬����������ʱ��ᵯ��һ��������ĶԻ���Ҫ�������û���������󷽿ɣ������û���֤�ķ�ʽ��ͬ��������ǰ����ܵĻ��ڱ����û������֤������HTTP����֤���ԣ�httpclient֧��������֤��ʽ������������ժҪ�Լ�NTLM��֤�����л�����֤��򵥡�ͨ�õ�Ҳ���ȫ��ժҪ��֤����HTTP 1.1�м������֤��ʽ����NTLM����΢��˾����Ķ�����ͨ�õĹ淶�����°汾��NTLM�Ǳ�ժҪ��֤��Ҫ��ȫ��һ�ַ�ʽ��

*/

public class BasicAuthenticationDemo {

    public BasicAuthenticationDemo() {

    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {

        HttpClient client = new HttpClient();
        //���ô����������ַ�Ͷ˿�
        //client.getHostConfiguration().setProxy("90.0.12.21",808);

        client.getState().setCredentials(
                "www.verisign.com",
                "realm",
                new UsernamePasswordCredentials("username", "password")
                );

        GetMethod get = new GetMethod("https://www.verisign.com/products/index.html");
        get.setDoAuthentication(true);

        int status = client.executeMethod(get);
        System.out.println(status + "" + get.getResponseBodyAsString());
        get.releaseConnection();
    }
}

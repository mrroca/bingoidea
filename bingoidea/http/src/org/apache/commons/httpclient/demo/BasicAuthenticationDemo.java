package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;

/*
 7． 访问启用认证的页面

 我们经常会碰到这样的页面，当访问它的时候会弹出一个浏览器的对话框要求输入用户名和密码后方可，这种用户认证的方式不同于我们在前面介绍的基于表单的用户身份验证。这是HTTP的认证策略，httpclient支持三种认证方式包括：基本、摘要以及NTLM认证。其中基本认证最简单、通用但也最不安全；摘要认证是在HTTP 1.1中加入的认证方式，而NTLM则是微软公司定义的而不是通用的规范，最新版本的NTLM是比摘要认证还要安全的一种方式。

*/

public class BasicAuthenticationDemo {

    public BasicAuthenticationDemo() {

    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {

        HttpClient client = new HttpClient();
        //设置代理服务器地址和端口
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

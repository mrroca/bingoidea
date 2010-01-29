package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.*;
import org.apache.commons.httpclient.methods.*;

/*
 4． 模拟输入用户名和口令进行登录

 本小节应该说是HTTP客户端编程中最常碰见的问题，很多网站的内容都只是对注册用户可见的，这种情况下就必须要求使用正确的用户名和口令登录成功后，方可浏览到想要的页面。因为HTTP协议是无状态的，也就是连接的有效期只限于当前请求，请求内容结束后连接就关闭了。在这种情况下为了保存用户的登录信息必须使用到Cookie机制。以JSP/Servlet为例，当浏览器请求一个JSP或者是Servlet的页面时，应用服务器会返回一个参数，名为jsessionid（因不同应用服务器而异），值是一个较长的唯一字符串的Cookie，这个字符串值也就是当前访问该站点的会话标识。浏览器在每访问该站点的其他页面时候都要带上jsessionid这样的Cookie信息，应用服务器根据读取这个会话标识来获取对应的会话信息。
 对于需要用户登录的网站，一般在用户登录成功后会将用户资料保存在服务器的会话中，这样当访问到其他的页面时候，应用服务器根据浏览器送上的Cookie中读取当前请求对应的会话标识以获得对应的会话信息，然后就可以判断用户资料是否存在于会话信息中，如果存在则允许访问页面，否则跳转到登录页面中要求用户输入帐号和口令进行登录。这就是一般使用JSP开发网站在处理用户登录的比较通用的方法。
 这样一来，对于HTTP的客户端来讲，如果要访问一个受保护的页面时就必须模拟浏览器所做的工作，首先就是请求登录页面，然后读取Cookie值；再次请求登录页面并加入登录页所需的每个参数；最后就是请求最终所需的页面。当然在除第一次请求外其他的请求都需要附带上Cookie信息以便服务器能判断当前请求是否已经通过验证。说了这么多，可是如果你使用httpclient的话，你甚至连一行代码都无需增加，你只需要先传递登录信息执行登录过程，然后直接访问想要的页面，跟访问一个普通的页面没有任何区别，因为类HttpClient已经帮你做了所有该做的事情了，太棒了！下面的例子实现了这样一个访问的过程。
*/

/**
 * 用来演示登录表单的示例
 * @author Liudong
 */

public class FormLoginDemo {

    static final String LOGON_SITE = "90.0.12.20";
    static final int LOGON_PORT = 8088;

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);

        //模拟登录页面login.jsp->main.jsp

        PostMethod post = new PostMethod("/NationWideAdmin/test/FormLoginDemo.jsp");

        NameValuePair name = new NameValuePair("name", "wsq");
        NameValuePair pass = new NameValuePair("password", "1");
        post.setRequestBody(new NameValuePair[] {name, pass});

        //int status = client.executeMethod(post);
        System.out.println(post.getResponseBodyAsString());

        post.releaseConnection();

        //查看cookie信息

        CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
        Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/NationWideAdmin/test/", false,client.getState().getCookies());

        if (cookies.length == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.length; i++) {
                System.out.println(cookies[i].toString());
            }
        }

        //访问所需的页面main2.jsp

        GetMethod get = new GetMethod("/NationWideAdmin/test/FormLoginDemo1.jsp");
        client.executeMethod(get);
        //System.out.println(get.getResponseBodyAsString());
        //打印结果页面
        String response = new String(get.getResponseBodyAsString().getBytes("8859_1"));
        //打印返回的信息
        System.out.println("===================================");
        System.out.println("返回的求内容:");
        System.out.println(response);
        System.out.println("===================================");

        get.releaseConnection();
    }
}

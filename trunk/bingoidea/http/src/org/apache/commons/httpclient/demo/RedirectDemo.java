package org.apache.commons.httpclient.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.GetMethod;
import java.io.*;

/*
 3． 处理页面重定向

 在JSP/Servlet编程中response.sendRedirect方法就是使用HTTP协议中的重定向机制。它与JSP中的<jsp:forward …>的区别在于后者是在服务器中实现页面的跳转，也就是说应用容器加载了所要跳转的页面的内容并返回给客户端；而前者是返回一个状态码，这些状态码的可能值见下表，然后客户端读取需要跳转到的页面的URL并重新加载新的页面。就是这样一个过程，所以我们编程的时候就要通过HttpMethod.getStatusCode()方法判断返回值是否为下表中的某个值来判断是否需要跳转。如果已经确认需要进行页面跳转了，那么可以通过读取HTTP头中的location属性来获取新的地址。

 状态码
  对应HttpServletResponse的常量
  详细描述

 301
  SC_MOVED_PERMANENTLY
  页面已经永久移到另外一个新地址

 302
  SC_MOVED_TEMPORARILY
  页面暂时移动到另外一个新的地址

 303
  SC_SEE_OTHER
  客户端请求的地址必须通过另外的URL来访问

 307
  SC_TEMPORARY_REDIRECT
  同SC_MOVED_TEMPORARILY


*/

public class RedirectDemo {
    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        //设置代理服务器地址和端口
        //client.getHostConfiguration().setProxy("90.0.12.21", 808);
        //client.getHostConfiguration().setHost("www.imobile.com.cn", 80, "http");

        PostMethod post = new PostMethod("http://90.0.12.20:8088/NationWideAdmin/test/RedirectDemo.jsp");

        try {
            client.executeMethod(post);
        } catch (IOException ex) {
        }

        System.out.println(post.getStatusLine().toString());

        post.releaseConnection();

        //检查是否重定向

        int statuscode = post.getStatusCode();
/*
         状态码
          对应HttpServletResponse的常量
          详细描述

         301
          SC_MOVED_PERMANENTLY
          页面已经永久移到另外一个新地址

         302
          SC_MOVED_TEMPORARILY
          页面暂时移动到另外一个新的地址

         303
          SC_SEE_OTHER
          客户端请求的地址必须通过另外的URL来访问

         307
          SC_TEMPORARY_REDIRECT
          同SC_MOVED_TEMPORARILY
*/
        System.out.println("检查是否重定向的代码："+statuscode);
        System.out.println("HttpStatus.SC_MOVED_TEMPORARILY："+HttpStatus.SC_MOVED_TEMPORARILY);
        System.out.println("HttpStatus.SC_MOVED_PERMANENTLY："+HttpStatus.SC_MOVED_PERMANENTLY);
        System.out.println("HttpStatus.SC_SEE_OTHER："+HttpStatus.SC_SEE_OTHER);
        System.out.println("HttpStatus.SC_TEMPORARY_REDIRECT："+HttpStatus.SC_TEMPORARY_REDIRECT);
        if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) ||
            (statuscode == HttpStatus.SC_MOVED_PERMANENTLY) ||
            (statuscode == HttpStatus.SC_SEE_OTHER) ||
            (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {

            //读取新的URL地址
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

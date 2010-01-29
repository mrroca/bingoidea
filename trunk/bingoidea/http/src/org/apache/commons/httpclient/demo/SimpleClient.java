package org.apache.commons.httpclient.demo;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

/*
 一般的情况下我们都是使用IE或者Navigator浏览器来访问一个WEB服务器，用来浏览页面查看信息或者提交一些数据等等。所访问的这些页面有的仅仅是一些普通的页面，有的需要用户登录后方可使用，或者需要认证以及是一些通过加密方式传输，例如HTTPS。目前我们使用的浏览器处理这些情况都不会构成问题。不过你可能在某些时候需要通过程序来访问这样的一些页面，比如从别人的网页中“偷”一些数据；利用某些站点提供的页面来完成某种功能，例如说我们想知道某个手机号码的归属地而我们自己又没有这样的数据，因此只好借助其他公司已有的网站来完成这个功能，这个时候我们需要向网页提交手机号码并从返回的页面中解析出我们想要的数据来。如果对方仅仅是一个很简单的页面，那我们的程序会很简单，本文也就没有必要大张旗鼓的在这里浪费口舌。但是考虑到一些服务授权的问题，很多公司提供的页面往往并不是可以通过一个简单的URL就可以访问的，而必须经过注册然后登录后方可使用提供服务的页面，这个时候就涉及到COOKIE问题的处理。我们知道目前流行的***页技术例如ASP、JSP无不是通过COOKIE来处理会话信息的。为了使我们的程序能使用别人所提供的服务页面，就要求程序首先登录后再访问服务页面，这过程就需要自行处理cookie，想想当你用java.net.HttpURLConnection来完成这些功能时是多么恐怖的事情啊！况且这仅仅是我们所说的顽固的WEB服务器中的一个很常见的“顽固”！再有如通过HTTP来上传文件呢？不需要头疼，这些问题有了“它”就很容易解决了！

 我们不可能列举所有可能的顽固，我们会针对几种最常见的问题进行处理。当然了，正如前面说到的，如果我们自己使用java.net.HttpURLConnection来搞定这些问题是很恐怖的事情，因此在开始之前我们先要介绍一下一个开放源码的项目，这个项目就是Apache开源组织中的httpclient，它隶属于Jakarta的commons项目，目前的版本是2.0RC2。commons下本来已经有一个net的子项目，但是又把httpclient单独提出来，可见http服务器的访问绝非易事。

 Commons-httpclient项目就是专门设计来简化HTTP客户端与服务器进行各种通讯编程。通过它可以让原来很头疼的事情现在轻松的解决，例如你不再管是HTTP或者HTTPS的通讯方式，告诉它你想使用HTTPS方式，剩下的事情交给httpclient替你完成。本文会针对我们在编写HTTP客户端程序时经常碰到的几个问题进行分别介绍如何使用httpclient来解决它们，为了让读者更快的熟悉这个项目我们最开始先给出一个简单的例子来读取一个网页的内容，然后循序渐进解决掉前进中的所形侍狻?/font>

 1． 读取网页(HTTP/HTTPS)内容

 下面是我们给出的一个简单的例子用来访问某个页面


*/

/**
 * 最简单的HTTP客户端,用来演示通过GET或者POST方式访问某个页面
 * @author Liudong
 */

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();
        //设置代理服务器地址和端口
        client.getHostConfiguration().setProxy("90.0.12.21",808);
        //使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
        HttpMethod method = new GetMethod("http://java.sun.com");
        //使用POST方法
        //HttpMethod method = new PostMethod("http://java.sun.com");
        client.executeMethod(method);
        //打印服务器返回的状态
        System.out.println("===================================");
        System.out.println("返回的状态：");
        System.out.println(method.getStatusLine());
        System.out.println("===================================");
        //打印返回的信息
        System.out.println("===================================");
        System.out.println("返回的求内容:");
        System.out.println(method.getResponseBodyAsString());
        System.out.println("===================================");
        //释放连接
        method.releaseConnection();
    }
}

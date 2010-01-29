package org.apache.commons.httpclient.demo;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

/*
 2． 以GET或者POST方式向网页提交参数

 其实前面一个最简单的示例中我们已经介绍了如何使用GET或者POST方式来请求一个页面，本小节与之不同的是多了提交时设定页面所需的参数，我们知道如果是GET的请求方式，那么所有参数都直接放到页面的URL后面用问号与页面地址隔开，每个参数用&隔开，例如：http://java.sun.com?name=liudong&mobile=123456，但是当使用POST方法时就会稍微有一点点麻烦。本小节的例子演示向如何查询手机号码所在的城市

*/

/**
 * 提交参数演示
 * 该程序连接到一个用于查询手机号码所属地的页面
 * 以便查询号码段1330227所在的省份以及城市
 * @author Liudong
 */

public class SimpleHttpClient {

    public static void main(String[] args) throws IOException{

        HttpClient client = new HttpClient();
        //设置代理服务器地址和端口
        //client.getHostConfiguration().setProxy("90.0.12.21",808);

        client.getHostConfiguration().setHost("www.imobile.com.cn", 80, "http");
        HttpMethod method = getPostMethod(); //使用POST方式提交数据
        //HttpMethod method = getPostMethod(); //使用GET方式提交数据
        client.executeMethod(method);

        //打印服务器返回的状态
        System.out.println("===================================");
        System.out.println("返回的状态：");
        System.out.println(method.getStatusLine());
        System.out.println("===================================");

        //打印结果页面
        String response = new String(method.getResponseBodyAsString().getBytes("8859_1"));
        //打印返回的信息
        System.out.println("===================================");
        System.out.println("返回的求内容:");
        System.out.println(response);
        System.out.println("===================================");

        method.releaseConnection();
    }

    /**
     * 使用POST方式提交数据
     * @return
     */
    private static HttpMethod getPostMethod() {
        PostMethod post = new PostMethod("/search2005.php");
        NameValuePair simcard = new NameValuePair("searchkeyword", "1330227");
        post.setRequestBody(new NameValuePair[] {simcard});
        return post;
    }
}

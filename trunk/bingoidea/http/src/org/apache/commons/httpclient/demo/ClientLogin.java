package org.apache.commons.httpclient.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.methods.GetMethod;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

/**
 * Created by IntelliJ IDEA. User: new Date: 2007-9-13 Time: 14:05:19 To change
 * this template use File | Settings | File Templates.
 */
public class ClientLogin
{
    public static void main(String[] args)
    {
        HttpClient httpClient = new HttpClient();
        // 设置代理服务器地址和端口
        //httpClient.getHostConfiguration().setProxy("90.0.12.21", 808);

        GetMethod get_method = new GetMethod(
            "http://172.20.250.54:7123/kuandai/login_in.jsp?yhm=runwaytest&yhmm=txxlkjfd");

        try
        {
            int statusCode = httpClient.executeMethod(get_method);
            if (statusCode != HttpStatus.SC_OK)
            {
                System.err.println("Method failed: "
                        + get_method.getStatusLine());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
        finally
        {
            // Release the connection.
            get_method.releaseConnection();
        }

        // GetMethod get_method2 = new
        // GetMethod("http://172.20.250.54:7123/kuandai/doAction.jsp?action=aRouterIpStatus&param0=10.16.16.1&area=1&count=1");
        // byte[] responseBody = null;
        // try {
        // // Provide custom retry handler is necessary
        // get_method2.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
        // new DefaultHttpMethodRetryHandler(3, false));
        // int statusCode = httpClient.executeMethod(get_method2);
        //
        // if (statusCode != HttpStatus.SC_OK) {
        // System.err.println("Method failed: " + get_method2.getStatusLine());
        // }
        //
        // //这里用流来读页面
        // InputStream in = get_method2.getResponseBodyAsStream();
        // if (in != null) {
        // byte[] tmp = new byte[4096];
        // int bytesRead = 0;
        // ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024);
        // while ((bytesRead = in.read(tmp)) != -1) {
        // buffer.write(tmp, 0, bytesRead);
        // }
        // responseBody = buffer.toByteArray();
        // }
        // System.out.println(new String(responseBody));
        // } catch (IOException e) {
        // e.printStackTrace(System.out);
        // } finally {
        // get_method2.releaseConnection();
        // }
        GetMethod get_method2 = new GetMethod(
            "http://172.20.250.54:7123/kuandai/queryrstgraph.jsp?hosts=00system00&items=UA&graph=graph&Avgvalue=1&DataDate=2007-10-31");
        byte[] responseBody = null;
        try
        {
            // Provide custom retry handler is necessary
            get_method2.getParams().setParameter(
                HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
            int statusCode = httpClient.executeMethod(get_method2);

            if (statusCode != HttpStatus.SC_OK)
            {
                System.err.println("Method failed: "
                        + get_method2.getStatusLine());
            }

            // 这里用流来读页面
            InputStream in = get_method2.getResponseBodyAsStream();
            if (in != null)
            {
                byte[] tmp = new byte[4096];
                int bytesRead = 0;
                ByteArrayOutputStream buffer = new ByteArrayOutputStream(1024);
                while ((bytesRead = in.read(tmp)) != -1)
                {
                    buffer.write(tmp, 0, bytesRead);
                }
                responseBody = buffer.toByteArray();
            }
            System.out.println(new String(responseBody));
        }
        catch (IOException e)
        {
            e.printStackTrace(System.out);
        }
        finally
        {
            get_method2.releaseConnection();
        }
        try
        {
            Parser parser = Parser.createParser(
                new String(responseBody, "GBK"), "GBK");
            // String filterStr = "网关";
            // NodeFilter filter = new StringFilter(filterStr);
            // NodeList fonts = parser.extractAllNodesThatMatch(filter);
            // for (SimpleNodeIterator iter = fonts.elements();
            // iter.hasMoreNodes();) {
            // Node tag = iter.nextNode();
            // try {
            // System.out.println("tag.getText() = " + tag.getText());
            // } catch (Exception e) {
            // e.printStackTrace(System.out);
            // }
            // }

            String filterStr = "param";
            NodeFilter filter = new TagNameFilter(filterStr);
            NodeList tables = parser.extractAllNodesThatMatch(filter);
            for (SimpleNodeIterator iter = tables.elements(); iter
                .hasMoreNodes();)
            {
                Tag tag = (Tag) iter.nextNode();
                try
                {
                    // System.out.println("tag.getTagName() = " +
                    // tag.getTagName());
                    // System.out.println("tag.getAttribute(\"name\") = " +
                    // tag.getAttribute("name"));
                    // System.out.println("tag.getAttribute(\"value\") = " +
                    // tag.getAttribute("value"));
                    if (tag.getAttribute("name").equals("sampleValues_0"))
                    {
                        String v = tag.getAttribute("value");
                        String value = null;
                        if (v.endsWith(","))
                        {
                            value = v.substring(0, v.length() - 1);
                        }
                        else
                        {
                            value = v;
                        }
                        int index = value.lastIndexOf(",");
                        String total = value.substring(index + 1, value
                            .length());
                        System.out.println("total = " + total);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.out);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
}

package cn.com.wsm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class HttpClientTest
{
    public static void main(String[] args)
    {
        String soapRequestData = "<SOAP-ENV:Envelope xmlns:SOAP-ENV="
                + "\"http://schemas.xmlsoap.org/soap/envelope/"
                + "\"><SOAP-ENV:Body><ns1:sayHi xmlns:ns1="
                + "\"http://wsm.com.cn/"
                + "\"><arg0>World</arg0></ns1:sayHi></SOAP-ENV:Body></SOAP-ENV:Envelope>";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(
            "http://localhost:8080/cxffirst/HelloWorld");
        byte[] b = null;
        try
        {
            b = soapRequestData.getBytes("utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
            "application/soap+xml; charset=utf-8");
        postMethod.setRequestEntity(re);
        try
        {
            int statusCode = httpClient.executeMethod(postMethod);
            System.out.println(statusCode);
            System.out.println(postMethod.getResponseBodyAsString());
        }
        catch (HttpException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

package org.jivaro.hw;

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
        String soapRequestData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        soapRequestData+="<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        soapRequestData+="<soap:Header>";
        soapRequestData+="<AuthenticationHeader xmlns=\"http://myservice/\">";
        soapRequestData+="<UserName>WSJD</UserName>";
        soapRequestData+="<Password>WSJD2010</Password>";
        soapRequestData+="</AuthenticationHeader>";
        soapRequestData+="</soap:Header>";
        soapRequestData+="<soap:Body>";
        soapRequestData+="<SendSms xmlns=\"http://myservice/\">";
        soapRequestData+="<mobile>15150152960</mobile>";
        soapRequestData+="<content>SFASFA</content>";
        soapRequestData+="</SendSms>";
        soapRequestData+="</soap:Body>";
        soapRequestData+="</soap:Envelope>";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(
            "http://211.103.78.202/xxzxSmsp/SmsService.asmx");
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
        System.out.println(new String(b));
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

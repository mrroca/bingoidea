package cn.com.wsm.service;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class JavaServiceAnother
{
    public String doSomething(String s)
    {
        System.out.println(s);
        return "Hello " + s + " !";
    }


    public static void main(String[] args)
    {
        Endpoint.publish("http://10.41.109.41:8888/another/", new JavaServiceAnother());
    }
}

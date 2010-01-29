package cn.com.wsm.service;

import cn.com.wsm.client.JavaService;
import cn.com.wsm.client.JavaServiceService;

public class Client
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JavaService javaService = new JavaServiceService().getJavaServicePort();
        String s = javaService.doSomething("wsm");
        System.out.println(s);
    }

}

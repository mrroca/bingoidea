package cn.com.wsm;

import javax.jws.WebService;

@WebService(endpointInterface = "cn.com.wsm.HelloWorld")
public class HelloWorldImpl implements HelloWorld
{

    public String sayHi(String text)
    {
        System.out.println("text is :" + text);
        return "Hellos " + text +" !";
    }
}

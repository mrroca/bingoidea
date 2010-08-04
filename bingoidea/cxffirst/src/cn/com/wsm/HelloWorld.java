package cn.com.wsm;

import javax.jws.WebService;

@WebService
public interface HelloWorld
{
    String sayHi(String text);
}

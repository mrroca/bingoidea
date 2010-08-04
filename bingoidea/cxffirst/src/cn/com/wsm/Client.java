package cn.com.wsm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client
{

    private Client()
    {
    }


    public static void main(String args[]) throws Exception
    {
        // ClassPathResource cpr = new ClassPathResource(
        // "cn/com/wsm/client-beans.xml");
        // XmlBeanFactory xbf = new XmlBeanFactory(cpr);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "cn/com/wsm/client-beans.xml" });
        HelloWorld client = (HelloWorld) context.getBean("helloWorld");
        IVote clientvote = (IVote) context.getBean("vote");
        boolean flag = clientvote.vote("apple", 11);
        String response = client.sayHi("Joe");
        System.out.println("Response: " + flag);
        System.out.println("Response: " + response);
    }
}

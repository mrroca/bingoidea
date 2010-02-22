package cn.com.bsoft.spring.jms.send;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.jms.core.JmsTemplate;

import cn.com.bsoft.util.SystemInitImpl;

public class MessageSenderImpl
{
    private int count = 0 ;
    public void init()
    {
        SystemInitImpl.init();
    }


    public void sendMessage()
    {
        XmlBeanFactory xbf = SystemInitImpl.createFactory();
        JmsTemplate template = (JmsTemplate) xbf.getBean("jmsTemplate");
        // ActiveMQConnectionFactory connectionFactory =
        // (ActiveMQConnectionFactory) xbf.getBean("connectionFactory");
        ActiveMQConnectionFactory cf = (ActiveMQConnectionFactory) template
            .getConnectionFactory();
        Connection connection = null;
        Session session = null;
        MessageCreatorImpl mc = new MessageCreatorImpl();
        String s = "this is message " + count + "!";
        count++;
        try
        {
            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            mc.createMessage(session, s);
            template.send(mc);
        }
        catch (JMSException e)
        {
        }
        finally
        {
            try
            {
                session.close();
                connection.close();
                session = null;
                connection = null;
            }
            catch (JMSException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("success!");
    }


    public static void main(String[] args)
    {
        MessageSenderImpl msi = new MessageSenderImpl();
        msi.init();
        for (int i = 0; i <= 9; i++)
        {
            msi.sendMessage();
        }
    }
}

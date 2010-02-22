package cn.com.bsoft.spring.jms.receive;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.jms.core.JmsTemplate;

import cn.com.bsoft.util.SystemInitImpl;

public class MessageReceiverImpl
{
    public void init()
    {
        SystemInitImpl.init();
    }


    public void receiveMessage()
    {
        XmlBeanFactory xbf = SystemInitImpl.createFactory();
        JmsTemplate template = (JmsTemplate) xbf.getBean("jmsTemplate");
        while (true)
        {
            TextMessage txtmsg = (TextMessage) template.receive();
            if (null != txtmsg)
            {
                try
                {
                    System.out.println("receive message ! the message is '" + txtmsg.getText()+"'");
                }
                catch (JMSException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                break;
            }
        }
    }


    public static void main(String[] args)
    {
        MessageReceiverImpl mri = new MessageReceiverImpl();
        mri.init();
        mri.receiveMessage();
    }
}

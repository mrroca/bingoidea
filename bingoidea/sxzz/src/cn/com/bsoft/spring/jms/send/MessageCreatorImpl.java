package cn.com.bsoft.spring.jms.send;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class MessageCreatorImpl implements MessageCreator
{

    private String string;
    private Message message;


    public Message createMessage(Session session) throws JMSException
    {
        return session.createTextMessage(string);
    }


    public Message createMessage(Session session, String s) throws JMSException
    {
        this.string = s;
        this.message = createMessage(session);
        return this.message;
    }
}

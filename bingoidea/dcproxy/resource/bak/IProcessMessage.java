package cn.com.bsoft.process;

import java.net.InetAddress;

import org.dom4j.Document;

import cn.com.bsoft.model.Message;

public interface IProcessMessage
{
    public boolean decideMessage(String msg, InetAddress clientIp,
        Document mHospIp);


    public boolean saveMessage(Message mesag);


    public Message convertMessage(String msg);
}

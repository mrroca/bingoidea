package cn.com.bsoft.process;

import java.net.InetAddress;
import java.util.HashMap;

import org.dom4j.Document;

public interface IProcessMessage
{
    public boolean decideMessage(String msg, InetAddress clientIp,
        Document mHospIp);


    public boolean saveMessage(HashMap<String, String> hm);


    public HashMap<String, String> convertMessagetoHashMap(String msg);
}

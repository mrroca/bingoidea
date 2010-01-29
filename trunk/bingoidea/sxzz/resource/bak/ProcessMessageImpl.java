package cn.com.bsoft.process;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import cn.com.bsoft.dbo.PersistenceMessage;
import cn.com.bsoft.model.Message;
import cn.com.bsoft.wrap.WrappMessage;

public class ProcessMessageImpl implements IProcessMessage
{
    private Logger log = LogManager.getLogger(this.getClass());
    private Message message = null;
    private HashMap<String, String> hashmap;


    public Message convertMessage(String msg)
    {
        Document doc = null;
        try
        {
            doc = DocumentHelper.parseText(msg);
        }
        catch (DocumentException e)
        {
            log.error(e);
        }
        Element root = doc.getRootElement();
        List<?> list = root.elements();
        message = WrappMessage.wrappMessage(list);

        return message;
    }


    public HashMap<String, String> convertMessagetoHashMap(String msg)
    {
        Document doc = null;
        hashmap = new HashMap<String, String>();
        try
        {
            doc = DocumentHelper.parseText(msg);
            Element root = doc.getRootElement();
            List<?> list = root.elements();
            System.out.println(list.size());
            hashmap = WrappMessage.wrappMessagetoHashMap(list);
            System.out.println(hashmap.toString());
        }
        catch (DocumentException e)
        {
            log.error(e);
        }

        return hashmap;
    }


    public boolean decideMessage(String msg, InetAddress address,
        Document hosgroup)
    {
        boolean flag = false;
        String clientip = address.getHostAddress();
        System.out.println(clientip);
        Node hospNode = hosgroup.getRootElement().selectSingleNode(
            "//hosip[@ip='" + clientip + "']");
        if (hospNode != null)
        {
            flag = true;
            // Message mesag = convertMessage(msg);
            // convertMessagetoHashMap(msg);
            saveMessage(convertMessagetoHashMap(msg));
            // saveMessage(mesag);
        }
        else
        {
            flag = false;
        }

        return flag;
    }


    public boolean saveMessage(Message mesag)
    {
        boolean flag = false;
        PersistenceMessage.saveData(mesag);
        return flag;
    }


    public boolean saveMessage(HashMap<String, String> hm)
    {
        boolean flag = false;
        PersistenceMessage.saveData(hm);
        return flag;
    }

}

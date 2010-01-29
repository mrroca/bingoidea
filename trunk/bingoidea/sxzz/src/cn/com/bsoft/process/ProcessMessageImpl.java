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
import cn.com.bsoft.wrap.WrappMessage;

public class ProcessMessageImpl implements IProcessMessage
{
    private Logger log = LogManager.getLogger(this.getClass());
    private HashMap<String, String> hashmap;
    private String className;


    public HashMap<String, String> convertMessagetoHashMap(String msg)
    {
        Document doc = null;
        hashmap = new HashMap<String, String>();
        try
        {
            doc = DocumentHelper.parseText(msg);
            Element root = doc.getRootElement();
            className = root.attributeValue("name");
            System.out.println(className);
            if (className == null || className.equals("")
                    || className.length() == 0)
            {
                try
                {
                    throw new Exception(
                        "miss classtype check your xml <message name='is service name'>");
                }
                catch (Exception e)
                {
                    log.error(e.getMessage());
                    return hashmap;
                }
            }
            List<?> list = root.elements();
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
        //System.out.println(clientip);
        Node hospNode = hosgroup.getRootElement().selectSingleNode(
            "//hosip[@ip='" + clientip + "']");
        if (hospNode != null)
        {
            flag = true;

            //saveMessage(convertMessagetoHashMap(msg));
        }
        else
        {
            flag = false;
        }

        return flag;
    }


    public boolean saveMessage(HashMap<String, String> hm)
    {
        boolean flag = false;
        //PersistenceMessage.saveData(hm);
        PersistenceMessage.insertData(hm, className);
        // PersistenceMessage.updateData(hm,className);
        return flag;
    }
}

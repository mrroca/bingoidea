package cn.com.bsoft.dbo;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import cn.com.bsoft.dao.IMessageDao;
import cn.com.bsoft.util.Constant;
import cn.com.bsoft.util.SystemInitImpl;

public class PersistenceMessage
{
    private static Logger log = LogManager.getLogger(PersistenceMessage.class);


    public static boolean saveData(HashMap<String, String> hm)
    {
        hm.values();
        Document doc = DocumentHelper.createDocument();
        // create rootElement
        Element root = doc.addElement("message");
        // add node Element
        for (Iterator<String> iterator = hm.keySet().iterator(); iterator
            .hasNext();)
        {
            Element node = root.addElement("msg");
            // add node Element Attribute
            String temp = iterator.next();
            node.addAttribute("name", temp);
            // add node Element content
            node.setText(hm.get(temp));
        }
        try
        {
            OutputFormat fmt = new OutputFormat();
            fmt.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(fmt);
            Calendar cal = Calendar.getInstance(Locale.CHINA);
            SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd_HH-mm-ss", Locale.CHINA);
            String mDateTime = format.format(cal.getTime());
            OutputStream out = new FileOutputStream(Constant.filePath
                    + "message-" + mDateTime + "-" + System.currentTimeMillis()
                    + ".xml");
            writer.setOutputStream(out);
            writer.write(doc);
            out.flush();
            out.close();
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            log.error(e);
        }
        return false;
    }


    public static boolean insertData(HashMap<String, String> hm,
        String className)
    {
        IMessageDao dao = (IMessageDao) SystemInitImpl.createFactory().getBean(
            className);
        try
        {
            dao.insertMessage(hm);
        }
        catch (SQLException e)
        {
            log.error(e.getMessage());
        }
        return true;
    }
}

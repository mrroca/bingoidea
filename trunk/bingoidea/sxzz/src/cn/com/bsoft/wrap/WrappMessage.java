package cn.com.bsoft.wrap;

import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;

import cn.com.bsoft.model.Message;

public class WrappMessage
{
    public static Message wrappMessage(List<?> list)
    {
        Message msg = new Message();
        for (int i = 0; i < list.size(); i++)
        {
            Element tempele = (Element) list.get(i);
            if ("hospitalcode".equals(tempele.attributeValue("name")))
            {
                msg.setHospitalcode(tempele.getText());
            }
            if ("doctorcode".equals(tempele.attributeValue("name")))
            {
                msg.setDoctorcode(tempele.getText());
            }
            if ("clinicquota".equals(tempele.attributeValue("name")))
            {
                msg.setClinicquota(tempele.getText());
            }
            if ("amorpm".equals(tempele.attributeValue("name")))
            {
                msg.setAmorpm(tempele.getText());
            }
            if ("weekday".equals(tempele.attributeValue("name")))
            {
                msg.setWeekday(tempele.getText());
            }
            if ("deptcode".equals(tempele.attributeValue("name")))
            {
                msg.setDeptcode(tempele.getText());
            }
            if ("workdate".equals(tempele.attributeValue("name")))
            {
                msg.setWorkdate(tempele.getText());
            }
        }
        return msg;
    }


    public static HashMap<String, String> wrappMessagetoHashMap(List<?> list)
    {
        HashMap<String, String> hm = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++)
        {
            Element element = (Element) list.get(i);
            hm.put(element.attributeValue("name"), element.getText());
        }
        return hm;
    }
}

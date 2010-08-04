package cn.com.bsoft.drugservice.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.bsoft.drugservice.dao.EventDao;
import cn.com.bsoft.drugservice.po.Event;

public class TempService
{
    private EventDao eventDao;


    public void setEventDao(EventDao eventDao)
    {
        this.eventDao = eventDao;
    }


    public void addTemp()
    {
        Event event = new Event();
        event.setTitle("5555");
        event.setDate(new Date());
        eventDao.saveEvent(event);
    }


    public static void main(String[] args)
    {
        try
        {
            ApplicationContext ac = new ClassPathXmlApplicationContext(
                "config/spring-context.xml");
            TempService tempService = (TempService) ac.getBean("tempService");
            tempService.addTemp();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

package cn.com.bsoft.util;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import cn.com.bsoft.base.BaseImpl;

public class SystemInitImpl extends BaseImpl implements ISystemInit
{
    private static ClassPathResource classpathresource = null;
    private static XmlBeanFactory xmlbeanfactory = null;


    public static void init()
    {
        DOMConfigurator.configure(Constant.LOG4J_CONFIG);
    }


    public static XmlBeanFactory createFactory()
    {
        classpathresource = new ClassPathResource("spring-context.xml");

        xmlbeanfactory = new XmlBeanFactory(classpathresource);

        return xmlbeanfactory;
    }
}

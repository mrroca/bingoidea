package com.bsoft.szdc.send.util;

import org.apache.log4j.xml.DOMConfigurator;

public class LogConfig
{

    public static void getLogConfig()
    {

        DOMConfigurator.configure(Constant.LOG4J_CONFIG);

    }
}

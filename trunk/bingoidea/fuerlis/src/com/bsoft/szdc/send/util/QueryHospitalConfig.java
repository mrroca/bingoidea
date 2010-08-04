package com.bsoft.szdc.send.util;

import org.apache.log4j.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class QueryHospitalConfig
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
        .getLogger(QueryHospitalConfig.class);


    public static String ReadHospitalCode()
    {

        String hospitalcode = "";
        Document mDoc = null;
        try
        {
            mDoc = new SAXReader().read(Constant.HOSPITAL_CONFIG);
        }
        catch (DocumentException e)
        {
            logger.error(e.getMessage());
        }
        Element root = mDoc.getRootElement();
        hospitalcode = root.element("hospitalcode").getText();

        return hospitalcode;
    }


    public static String ReadHospitalName()
    {

        String hospitalname = "";
        Document mDoc = null;
        try
        {
            mDoc = new SAXReader().read(Constant.HOSPITAL_CONFIG);
        }
        catch (DocumentException e)
        {
            logger.error(e.getMessage());
        }
        Element root = mDoc.getRootElement();
        hospitalname = root.element("hospitalname").getText();

        return hospitalname;
    }


    public static void main(String[] args)
    {
        System.out.println(QueryHospitalConfig.ReadHospitalCode());
        System.out.println(QueryHospitalConfig.ReadHospitalName());
    }
}

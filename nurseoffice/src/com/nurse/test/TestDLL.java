package com.nurse.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDLL
{
    
    public static void main(String[] args)
    {
        Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历.
        cal.add(Calendar.DAY_OF_MONTH, -1);//取当前日期的前一天.    
          
        //cal.add(Calendar.DAY_OF_MONTH, +1);//取当前日期的后一天.    
          
        //通过格式化输出日期    
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");    
        
        //System.out.println("Today is:"+format.format(Calendar.getInstance().getTime()));    
         
        //System.out.println("yesterday is:"+format.format(cal.getTime()));
        Date date = new Date();
        int m = 3;
        int n = 3;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            date = sdf.parse("2010-07-05");
            
            System.out.println(new Date(date.getTime()));
            Long time= date.getTime();
            System.out.println(time);
            //time = time+3*24*60*60*1000;
            //System.out.println(new Date(time));
            for(int i = 0 ; i<m ; i++){
                time = time + n*24*60*60*1000;
                System.out.println(new Date(time));
                String dates = sdf.format(new Date(time)); 
                System.out.println(dates);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        } 
    }
}

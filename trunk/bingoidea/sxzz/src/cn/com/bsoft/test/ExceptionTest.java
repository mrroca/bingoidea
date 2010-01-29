package cn.com.bsoft.test;

import cn.com.bsoft.exception.BaseException;
import cn.com.bsoft.exception.PrintExceptionImpl;

public class ExceptionTest
{

    public void print()
    {
        try
        {
            throw new BaseException("Hello Exception!");
        }
        catch (Exception e)
        {
            PrintExceptionImpl.printError(e);
        }
    }


    public void print2()
    {
        Throwable t = new Throwable();
        try
        {
            throw new BaseException("exception", t);
        }
        catch (BaseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        ExceptionTest et = new ExceptionTest();
        et.print();
        // et.print2();
    }
}

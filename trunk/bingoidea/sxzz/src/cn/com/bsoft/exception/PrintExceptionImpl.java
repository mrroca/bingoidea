package cn.com.bsoft.exception;

public class PrintExceptionImpl implements IPrintException
{
    public static void printError(Exception e)
    {
        StackTraceElement[] ste = e.getStackTrace();
        for (int i = 0; i < ste.length; i++)
        {
            System.out.println(ste[i].toString());
        }
    }
}

package cn.com.bsoft.wsm.singleton;

public class GlobalObject
{
    private PrintSpooler printSpooler;


    public GlobalObject()
    {
        printSpooler = new PrintSpooler();
        // ...
    }


    public PrintSpooler getPrintSpooler()
    {
        return printSpooler;
    }
}

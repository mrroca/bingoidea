package com.bsoft.szdc.send.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobTest implements Job
{
    private Logger mLog = LogManager.getLogger(this.getClass());


    private void PrintStr()
    {
        mLog.info("启动一个作业");
        System.out.println("启动一个作业");
    }


    public void execute(JobExecutionContext arg0) throws JobExecutionException
    {
        PrintStr();
    }

}

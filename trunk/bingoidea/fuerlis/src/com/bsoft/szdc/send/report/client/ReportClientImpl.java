package com.bsoft.szdc.send.report.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.net.SocketFactory;

import com.bsoft.szdc.send.util.Constant;

public class ReportClientImpl implements ReportClient
{

    private Properties mProp = null;

    private String mServerIp = null;

    private String mServerPort = null;


    public ReportClientImpl() throws IOException
    {
        mProp = System.getProperties();
        File file = new File(Constant.REPORT_CLIENT_CFG);
        InputStream is = new FileInputStream(file);
        mProp.load(is);
        mServerIp = mProp.getProperty("server_ip");
        mServerPort = mProp.getProperty("server_port");
        is.close();
    }


    public void sendReport(String msg) throws IOException
    {
        Socket sok = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try
        {
            SocketFactory sf = SocketFactory.getDefault();
            sok = sf.createSocket(mServerIp, Integer.parseInt(mServerPort));
            sok.setSoTimeout(1500);
            dos = new DataOutputStream(sok.getOutputStream());
            dis = new DataInputStream(sok.getInputStream());

            byte[] msgs = msg.getBytes("gb2312");

            dos.write(String.valueOf(msgs.length).getBytes());
            dos.flush();

            while (true)
            {
                byte[] r = new byte[5];
                dis.read(r, 0, r.length);
                if ("ready".equals(new String(r).trim()))
                    break;
            }

            dos.write(msgs);
            dos.flush();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (dis != null)
                dis.close();
            if (dos != null)
                dos.close();
            if (sok != null && !sok.isClosed())
            {
                sok.shutdownInput();
                sok.shutdownOutput();
                sok.close();
            }
        }
    }


    public static void main(String[] args) throws IOException
    {
        ReportClientImpl rci = new ReportClientImpl();
        String msg = "<mhwsj><hosp ip='10.98.64.13'>七宝测试内容</hosp></mhwsj>";
        rci.sendReport(msg);
    }
}

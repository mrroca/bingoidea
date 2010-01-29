package cn.com.bsoft.socket.client;

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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cn.com.bsoft.util.Constant;
import cn.com.bsoft.util.SystemInitImpl;
import static cn.com.bsoft.util.SystemPrint.*;

public class SendMessageClientImpl implements ISendMessageClient
{

    private Properties mProp = null;

    private String mServerIp = null;

    private String mServerPort = null;

    private Logger log = LogManager.getLogger(SendMessageClientImpl.class);


    public SendMessageClientImpl() throws IOException
    {
        mProp = System.getProperties();
        File file = new File(Constant.REPORT_CLIENT_CFG);
        InputStream is = new FileInputStream(file);
        mProp.load(is);
        mServerIp = mProp.getProperty("server");
        mServerPort = mProp.getProperty("port");
        is.close();
    }


    public void sendMessage(String msg) throws IOException
    {
        Socket sok = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try
        {
            SocketFactory sf = SocketFactory.getDefault();

            sok = sf.createSocket(mServerIp, Integer.parseInt(mServerPort));

            sok.setSoTimeout(500);

            dos = new DataOutputStream(sok.getOutputStream());

            dis = new DataInputStream(sok.getInputStream());

            byte[] msgs = msg.getBytes("UTF-8");

            println(new String(msgs, "UTF-8").trim());

            dos.write(String.valueOf(msgs.length).getBytes("UTF-8"));

            dos.flush();

            while (true)
            {
                byte[] r = new byte[8];
                dis.read(r, 0, r.length);
                println(new String(r).trim()+"--");
                if ("ready!".equals(new String(r).trim()))
                {
                    break;
                }
            }

            dos.write(msgs);

            dos.flush();

            while (true)
            {
                byte[] r = new byte[8];
                dis.read(r, 0, r.length);
                println(new String(r).trim());
                if ("success!".equals(new String(r).trim())
                        || "failure!".equals(new String(r).trim()))
                {
                    break;
                }
            }
        }
        catch (NumberFormatException e)
        {
            log.error(e);
        }
        catch (UnknownHostException e)
        {
            log.error(e);
        }
        catch (IOException e)
        {
            log.error(e);
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
        SystemInitImpl.init();
        ISendMessageClient ismc = new SendMessageClientImpl();
        String msg = "<?xml version='1.0' encoding='UTF-8'?><message name='doctorScheduler'><msg description='医院代码' name='hospitalcode'>3002</msg><msg description='医生代码' name='doctorcode'>0010</msg><msg description='门诊限额' name='clinicquota'>5</msg><msg description='上午或下午' name='amorpm'>am</msg><msg description='星期' name='weekday'>星期3</msg><msg description='科室代码' name='deptcode'>001</msg><msg description='工作日期' name='workdate'>20100101</msg></message>";
        for (int i = 0; i < 5; i++)
        {
            ismc.sendMessage(msg);
        }
    }
}

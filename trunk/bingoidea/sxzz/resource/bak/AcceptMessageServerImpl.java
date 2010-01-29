package cn.com.bsoft.socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import javax.net.ServerSocketFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import cn.com.bsoft.base.BaseImpl;
import cn.com.bsoft.process.IProcessMessage;
import cn.com.bsoft.util.Constant;
import cn.com.bsoft.util.SystemInitImpl;

/**
 * socket server receive data from hospital
 */

public class AcceptMessageServerImpl extends BaseImpl implements
    IAcceptMessageServer
{

    private ServerSocket mSS = null;

    private Properties mProp = null;

    private Document mHospIp = null;

    private InetAddress clientIp = null;

    private Logger log = LogManager.getLogger(this.getClass());


    public AcceptMessageServerImpl()
    {
        try
        {
            init();
        }
        catch (Exception e)
        {
            log.error(e);
        }
    }


    private boolean init()
    {
        boolean flag = false;

        mProp = System.getProperties();

        InputStream is = null;

        try
        {
            mHospIp = new SAXReader().read(Constant.HOSP_IP);

            is = new FileInputStream(new File(Constant.SERVER_CFG));

            mProp.load(is);

            int port = Integer.parseInt(mProp.getProperty("port"));

            mSS = ServerSocketFactory.getDefault().createServerSocket(port);

            flag = true;
        }
        catch (DocumentException e)
        {
            log.error(e);

            flag = false;
        }
        catch (IOException ioe)
        {
            log.error(ioe);

            flag = false;
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                log.error(e);

                flag = false;
            }
        }
        return flag;
    }


    /*
     * (non-Javadoc)
     * 
     * @see cn.com.bsoft.sxzz.socket.server.IAcceptMessageServer#acceptMessage()
     */
    public void acceptMessage()
    {
        if (mSS != null)
            while (true)
            {
                Socket sok = null;

                try
                {
                    sok = mSS.accept();
                }
                catch (IOException e)
                {
                    log.error(e);
                }

                clientIp = sok.getInetAddress();

                Process oper = new AcceptMessageServerImpl.Process(sok);

                Thread thread = new Thread(oper);

                thread.start();
            }
    }

    public class Process implements Runnable
    {

        private Socket mSok = null;


        public Process()
        {
        }


        public Process(Socket s)
        {
            mSok = s;
        }


        public void setSocket(Socket s)
        {
            mSok = s;
        }


        private void sendMessage(OutputStream os, String msg)
            throws IOException
        {
            os.write(msg.getBytes("UTF-8"));

            os.flush();
        }


        private byte[] receiveMessage(InputStream is, int length)
            throws IOException
        {
            byte[] buf = new byte[length];

            is.read(buf, 0, length);

            return buf;
        }


        public void run()
        {
            DataInputStream dis = null;

            DataOutputStream dos = null;
            try
            {
                dis = new DataInputStream(mSok.getInputStream());

                dos = new DataOutputStream(mSok.getOutputStream());

                String msg = new String(receiveMessage(dis, 10000), "UTF-8");

                msg = msg.substring(0, msg.indexOf("</message>") + 10);

                System.out.println(msg);

                IProcessMessage ipm = (IProcessMessage) SystemInitImpl
                    .createFactory().getBean("process");

                if (ipm.decideMessage(msg, clientIp, mHospIp))
                {
                    sendMessage(dos, "success");
                }
                else
                {
                    sendMessage(dos, "failure");
                }
            }
            catch (IOException e)
            {
                log.info("IOException：" + e.getMessage());
            }
        }
    }


    public static void main(String[] args)
    {
        SystemInitImpl.init();

        IAcceptMessageServer ams = new AcceptMessageServerImpl();

        ams.acceptMessage();
    }
}

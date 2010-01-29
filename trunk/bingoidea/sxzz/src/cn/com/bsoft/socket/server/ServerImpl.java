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
import static cn.com.bsoft.util.SystemPrint.*;

/**
 * socket server receive data from hospital
 */

public class ServerImpl extends BaseImpl implements IServer
{
    /*
     * doc是医院IP配置xml, prop为socketserver的配置, address为客户端地址
     */

    private ServerSocketFactory serverSocketFactory;

    private ServerSocket serverSocket;

    private DataInputStream dis;

    private DataOutputStream dos;

    private Properties prop;

    private Document doc;

    private InetAddress address;

    private String ipconfig;

    private String propfile;

    private Logger log;


    public ServerImpl() throws Exception
    {
        ipconfig = Constant.HOSP_IP;

        propfile = Constant.SERVER_CFG;

        if (!init())
        {
            throw new Exception("socket server init failed!");
        }
    }


    private boolean init()
    {
        boolean flag = false;

        log = LogManager.getLogger(this.getClass());

        prop = System.getProperties();

        InputStream is = null;

        try
        {
            doc = new SAXReader().read(ipconfig);

            is = new FileInputStream(new File(propfile));

            prop.load(is);

            String s = prop.getProperty("port");

            int port = Integer.parseInt(s);

            serverSocketFactory = ServerSocketFactory.getDefault();

            serverSocket = serverSocketFactory.createServerSocket(port);

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

                is = null;
            }
            catch (IOException e)
            {
                log.error(e);

                flag = false;
            }
        }
        return flag;
    }


    public void acceptMessage()
    {
        if (serverSocket != null)
        {
            while (true)
            {
                Socket socket = null;

                try
                {
                    socket = serverSocket.accept();
                }
                catch (IOException e)
                {
                    log.error(e);
                }

                address = socket.getInetAddress();

                Process process = new ServerImpl.Process(socket);

                Thread thread = new Thread(process);

                thread.start();
            }
        }
    }

    public class Process implements Runnable
    {

        private Socket socket = null;


        public Process()
        {
        }


        public Process(Socket s)
        {
            socket = s;
        }


        private DataInputStream getInput(Socket sok) throws IOException
        {
            return new DataInputStream(sok.getInputStream());
        }


        private DataOutputStream getOutput(Socket sok) throws IOException
        {
            return new DataOutputStream(sok.getOutputStream());
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
            dis = null;

            dos = null;

            try
            {
                dis = getInput(socket);

                dos = getOutput(socket);

                String s = new String(receiveMessage(dis, 16), "UTF-8");

                int size = Integer.parseInt(s.trim());

                if (size > 0)
                {
                    sendMessage(dos, "ready!");
                    String msg = new String(receiveMessage(dis, size), "UTF-8");
                    IProcessMessage ipm = (IProcessMessage) SystemInitImpl
                        .createFactory().getBean("process");

                    println(msg);

                    if (ipm.decideMessage(msg, address, doc))
                    {
                        sendMessage(dos, "success!");
                    }
                    else
                    {
                        sendMessage(dos, "failure!");
                    }
                }
            }
            catch (IOException e)
            {
                log.error("IOException：" + e.getMessage());
            }
        }
    }


    public static void main(String[] args)
    {
        SystemInitImpl.init();

        IServer ams;

        Logger log = LogManager.getLogger(ServerImpl.class);

        try
        {
            ams = new ServerImpl();

            ams.acceptMessage();
        }
        catch (Exception e)
        {
            log.error("Exception：" + e.getMessage());
        }
    }
}

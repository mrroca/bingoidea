package cn.com.wsm;

import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import com.sun.xml.messaging.saaj.util.JAXMStreamSource;

public class SOAPRequest
{
    public static void main(String[] args)
    {
        try
        {
            SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = sfc.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage sm = mf.createMessage();

            SOAPHeader sh = sm.getSOAPHeader();
            SOAPBody sb = sm.getSOAPBody();
            sh.detachNode();
            QName bodyName = new QName("http://wsm.com.cn/", "sayHi", "ns1");
            SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);
            QName qn = new QName("arg0");
            SOAPElement quotation = bodyElement.addChildElement(qn);

            quotation.addTextNode("dodo");

            System.out.println("Soap Request:\n");
            sm.writeTo(System.out);
            System.out.println();

            URL endpoint = new URL("http://localhost:8080/cxffirst/HelloWorld");
            SOAPMessage response = connection.call(sm, endpoint);
            OutputStream ps = System.out;
            response.getMimeHeaders().toString();
            response.writeTo(ps);
            System.out.println();
            SOAPPart sp = response.getSOAPPart();
            JAXMStreamSource source = (JAXMStreamSource) sp.getContent();
            byte[] b = new byte[209];
            source.getInputStream().read(b);
            System.out.println(new String(b,"UTF-8"));
            System.out.println();

            //SOAPBody sbody = response.getSOAPBody();
            //System.out.println(response.getProperty(""));
            Iterator<?> iterator = response.getMimeHeaders().getAllHeaders();//sbody.getChildElements(bodyName);
            while (iterator.hasNext())
            {
                //System.out.println("The Value is:" + val.getName()+"--"+val.getValue());
            }
            //System.out.println(response.getContentDescription());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

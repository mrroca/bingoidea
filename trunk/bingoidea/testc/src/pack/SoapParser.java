package pack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
//import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SoapParser 
{
    
    public static void main(String[] args)
    {
        doSoapPost();
    }

    public static void doSoapPost()
    {
        try 
        {
             //First create the connection
             SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
             SOAPConnection connection = soapConnFactory.createConnection();//创建连接
             
             //Next, create the actual message
             MessageFactory messageFactory = MessageFactory.newInstance();
             SOAPMessage message = messageFactory.createMessage();//创建soap请求
             
             //Create objects for the message parts
             //System.out.println(message.getMimeHeaders().toString());
             
             SOAPPart soapPart = message.getSOAPPart();
             SOAPEnvelope envelope = soapPart.getEnvelope();
             envelope.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
             envelope.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
             SOAPBody body = envelope.getBody();
//             SOAPHeader header = envelope.getHeader();
//             SOAPElement headerElement=header.addChildElement(envelope.createName("AuthenticationHeader" , "", "http://myservice/"));
//             SOAPElement hfirstElemnt = headerElement.addChildElement("UserName");
//             SOAPElement hscondElemnt = headerElement.addChildElement("Password");
//             hfirstElemnt.addTextNode("WSJD");
//             hscondElemnt.addTextNode("WSJD2010");
             //Populate the body
             //Create the main element and namespace
             SOAPElement bodyElement = body.addChildElement(envelope.createName("SmsParam" , "", "http://tempuri.org/"));
             //Add content
             
             SOAPElement firstElemnt = bodyElement.addChildElement("mobile");
             firstElemnt.addTextNode("15150152960");

             SOAPElement secondElemnt = bodyElement.addChildElement("content");
             secondElemnt.addTextNode("test");
             SOAPElement NAMEElemnt = bodyElement.addChildElement("username");
             NAMEElemnt.addTextNode("WSJD");
             SOAPElement PWDElemnt = bodyElement.addChildElement("password");
             PWDElemnt.addTextNode("WSJD2010");
             //Save the message
             message.saveChanges();
             //Check the input
             message.writeTo(System.out);
             System.out.println();
            //Send the message and get a reply   
                
            //Set the destination
            String destination = "http://211.103.78.202/xxzxSmsp/SmsService.asmx";
            //Send the message
            SOAPMessage reply = connection.call(message, destination);
            
            if(reply!=null)
            {
                 //SOAPPart replySP = reply.getSOAPPart();
                 //SOAPEnvelope replySE = replySP.getEnvelope();
                 //SOAPBody replySB = replySE.getBody();

                 Source source = reply.getSOAPPart().getContent();
                 Transformer transformer = TransformerFactory.newInstance().newTransformer();
                 ByteArrayOutputStream myOutStr = new ByteArrayOutputStream();
                 StreamResult res = new StreamResult();
                 res.setOutputStream(myOutStr);
                 transformer.transform(source,res);
                 String temp = myOutStr.toString("UTF-8");
                 System.out.println("---------------");
                 System.out.println(temp);
                 byte[] bytes = temp.getBytes("UTF-8");
                 ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                 
                 DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
                 DocumentBuilder db = null;
                 Document doc = null;
                 db = dbf.newDocumentBuilder();
                 doc = db.parse(in);
                 //System.out.println("-------"+doc.toString());
                 Element docEle = doc.getDocumentElement();
                 System.out.println("-------"+docEle.toString());
                 NodeList nl = docEle.getElementsByTagName("ns:echoResponse");
                if(nl != null && nl.getLength() > 0) 
                {
                    for(int i = 0 ; i < nl.getLength();i++) 
                    {
                        //get the employee element
                        Element el = (Element)nl.item(i);
                        String name = getTextValue(el,"return");
                        //int id = getIntValue(el,"userId");
                        System.out.println("name: "+name);
                    }
                }    
            }
             //Close the connection            
             connection.close();
        } 
        catch(Exception e) 
        {
                System.out.println(e.getMessage());
        }   
    }
    

    private static String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() > 0) {
            Element el = (Element)nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal;
    }
    /** *//**
     * Calls getTextValue and returns a int value
     * @param ele
     * @param tagName
     * @return
     */
//    private static int getIntValue(Element ele, String tagName) {
//        //in production application you would catch the exception
//        return Integer.parseInt(getTextValue(ele,tagName));
//    }
//    
//    private static void parseXmlFile(String fileName)
//    {
//        //get the factory
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        try {
//            //Using factory get an instance of document builder
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            //parse using builder to get DOM representation of the XML file
//            Document dom = db.parse(fileName);
//        }catch(ParserConfigurationException pce) {
//            pce.printStackTrace();
//        }catch(SAXException se) {
//            se.printStackTrace();
//        }catch(IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
}

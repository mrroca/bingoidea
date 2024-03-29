
package cn.bsoft.service.client;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ReadCardServer", targetNamespace = "http://service.bsoft.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ReadCardServer {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "readCard", targetNamespace = "http://service.bsoft.cn/", className = "cn.bsoft.service.client.ReadCard")
    @ResponseWrapper(localName = "readCardResponse", targetNamespace = "http://service.bsoft.cn/", className = "cn.bsoft.service.client.ReadCardResponse")
    public String readCard();

}


package cn.bsoft.service.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.bsoft.service.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReadCard_QNAME = new QName("http://service.bsoft.cn/", "readCard");
    private final static QName _ReadCardResponse_QNAME = new QName("http://service.bsoft.cn/", "readCardResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.bsoft.service.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReadCardResponse }
     * 
     */
    public ReadCardResponse createReadCardResponse() {
        return new ReadCardResponse();
    }

    /**
     * Create an instance of {@link ReadCard }
     * 
     */
    public ReadCard createReadCard() {
        return new ReadCard();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.bsoft.cn/", name = "readCard")
    public JAXBElement<ReadCard> createReadCard(ReadCard value) {
        return new JAXBElement<ReadCard>(_ReadCard_QNAME, ReadCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.bsoft.cn/", name = "readCardResponse")
    public JAXBElement<ReadCardResponse> createReadCardResponse(ReadCardResponse value) {
        return new JAXBElement<ReadCardResponse>(_ReadCardResponse_QNAME, ReadCardResponse.class, null, value);
    }

}

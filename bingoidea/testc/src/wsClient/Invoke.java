package wsClient;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

public class Invoke
{
    public static void main(String[] args)
    {
        try
        {
            Client client = new Client(new URL(
                "http://211.103.78.202/SmsJkcf/SmsService.asmx?wsdl"));
            
            Object[] results = client.invoke("SendSms", new String[]{"15150152960","asdfasd"});
            System.out.println(results[0]);

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

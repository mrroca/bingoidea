package cn.com.wsm.ftp;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException; //import org.idoox.wasp.types.ResponseMessageAttachment;
import cn.com.wsm.ftp.FtpBean;
import cn.com.wsm.ftp.FtpException;

/**
 * A very simple FTP Web Service. This is designed to allow other Web Service to
 * download data in a standard way.
 * 
 * If you find the idea funny, I suggest you look at www.webservile.com
 * 
 * @author Mark Barnes, Systinet.
 * @version 1.0
 */
public class FTPServer
{

    /**
     * Gets the data at location, using username and password to login.
     * 
     * @param location
     *            The url where the file exists.
     * @param username
     *            The username to use to login.
     * @param password
     *            The password for the above user.
     * @return The data from the server.
     * @exception IOException
     *                Thrown if any errors occur during the download.
     */
    public byte[] download(String location, String username, String password)
        throws IOException
    {
        return getIt(location, username, password);
    }


    /**
     * Gets the data at location, using an anonymous login.
     * 
     * @param location
     *            The url where the file exists.
     * @return The data from the server.
     * @exception IOException
     *                Thrown if any errors occur during the download.
     */
    public byte[] download(String location) throws IOException
    {
        return download(location, null, null);
    }


    /**
     * Gets the data at location, and stores it in the attachment.
     * 
     * @param location
     *            The url where the file exists.
     * @param username
     *            The username to use to login.
     * @param password
     *            The password for the above user.
     * @param attachment
     *            The attachment onto which to store the data.
     * @return The size of data downloaded.
     * @exception IOException
     *                Thrown if any errors occur during the download.
     * 
     *                public long download(String location, String username,
     *                String password, ResponseMessageAttachment attachment)
     *                throws IOException { byte[] data = download(location,
     *                username, password); attachment.setData(new
     *                ByteArrayInputStream(data)); return data.length; }
     */
    /**
     * Gets the data at location, and stores it in the attachment.
     * 
     * @param location
     *            The url where the file exists.
     * @param attachment
     *            The attachment onto which to store the data.
     * @return The size of data downloaded.
     * @exception IOException
     *                Thrown if any errors occur during the download.
     * 
     *                public long download(String location,
     *                ResponseMessageAttachment attachment) throws IOException {
     *                return download(location, null, null, attachment); }
     */

    /**
     * A helper function to actually do the download.
     */
    private static byte[] getIt(String location, String username,
        String password) throws IOException
    {
        // We use FtpBean, which greatly simplifies the download process...
        FtpBean ftp = new FtpBean();
        try
        {
            // We convert the location to a URL, so we can extract details...
            URL url = new URL(location);
            System.out.println("Downloading from: " + url.toExternalForm());
            String user = (username == null) ? "anonymous" : username;
            String pass = (password == null) ? "bridge" : password;

            // connect to the server
            if (url.getPort() > 0)
            {
                ftp.setPort(url.getPort());
            }
            ftp.ftpConnect(url.getHost(), user, pass);

            // get the results...
            byte[] results = ftp.getBinaryFile(url.getPath());
            // be nice!
            ftp.close();
            return results;
        }
        catch (MalformedURLException mue)
        {
            throw new IOException("Couldn't download " + location
                    + ": Invalid URL: " + mue.toString());
        }
        catch (FtpException ftpe)
        {
            throw new IOException("Couldn't download " + location
                    + ": FTPException: " + ftpe.toString());
        }
    }
}

package cn.wsm.server;

import java.io.File;
import java.io.IOException;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.NCSARequestLog;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.handler.RequestLogHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class SimpleHttpServer
{

    Server server;

    int port;

    final int DEFAULTPORT = 8080;

    Connector connector = new SelectChannelConnector();

    HandlerCollection handlers = new HandlerCollection();

    ContextHandlerCollection contexts = new ContextHandlerCollection();

    RequestLogHandler requestLogHandler = new RequestLogHandler();

    WebAppContext context;


    public SimpleHttpServer() throws IOException
    {
        initialize(null, 0, null, null);
    }


    public SimpleHttpServer(File webAppDir, int port, String host, File logFile)
        throws IOException
    {
        initialize(webAppDir, port, host, logFile);
    }


    protected void initialize(File webAppDir, int port, String host,
        File logFile) throws IOException
    {
        webAppDir = webAppDir == null ? new File(
            new File("").getAbsoluteFile(), "WebContent") : webAppDir;

        server = new Server();
        this.port = port == 0 ? DEFAULTPORT : port;

        connector.setPort(this.port);
        if (host != null && host.length() > 1)
            connector.setHost(host);
        server.setConnectors(new Connector[] { connector });

        handlers.setHandlers(new Handler[] { contexts, new DefaultHandler(),
                requestLogHandler });
        context = new WebAppContext(contexts, webAppDir.getAbsolutePath(), "/");
        server.addHandler(handlers);

        // jetty��־
        logFile = logFile == null ? new File(new File("").getAbsoluteFile(),
            "jetty.log") : logFile;
        logFile.createNewFile();
        NCSARequestLog requestLog = new NCSARequestLog(logFile
            .getAbsolutePath());
        requestLog.setExtended(false);
        requestLogHandler.setRequestLog(requestLog);
        server.setSendServerVersion(true);
    }


    public void start() throws Exception
    {
        if (this.server != null)
            this.server.start();
    }


    public static void main(String[] args) throws Exception
    {

        SimpleHttpServer server = new SimpleHttpServer(null, 8080, null, null);

        server.start();

    }
}

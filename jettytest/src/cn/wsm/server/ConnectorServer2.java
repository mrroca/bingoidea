package cn.wsm.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class ConnectorServer2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(8081);
		connector.setUseDirectBuffers(false);
		server.addConnector(connector);

		WebAppContext context = new WebAppContext();

		context.setContextPath("/");
		context.setDescriptor("web/WEB-INF/web.xml");
		context.setResourceBase("web");
		//context.setConfigurationDiscovered(true);

		server.setHandler(context);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

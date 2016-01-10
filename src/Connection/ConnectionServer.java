package Connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConnectionServer {
	private ServerSender serv;
	private ExecutorService exec = Executors.newFixedThreadPool(1);
	
	public ConnectionServer()
	{
		serv=new ServerSender();
		exec.submit(serv);
	}
	
	public ServerSender getServer()
	{
		return serv;
	}
	
}

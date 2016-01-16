package Connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Mechanics.Player2;


public class ConnectionServer {
	private ServerSender serv;
	private ExecutorService exec = Executors.newFixedThreadPool(1);
	
	public ConnectionServer(Player2 p2)
	{
		serv=new ServerSender(p2);
		exec.submit(serv);
	}
	
	public ServerSender getServer()
	{
		return serv;
	}
	
}

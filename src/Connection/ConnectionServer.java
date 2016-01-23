package Connection;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Mechanics.Player2;


public class ConnectionServer {
	private ServerSender servSend;
	private ServerConnectingWithClient serv;
	private ExecutorService exec = Executors.newFixedThreadPool(1);
	
	public ConnectionServer(Player2 p2, int s)
	{
		serv=new ServerConnectingWithClient();
		Socket g=serv.connectWithClient();
		servSend=new ServerSender(p2, g, s);
		exec.submit(servSend);
	}
	
	public ServerSender getServer()
	{
		return servSend;
	}
	
}

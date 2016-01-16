package Connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Mechanics.Player2;


public class ConnectionClient {
	private ClientReceiver cl;
	ExecutorService exec = Executors.newFixedThreadPool(1);
	
	public ConnectionClient(Player2 p2) 
	{
		cl=new ClientReceiver(p2);
		exec.submit(cl);
	}

	public ClientReceiver getClient()
	{
		return cl;
	}
}

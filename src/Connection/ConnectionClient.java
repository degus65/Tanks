package Connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConnectionClient {
	private ClientReceiver cl;
	ExecutorService exec = Executors.newFixedThreadPool(1);
	
	public ConnectionClient() 
	{
		cl=new ClientReceiver();
		exec.submit(cl);
	}

	public ClientReceiver getClient()
	{
		return cl;
	}
}

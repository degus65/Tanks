package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class ClientReceiver implements Callable<Boolean> {

	private BufferedReader in;
	private Socket g;
	private ClientSender cs;
	private FutureTask<String> sendTask;
	ExecutorService exec;
	
	public ClientReceiver() {
		
		try {
			g=new Socket(InetAddress.getLocalHost(), 50000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		exec = Executors.newFixedThreadPool(5);
		
		try {
			in= new BufferedReader(new InputStreamReader(g.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	
	public Boolean call() throws IOException {
		// TODO Auto-generated method stub
		String strLine="";
		
		while(true){
				if((strLine = in.readLine()) != null)
				{
					System.out.println(strLine);
				}
				cs=new ClientSender(g);
				sendTask=new FutureTask<String>(cs);
				
				//exec.submit(sendTask);
				if(strLine.equalsIgnoreCase("END"))
					return true;
		}
	}
	

}

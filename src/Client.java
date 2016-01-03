
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Client implements Runnable {

	private Socket g;
	private ClientReceiver cr;
	private PrintWriter out;
	
	Client()
	{
		try {
			g=new Socket(InetAddress.getLocalHost(), 50000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cr=new ClientReceiver(g);
		
		try {
			out = new PrintWriter(new OutputStreamWriter(g.getOutputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("Klient ³¹czy siê z serwerem");
		
		Thread tcr=new Thread(cr);
		tcr.start();
		
		String msg="";
		
		while(true){
			
		}
	}
	
		
	
}

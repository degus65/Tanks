package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import Mechanics.Player2;


public class ClientReceiver implements Callable<Boolean> {

	private BufferedReader in;
	private PrintWriter out;
	private Socket g;
	private Player2 p2;
	ExecutorService exec;
	
	public ClientReceiver(Player2 p2) {
		
		try {
			g=new Socket(InetAddress.getLocalHost(), 50000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.p2=p2;
		exec = Executors.newFixedThreadPool(5);
		
		try {
			in= new BufferedReader(new InputStreamReader(g.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(g.getOutputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void fire(int x, int y)
	{
		FutureTask<Boolean> fireTask=new FutureTask<Boolean>(new Fire(out, x, y));
		exec.submit(fireTask);
	}
	
	public void coordinates(int c)
	{
		FutureTask<Boolean> coord=new FutureTask<Boolean>(new Coordinates(out, c));
		exec.submit(coord);
	}
	
	
	public Boolean call() throws IOException {
		// TODO Auto-generated method stub
		String strLine="";
		
		while(true)
		{
			if((strLine = in.readLine()) != null)
			{
				//System.out.println(strLine);
				if(strLine.equalsIgnoreCase("FIRE"))
				{
					strLine = in.readLine();
					int x=Integer.parseInt(strLine);
					strLine = in.readLine();
					int y=Integer.parseInt(strLine);
					p2.fire(x, y);
				}
				
				if(strLine.equalsIgnoreCase("COORD"))
				{
					strLine=in.readLine();
					int c=Integer.parseInt(strLine);
					p2.getCoordinates(c);
				}
			}
		}
	}
	

}

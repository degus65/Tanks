package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import Mechanics.Player2;


public class ServerSender implements Callable<Boolean> {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private Player2 p2;
	private ExecutorService exec = Executors.newFixedThreadPool(5);
	private int stage;
	private boolean gameStart;
	
	ServerSender(Player2 p2, Socket g, int s)
	{
		this.clientSocket=g;
		this.p2=p2;
		this.stage=s;
	}
	
	public void establishWriterAndReader()
	{
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
	
	
	public void coord(int x, int y, int d)
	{
		FutureTask<Boolean> cord=new FutureTask<Boolean>(new Coordinates(out, x, y, d));
		exec.submit(cord);
	}
	
	private void whichStage(int s)
	{
		FutureTask<Boolean> st=new FutureTask<Boolean>(new whichStage(out, s));
		exec.submit(st);
	}
	
	public Boolean call() throws InterruptedException, ExecutionException, NumberFormatException, IOException
	{
		establishWriterAndReader();
		String strLine="";
		
		while(true)
		{
			if(gameStart==false)
				whichStage(stage);
			if((strLine = in.readLine()) != null)
			{
				if(strLine.equalsIgnoreCase("FIRE"))
				{
					strLine = in.readLine();
					int x=Integer.parseInt(strLine);
					strLine = in.readLine();
					int y=Integer.parseInt(strLine);
					p2.fire(x, y);
				}
				
				if(strLine.equalsIgnoreCase("SETXY"))
				{
					gameStart=true;
					strLine = in.readLine();
					int x=Integer.parseInt(strLine);
					strLine = in.readLine();
					int y=Integer.parseInt(strLine);
					strLine = in.readLine();
					int d=Integer.parseInt(strLine);
					p2.setX(x);
					p2.setY(y);
					p2.setDirection(d);
				}
			}
		}
		//return true;
	}
	
}

package Connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class ServerSender implements Callable<Boolean> {

	private Socket clientSocket;
	private PrintWriter out;
	private ServerReceiver sr;
	private FutureTask<String> taskRecSrv;
	private ServerSocket server;
	private ExecutorService exec = Executors.newFixedThreadPool(5);
	
	ServerSender()
	{
		try {
			server=new ServerSocket(50000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectClient()
	{
		System.out.println("CZekanie na klienta");
		 try {
             clientSocket = server.accept();
         } catch (IOException e) {
             e.printStackTrace();
         }
		 System.out.println("po");
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	public void fire()
	{
		out.print("FIRE");
	}
	
	public Boolean call() throws InterruptedException, ExecutionException
	{
		String msg="";
		connectClient();
		while(true)
		{
			sr=new ServerReceiver(clientSocket);
			taskRecSrv=new FutureTask<String>(sr);
			exec.submit(taskRecSrv);
			if(taskRecSrv.isDone())
			{
				msg=taskRecSrv.get();
				System.out.println(msg);
			}
		}
		//return true;
	}
	
}

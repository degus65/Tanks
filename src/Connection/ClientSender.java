package Connection;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class ClientSender implements Callable<String> {

	
	private Scanner sc;
	private PrintWriter out;
	
	ClientSender(Socket g)
	{
		
		try {
			out = new PrintWriter(new OutputStreamWriter(g.getOutputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		sc=new Scanner(System.in);
	}

	@Override
	public String call() {
		// TODO Auto-generated method stub
			
		String msg="";
		//System.out.println("Wpisz odpowiedz:");
		msg=sc.nextLine();
		out.println(msg);
		out.flush();
		return msg;
		
	}
	
	
		
	
}

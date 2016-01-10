package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.Callable;


public class ServerReceiver implements Callable<String> {
	
	private BufferedReader in;
	
	ServerReceiver(Socket clientSocket)
	{
	
		try {
			in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public String call() throws IOException {
		
		
		String strLine="";
		while((strLine = in.readLine()) == null){
			//System.out.println(".");
	}
		return strLine;
	
	}
	
	

	

}

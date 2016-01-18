package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectingWithClient {
	
	private ServerSocket server;
	private Socket clientSocket;
	
	ServerConnectingWithClient()
	{
		try {
			server=new ServerSocket(50000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Socket connectWithClient()
	{
		 try {
           clientSocket = server.accept();
       } catch (IOException e) {
           e.printStackTrace();
       }
		 return clientSocket;
	}
}

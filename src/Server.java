import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;



public class Server implements Runnable {
	
	private Socket clientSocket;
	private ServerSocket server;
	
	
	Server()
	{
		try {
			server=new ServerSocket(50000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
            try {
                clientSocket = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Connection.sr=new ServerSender(clientSocket);
            Connection.sr.start();
        }
	}
	
	


}

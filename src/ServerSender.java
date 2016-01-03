import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


public class ServerSender extends Thread {

	private Socket clientSocket;
	private PrintWriter out;
	private ServerReceiver sr;
	
	ServerSender(Socket socket)
	{
		clientSocket=socket;
	}
	
	public void connectClient()
	{
		try {
			out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		sr=new ServerReceiver(clientSocket);
		Thread tsr=new Thread(sr);
		tsr.start();
	}
	
	public void fire(int d, int x, int y)
	{
		out.println("FIRE");
		out.println(d);//direction 1, 2, 3, 4
		out.println(x);
		out.println(y);
		out.flush();
	}
	
	public void run()
	{
		connectClient();
	}
	
}

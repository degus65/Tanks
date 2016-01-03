import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.SQLException;


public class ServerReceiver implements Runnable {
	
	private BufferedReader in;
	private int index=0;
	private boolean ansReceived=true;
	
	ServerReceiver(Socket clientSocket)
	{
	
		try {
			in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public boolean getAnsReceived()
	{
		return ansReceived;
	}
	
	public void setAnsReceived()
	{
		ansReceived=false;
	}
	
	@Override
	public void run() {
			
		while(true){
			String strLine;
			try {
				if((strLine = in.readLine()) != null)
				{
					if(strLine.equalsIgnoreCase("ID"))
					{
						index=Integer.parseInt(in.readLine());
						//System.out.println(index);
					}
					else
					{
						System.out.println("Client: "+strLine);
						ansReceived=true;
					}
						
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
	}
	
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;


public class ClientReceiver implements Runnable {

	private BufferedReader in;
	private int fireD=1, fireX=0, fireY=0;
	
	public ClientReceiver(Socket g) {
		
		
		try {
			in= new BufferedReader(new InputStreamReader(g.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			String strLine;
			try {
				if((strLine = in.readLine()) != null)
				{
					if(strLine.equalsIgnoreCase("FIRE"))
					{
						fireD=Integer.parseInt(in.readLine());
						fireX=Integer.parseInt(in.readLine());
						fireY=Integer.parseInt(in.readLine());
					}
					else if(strLine.equalsIgnoreCase("END"))
						break;	
					else
						System.out.println(strLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package Connection;

import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class whichStage implements Callable<Boolean> {

	private PrintWriter out;
	private int s;
	
	whichStage(PrintWriter out, int s)
	{
		this.out=out;
		this.s=s;
	}
	
	public Boolean call() throws Exception {
		out.println("STAGE");
		out.println(s);
		out.flush();
		return true;
	}
}

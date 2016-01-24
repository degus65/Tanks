package Connection;

import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class OnceAgain implements Callable<Boolean> {

	private PrintWriter out;
	
	OnceAgain(PrintWriter out)
	{
		this.out=out;
	}
	
	public Boolean call() throws Exception {
		out.println("AGAIN");
		out.flush();
		return true;
	}

}

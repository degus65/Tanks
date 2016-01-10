package Connection;

import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class Fire implements Callable {

	private PrintWriter out;
	
	Fire(PrintWriter out)
	{
		this.out=out;
	}
	
	@Override
	public Object call() throws Exception {
		out.print("FIRE");
		return null;
	}

}

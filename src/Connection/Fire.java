package Connection;

import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class Fire implements Callable<Boolean> {

	private PrintWriter out;
	private int x, y;
	
	Fire(PrintWriter out, int x, int y)
	{
		this.out=out;
		this.x=x;
		this.y=y;
	}
	
	@Override
	public Boolean call() throws Exception {
		out.println("FIRE");
		out.println(x);
		out.println(y);
		out.flush();
		return true;
	}

}

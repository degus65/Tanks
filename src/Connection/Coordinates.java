package Connection;

import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class Coordinates implements Callable<Boolean> {

	private PrintWriter out;
	private int x, y, d;
	
	Coordinates(PrintWriter out, int x, int y, int d)
	{
		this.out=out;
		this.x=x;
		this.y=y;
		this.d=d;
	}
	
	public Boolean call() throws Exception {
		out.println("SETXY");
		out.println(x);
		out.println(y);
		out.println(d);
		out.flush();
		return true;
	}
}

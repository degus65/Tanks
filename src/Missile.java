
public class Missile extends Sprite {
	private static final int MISSILE_SPEED = 8;
	private boolean active=true;
	
	Missile(Direction d, int x, int y)
	{
		super("missile.png");
		
		if(d.equals(Direction.UP))
			vY=-MISSILE_SPEED;
		if(d.equals(Direction.RIGHT))
			vX=MISSILE_SPEED;
		if(d.equals(Direction.DOWN))
			vY=MISSILE_SPEED;
		if(d.equals(Direction.LEFT))
			vX=-MISSILE_SPEED;
		this.x=x;
		this.y=y;
	}
	
	
	public void act() 
	{
		x += vX;
		y += vY;
		
		if (x < 0) {
			active=false;
		}
		if (y < 0) {
			active=false;
		}
		if (x > 603) {
			active=false;
		}
		if (y > 423) {
			active=false;
		}
	}
	
	public boolean getActive()
	{
		return active;
	}
	
	public void setActive(boolean v)
	{
		active=v;
	}
}

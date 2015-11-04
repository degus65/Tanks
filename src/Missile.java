import java.awt.image.BufferedImage;


public class Missile {
	private static final int MISSILE_SPEED = 8;
	private int x=0, y=0, vX=0, vY=0;
	private SpriteCache sprite = new SpriteCache();
	private String sciezka="missile.png";
	private boolean active=true;
	
	Missile(Direction d, int x, int y)
	{
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
	
	public BufferedImage getSprite() {
		return sprite.getSprite(sciezka);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean getActive(){
		return active;
	}
}

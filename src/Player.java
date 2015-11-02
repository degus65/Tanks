import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Player {
	private int x=400, y=400, vX=0, vY=0;
	private static final int PLAYER_SPEED = 4;
	String sciezka;
	SpriteCache sprite=new SpriteCache();
	private boolean up=false, down=false, left=false, right=false;
	
	Player(String sciezka)
	{
		this.sciezka=sciezka;
	}
	
	public void act() {
		x+=vX;
		y+=vY;
		
		// KOLIZJE Z RAMKA ~MATIUS
		if(x < 0 ){
			x = 0;
		}
		if(y < 0 ){
			y = 0;
		}
		if(x > 603)
		{
			x = 603;
		}
		if(y > 423)
		{
			y = 423;
		}
	}
	
	protected void updateSpeed()
	{
		vX=0;
		vY=0;
		if (down)
		{
			vY = PLAYER_SPEED;
			this.sciezka="playerDown.png";
		}
		if (up) 
		{
			vY = -PLAYER_SPEED;
			this.sciezka="playerUp.png";
		}
		if (left) 
		{
			vX = -PLAYER_SPEED;
			this.sciezka="playerLeft.png";
		}
		if (right) 
		{
			vX = PLAYER_SPEED;
			this.sciezka="playerRight.png";
		}
	}

	public void keyPressed(KeyEvent k) 
	{
		if(k.getKeyCode()==KeyEvent.VK_RIGHT)
			right=true;
		if(k.getKeyCode()==KeyEvent.VK_LEFT)
			left=true;
		if(k.getKeyCode()==KeyEvent.VK_UP)
			up=true;
		if(k.getKeyCode()==KeyEvent.VK_DOWN)
			down=true;
		updateSpeed();
	}
	
	public void keyReleased(KeyEvent k)
	{
		if(k.getKeyCode()==KeyEvent.VK_RIGHT)
			right=false;
		if(k.getKeyCode()==KeyEvent.VK_LEFT)
			left=false;
		if(k.getKeyCode()==KeyEvent.VK_UP)
			up=false;
		if(k.getKeyCode()==KeyEvent.VK_DOWN)
			down=false;
		updateSpeed();
	}
	
	public BufferedImage getSprite()
	{
		return sprite.getSprite(sciezka);
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	public void setSciezka(String sciezka)
	{
		this.sciezka=sciezka;
	}
		
}

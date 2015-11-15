import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public abstract class Sprite {
	protected int x, y, vX, vY;
	private SpriteCache sprite;
	protected String sciezka;
	protected BufferedImage img;
	
	Sprite(String sciezka) 
	{
		this.sciezka = sciezka;
		sprite=new SpriteCache();
		x=0; y=0; vX=0; vY=0;
		img=sprite.getSprite(sciezka);
	}
	
	public BufferedImage getSprite() 
	{
		img=sprite.getSprite(sciezka);
		return img;
	}
	
	public int getX() 
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
		
	public void setSciezka(String sciezka) {
		this.sciezka = sciezka;
	}
	
	 public Rectangle getBounds() {
	        return new Rectangle(x, y, img.getWidth(), img.getHeight());
	    }
}

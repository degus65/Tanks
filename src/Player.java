import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
	private int x = 301, y = 400, vX = 0, vY = 0;
	private static final int PLAYER_SPEED = 4;
	private String sciezka;
	private SpriteCache sprite = new SpriteCache();
	private boolean up = false, down = false, left = false, right = false;
	private ArrayList<Missile> missiles=new ArrayList<Missile>();
	Direction direction=Direction.UP;

	Player(String sciezka) {
		this.sciezka = sciezka;
	}

	public void act() {
		x += vX;
		y += vY;

		// KOLIZJE Z RAMKA ~MATIUS
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		if (x > 603) {
			x = 603;
		}
		if (y > 423) {
			y = 423;
		}
		
		for (int i=0; i<missiles.size(); i++)
		{
			if(missiles.get(i).getActive()==false)
				missiles.remove(i);
		}
	}
	
	public void fire()
	{
		missiles.add(new Missile(direction, x+13, y+13));
	}

	protected void updateSpeed() {
		vX = 0;
		vY = 0;
		if (down) {
			vY = PLAYER_SPEED;
			this.sciezka = "playerDown.png";
			direction=Direction.DOWN;
		}
		if (up) {
			vY = -PLAYER_SPEED;
			this.sciezka = "playerUp.png";
			direction=Direction.UP;
		}
		if (left) {
			vX = -PLAYER_SPEED;
			this.sciezka = "playerLeft.png";
			direction=Direction.LEFT;
		}
		if (right) {
			vX = PLAYER_SPEED;
			this.sciezka = "playerRight.png";
			direction=Direction.RIGHT;
		}

		// Collision();
	}

	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_RIGHT)
			right = true;
		if (k.getKeyCode() == KeyEvent.VK_LEFT)
			left = true;
		if (k.getKeyCode() == KeyEvent.VK_UP)
			up = true;
		if (k.getKeyCode() == KeyEvent.VK_DOWN)
			down = true;
		if (k.getKeyCode() == KeyEvent.VK_SPACE)
			fire();
		updateSpeed();
	}

	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_RIGHT)
			right = false;
		if (k.getKeyCode() == KeyEvent.VK_LEFT)
			left = false;
		if (k.getKeyCode() == KeyEvent.VK_UP)
			up = false;
		if (k.getKeyCode() == KeyEvent.VK_DOWN)
			down = false;
		updateSpeed();
	}
	
	public ArrayList<Missile> getMissile(){
		return missiles;
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

	public void setSciezka(String sciezka) {
		this.sciezka = sciezka;
	}

}

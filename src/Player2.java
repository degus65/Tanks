import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player2 {

	private int x = 301, y = 50, vX = 0, vY = 0;
	private static final int PLAYER2_SPEED = 3;
	String sciezka;
	SpriteCache sprite = new SpriteCache();
	private boolean up = false, down = false, left = false, right = false;

	Player2(String sciezka) {
		this.sciezka = sciezka;
		// TODO Auto-generated constructor stub
	}

	// Player2(String sciezka)
	// {
	// this.sciezka=sciezka;
	// }

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
	}

	protected void updateSpeed() {
		vX = 0;
		vY = 0;
		if (down) {
			vY = PLAYER2_SPEED;
			this.sciezka = "tank2Down.png";
		}
		if (up) {
			vY = -PLAYER2_SPEED;
			this.sciezka = "tank2Up.png";
		}
		if (left) {
			vX = -PLAYER2_SPEED;
			this.sciezka = "tank2Left.png";
		}
		if (right) {
			vX = PLAYER2_SPEED;
			this.sciezka = "tank2Right.png";
		}
	}

	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_D)
			right = true;
		if (k.getKeyCode() == KeyEvent.VK_A)
			left = true;
		if (k.getKeyCode() == KeyEvent.VK_W)
			up = true;
		if (k.getKeyCode() == KeyEvent.VK_S)
			down = true;
		updateSpeed();
	}

	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_D)
			right = false;
		if (k.getKeyCode() == KeyEvent.VK_A)
			left = false;
		if (k.getKeyCode() == KeyEvent.VK_W)
			up = false;
		if (k.getKeyCode() == KeyEvent.VK_S)
			down = false;
		updateSpeed();
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

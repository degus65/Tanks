import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Sprite {
	private static final int PLAYER_SPEED = 4;
	private boolean up = false, down = false, left = false, right = false;
	private ArrayList<Missile> missiles=new ArrayList<Missile>();
	Direction direction=Direction.UP;
	private int hp=5;
	

	Player(String sciezka) {
		super(sciezka);
		x=301; y=400; vX=0; vY=0;
	}

	public void act() {
		x += vX;
		y += vY;
		
		if(hp<=0)
		{
			Stage.setEndOfGame(true);
		}

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
		missiles.add(new Missile(direction, x+width/2, y+height/2));
		Connection.sr.fire(convertDirectionIntoInteger(), x+width/2, y+height/2);
	}

	protected void updateSpeed() {
		vX = 0;
		vY = 0;
		if (down) {
			vY = PLAYER_SPEED;
			this.setSciezka("playerDown.png");
			direction=Direction.DOWN;
		}
		else if (up) {
			vY = -PLAYER_SPEED;
			this.setSciezka("playerUp.png");
			direction=Direction.UP;
		}
		else if (left) {
			vX = -PLAYER_SPEED;
			this.setSciezka("playerLeft.png");
			direction=Direction.LEFT;
		}
		else if (right) {
			vX = PLAYER_SPEED;
			this.setSciezka("playerRight.png");
			direction=Direction.RIGHT;
		}
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
	
	public void hit()
	{
		hp--;
	}
	
	public ArrayList<Missile> getMissiles(){
		return missiles;
	}
	
	public void setX(int x)
	{
		this.x=x;
	}
	
	public void setY(int y)
	{
		this.y=y;
	}
	
	public int convertDirectionIntoInteger()
	{
		if(direction==Direction.UP)
			return 1;
		else if(direction==Direction.RIGHT)
			return 2;
		else if(direction==Direction.DOWN)
			return 3;
		else
			return 4;
	}
}

package Mechanics;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Connection.ClientReceiver;
import Connection.ServerSender;

public class Player extends Sprite {
	private static final int PLAYER_SPEED = 4;
	private boolean up = false, down = false, left = false, right = false;
	private ArrayList<Missile> missiles=new ArrayList<Missile>();
	Direction direction;
	private int hp=5;
	private ServerSender serv;
	private ClientReceiver cl;
	private int mode;
	

	Player(String sciezka, ServerSender ss) {
		super(sciezka);
		x=301; y=400; vX=0; vY=0;
		serv=ss;
		direction=Direction.UP;
		mode=1;
	}
	
	Player(String sciezka, ClientReceiver cr) {
		super(sciezka);
		x=301; y=250; vX=0; vY=0;
		cl=cr;
		direction=Direction.DOWN;
		mode=2;
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
		if(serv!=null)
			serv.coord(x, y, convertDirectionIntoInteger());
		else if(cl!=null)
			cl.coord(x, y,  convertDirectionIntoInteger());
	}
		
	public void fire()
	{
		missiles.add(new Missile(direction, x+width/2, y+height/2));
		if(serv!=null)
			serv.fire(x+width/2, y+height/2);
		else if(cl!=null)
			cl.fire(x+width/2, y+height/2);;
	}
	
	
	private void updateSpeed() {
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
	
	public void setSciezka(String sciezka) {
		if(mode==1)
			this.sciezka="p1/"+sciezka;
		else if(mode==2)
			this.sciezka="p2/"+sciezka;
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

package Mechanics;

import java.util.ArrayList;


public class Player2 extends Sprite {

	//private static final int PLAYER2_SPEED = 4;
	private int hp=5;
	//private boolean up = false, down = false, left = false, right = false;
	private ArrayList<Missile> missiles=new ArrayList<Missile>();
	Direction direction;
	private int mode;

	Player2(String sciezka, int mode) {
		super(sciezka);
		if(mode==1)
		{
			x=301; y=100; vX=0; vY=0;
			direction=Direction.UP;
		}
		else if(mode==2)
		{
			x=301; y=400; vX=0; vY=0;
			direction=Direction.DOWN;
		}
		this.mode=mode;
			
	}

	

	public int getHp() {
		return hp;
	}



	public void setHp(int hp) {
		this.hp = hp;
	}

	public void startOver()
	{
		if(mode==1)
		{
			x=301; y=100; vX=0; vY=0;
			this.setSciezka("playerUp.png");
		}	
		else if(mode==2)
		{
			x=301; y=400; vX=0; vY=0;
			this.setSciezka("playerDown.png");
		}
		hp=5;
	}

	@Override
	public void setSciezka(String sciezka) {
		if(mode==1)
			this.sciezka="p2/"+sciezka;
		else if(mode==2)
			this.sciezka="p1/"+sciezka;
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
	
	public void fire(int x, int y)
	{
		missiles.add(new Missile(direction, x, y));
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

	
	public void hit()
	{
		hp--;
	}
	
	public int getHP(){
		
		return hp;
	}


	public void setDirection(int d) {
		// TODO Auto-generated method stub
		if(d==1)
		{
			this.setSciezka("playerUp.png");
			direction=Direction.UP;
		}
		else if(d==2)
		{
			this.setSciezka("playerRight.png");
			direction=Direction.RIGHT;
		}
		else if(d==3)
		{
			this.setSciezka("playerDown.png");
			direction=Direction.DOWN;
		}
		else if(d==4)
		{
			this.setSciezka("playerLeft.png");
			direction=Direction.LEFT;
		}
	}
	
	
	


}

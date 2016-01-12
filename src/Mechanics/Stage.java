package Mechanics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Connection.ConnectionClient;
import Connection.ConnectionServer;

public class Stage extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SZEROKOSC = 640;
	public static final int WYSOKOSC = 480;
	private ArrayList<Block> blocks=new ArrayList<Block>();
	private ArrayList<Block> hp1=new ArrayList<Block>();
	private static boolean endOfGame=false;
	private boolean init=true;
	
	private ConnectionServer cs;//gdy serwer
	private ConnectionClient cc;//gdy klient
	
	public static enum STATE{
		SINGLEPLAYER,
		SERVER,
		CLIENT,
		MENU
	};
	public static STATE State = STATE.SINGLEPLAYER;
	
	
	private String background = "/img/background.png"; // sciezka do tla

	
	static Player p1 = new Player("playerUp.png");
	static Player2 p2 = new Player2("tank2Down.png");
	
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		this.addMouseListener(new MouseInput());

		g.drawImage(getBackgroundImage(), 0, 0, this); // ustawienie t³a
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, getWidth(), getHeight());
		
//		if(State == STATE.SINGLEPLAYER || State==STATE.SERVER || State==STATE.CLIENT){
//			
//			
			g.drawImage(p1.getSprite(), p1.getX(), p1.getY(), this);
			g.drawImage(p2.getSprite(), p2.getX(), p2.getY(), this); // rysuj drugiego gracza
			
			for (Block b1 : blocks) {    
	        	g.drawImage(b1.getSprite(), b1.getX(), b1.getY(), this);
			}
			
			for (Missile m1 : p1.getMissiles()) {    
	            	g.drawImage(m1.getSprite(), m1.getX(), m1.getY(), this);
	        }
			for (Missile m1 : p2.getMissiles()) {    
	        	g.drawImage(m1.getSprite(), m1.getX(), m1.getY(), this);
			}
			
			if(endOfGame==true)
			{
				g.setColor(Color.WHITE);
				g.drawString("Koniec gry", 200, 200);
			}

			
//		}else if(State == STATE.MENU){
//			//menu gry
//			menu.render(g);
//			
//		}
//		

	}

	public void updateWorld() {	
//		if(State == STATE.SINGLEPLAYER || State==STATE.SERVER || State==STATE.CLIENT)
//		{
//			
			p1.act();
			p2.act(); // drugi gracz
			
			collision();
		
			for (Missile m1 : p1.getMissiles()) {
	            Missile m = (Missile) m1;
	            m.act();
	        }
			
			for (Missile m1 : p2.getMissiles()) {
	            Missile m = (Missile) m1;
	            m.act();
	        }
			
//		}
		
	}

	public void gameLoop() {
		
		System.out.println("Stage.gameLoop()");
		
		//to bêdzie inaczej, nie w tym miejscu
		if(State==STATE.SERVER && init==true)
		{
			cs=new ConnectionServer();//gdy serwer
			p1.setServer(cs.getServer());
			init=false;
			System.out.println("init");
			//p2.setClient(cc.getClient());
		}
		else if(State==STATE.CLIENT)
		{
			cc=new ConnectionClient();//gdy klient
			//p2.setServer(cs.getServer());
			p1.setClient(cc.getClient());
			init=false;
		}
		repaint();
		updateWorld();
	}
	
	public void collision(){
		//bloki z graczem
		Rectangle r1=p1.getBounds();
		for (Block b1 : blocks) {    
        	Rectangle r2=b1.getBounds();
        	if(r1.intersects(r2))
        	{
        		if(r2.getMinY()<=r1.getMaxY() && p1.direction==Direction.DOWN)
        			p1.setY((int)r2.getMinY()-p1.getHeight());
        		
        		else if(r2.getMaxY()>=r1.getMinY() && p1.direction==Direction.UP)
        			p1.setY((int)r2.getMaxY());
        		
        		else if(r2.getMaxX()>=r1.getMinX() && p1.direction==Direction.LEFT)
        			p1.setX((int)r2.getMaxX());
        		
        		else if(r2.getMinX()<=r1.getMaxX() && p1.direction==Direction.RIGHT)
        			p1.setX((int)r2.getMinX()-p1.getWidth());
        			
        		
        	}
    }
		
		for(Block b1: blocks){   //sprawdzanie czy pociski trafiaja w bloki
			Rectangle r2=b1.getBounds();
			for(Missile m1: p1.getMissiles()){
				Rectangle r3=m1.getBounds();
				if(r2.intersects(r3))
					m1.setActive(false);
			}
		}
		
		for(Block b1: blocks){   //sprawdzanie czy pociski trafiaja w bloki
			Rectangle r2=b1.getBounds();
			for(Missile m1: p2.getMissiles()){
				Rectangle r3=m1.getBounds();
				if(r2.intersects(r3))
					m1.setActive(false);
			}
		}
		
		r1=p1.getBounds();
		for(Missile m1: p2.getMissiles()){
			Rectangle r3=m1.getBounds();
			if(r1.intersects(r3))
			{
				m1.setActive(false);
				p1.hit();
			}
				
		}
	}

	// tlo
	public Image getBackgroundImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(background));
		return i.getImage();
	}
	
	public static void setEndOfGame(boolean v)
	{
		endOfGame=v;
	
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		
//		if(State == STATE.SINGLEPLAYER){
			p1.keyPressed(k);
			p2.keyPressed(k);
			System.err.println("KEY PRESSED");
			
//		}
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		p1.keyReleased(k);
		p2.keyReleased(k);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gameLoop();
	}

	Stage() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		Timer timer = new Timer(1000 / 60, (ActionListener) this);
		timer.start();

		setFocusable(true);
		addKeyListener(this);
		
		setStage1();
	}
	
	//pusta mapa
	public void setStageClear(){
		blocks.clear();
	}
	
	//mapa1, rozmiar brick to 20x20
	public void setStage1(){
		blocks.clear();
		blocks.add(new Block("brick.png", 310, 230));
		blocks.add(new Block("brick.png", 290, 230));
		blocks.add(new Block("brick.png", 270, 230));
		blocks.add(new Block("brick.png", 230, 230));
		blocks.add(new Block("brick.png", 250, 230));
		blocks.add(new Block("brick.png", 330, 230));
		blocks.add(new Block("brick.png", 350, 230));
		blocks.add(new Block("brick.png", 370, 230));
		
		blocks.add(new Block("brick.png", 90, 130));
		blocks.add(new Block("brick.png", 110, 130));
		blocks.add(new Block("brick.png", 130, 130));
		blocks.add(new Block("brick.png", 150, 130));
		blocks.add(new Block("brick.png", 150, 150));
		blocks.add(new Block("brick.png", 150, 170));
		blocks.add(new Block("brick.png", 170, 130));
		blocks.add(new Block("brick.png", 190, 130));
		blocks.add(new Block("brick.png", 210, 130));
		
		blocks.add(new Block("brick.png", 90, 330));
		blocks.add(new Block("brick.png", 110, 330));
		blocks.add(new Block("brick.png", 130, 330));
		blocks.add(new Block("brick.png", 150, 330));
		blocks.add(new Block("brick.png", 150, 310));
		blocks.add(new Block("brick.png", 150, 290));
		blocks.add(new Block("brick.png", 170, 330));
		blocks.add(new Block("brick.png", 190, 330));
		blocks.add(new Block("brick.png", 210, 330));
		
		blocks.add(new Block("brick.png", 390, 130));
		blocks.add(new Block("brick.png", 410, 130));
		blocks.add(new Block("brick.png", 430, 130));
		blocks.add(new Block("brick.png", 450, 130));
		blocks.add(new Block("brick.png", 450, 150));
		blocks.add(new Block("brick.png", 450, 170));
		blocks.add(new Block("brick.png", 470, 130));
		blocks.add(new Block("brick.png", 490, 130));
		blocks.add(new Block("brick.png", 510, 130));
		
		blocks.add(new Block("brick.png", 390, 330));
		blocks.add(new Block("brick.png", 410, 330));
		blocks.add(new Block("brick.png", 430, 330));
		blocks.add(new Block("brick.png", 450, 330));
		blocks.add(new Block("brick.png", 450, 290));
		blocks.add(new Block("brick.png", 450, 310));
		
		blocks.add(new Block("brick.png", 470, 330));
		blocks.add(new Block("brick.png", 490, 330));
		blocks.add(new Block("brick.png", 510, 330));
	}
	
	
	public void setStage2(){
		blocks.clear();
		
		//pionowa linia brick
		int y=130;
		int setX= 450;
		for(int i=0; i<=10; i++){
			blocks.add(new Block("brick.png", setX, y));
			y=y+20;
		}
		
		//pionowa linia brick
			y=130;
			setX= 130;
			for(int i=0; i<=10; i++){
				blocks.add(new Block("brick.png", setX, y));
				y=y+20;
			}
		
		//pozioma linia brick
		int x=190;
		int setY=230;
		for(int i=0; i<=10; i++){
			blocks.add(new Block("brick.png", x, setY));
			x=x+20;
		}
		
		//pozioma
		x=150;
		setY=130;
		for(int i=0; i<=12; i++){
			blocks.add(new Block("brick.png", x, setY));
			x=x+20;
		}
		
		//pozioma
		x=190;
		setY=330;
		for(int i=0; i<=12; i++){
			blocks.add(new Block("brick.png", x, setY));
			x=x+20;
		}
	}

}

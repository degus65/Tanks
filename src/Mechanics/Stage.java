package Mechanics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Connection.ClientReceiver;
import Connection.ConnectionClient;
import Connection.ConnectionServer;
import Connection.ServerSender;

public class Stage extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SZEROKOSC = 640;
	public static final int WYSOKOSC = 480;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private ArrayList<Life> lifes = new ArrayList<Life>();
	private Timer timer;

	private static boolean endOfGame = false;

	private ConnectionServer cs;// gdy serwer
	private ConnectionClient cc;// gdy klient

	private String background = "/img/background.png"; // sciezka do tla
	private String backgroundEnd = "/img/backgroundEND.png";
	private String lifePath = "/img/life.png";

	private Player p1;
	private Player2 p2;

	private int hp1 = 0;
	private int hp2 = 0;
	boolean a = true;
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!endOfGame) {
			g.drawImage(getBackgroundImage(), 0, 0, this); // ustawienie t³a
			g.drawImage(p1.getSprite(), p1.getX(), p1.getY(), this);
			g.drawImage(p2.getSprite(), p2.getX(), p2.getY(), this); 

			for (Block b1 : blocks) {
				g.drawImage(b1.getSprite(), b1.getX(), b1.getY(), this);
			}

			for (Missile m1 : p1.getMissiles()) {
				g.drawImage(m1.getSprite(), m1.getX(), m1.getY(), this);
			}
			for (Missile m1 : p2.getMissiles()) {
				g.drawImage(m1.getSprite(), m1.getX(), m1.getY(), this);
			}

			for (Life l1 : lifes) {
				g.drawImage(l1.getSprite(), l1.getX(), l1.getY(), this);
			}

			checkHP(g);

		}

		if (endOfGame == true) {
			
			setEndGameScreen(g); 
			if(a){ 
				a = false;
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Nowa gra? Drugi gracz te¿ musi potwierdzic", "Nowa gra?", dialogButton);
				if(dialogResult == 0) {
				  //System.out.println("Yes option");
					if(cc!=null)
					{
						ClientReceiver tempClient=cc.getClient();
						tempClient.iWantAgain();
						boolean accepted=false;
						while(accepted==false)
						{
							accepted=tempClient.getAgain();
						}
					}	
					else if(cs!=null)
					{
						ServerSender tempServer=cs.getServer();
						tempServer.iWantAgain();
						boolean accepted=false;
						while(accepted==false)
						{
							accepted=tempServer.getAgain();
						}
					}
					a=true;
					endOfGame=false;
					p1.startOver();
					p2.startOver();
					setLifes();
				} else {
				  //System.out.println("No Option");
				  System.exit(1);
				} 
			}
		}
	}

	public void updateWorld() {

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
	}

	public void checkHP(Graphics g) {

		hp1 = p1.getHP();
//		 System.out.println("PLAYER1 HP: " + hp1 );

		hp2 = p2.getHP();
//		 System.out.println("PLAYER2 HP: " + hp2 );

		for (int i1 = 0; i1 < hp1; i1++) {
			int x1 = 20 + i1 * 22;
			g.drawImage(getLifeImage(), x1, 20, this);
		}

		for (int i2 = 0; i2 < hp2; i2++) {
			int x2 = 590 - i2 * 22;
			g.drawImage(getLifeImage(), x2, 20, this);
		}
	}

	public void setEndGameScreen(Graphics g) {
		g.drawImage(getBackgroundENDImage(), 0, 0, this); // ustawienie t³a
	}

	public void gameLoop() {
		repaint();
		updateWorld();
	}

	private void collisionP1withBlocks() {
		Rectangle r1 = p1.getBounds();
		for (Block b1 : blocks) {
			Rectangle r2 = b1.getBounds();
			if (r1.intersects(r2)) {
				if (r2.getMinY() <= r1.getMaxY() && p1.direction == Direction.DOWN)
					p1.setY((int) r2.getMinY() - p1.getHeight());

				else if (r2.getMaxY() >= r1.getMinY() && p1.direction == Direction.UP)
					p1.setY((int) r2.getMaxY());

				else if (r2.getMaxX() >= r1.getMinX() && p1.direction == Direction.LEFT)
					p1.setX((int) r2.getMaxX());

				else if (r2.getMinX() <= r1.getMaxX() && p1.direction == Direction.RIGHT)
					p1.setX((int) r2.getMinX() - p1.getWidth());
			}
		}
	}
	
	private void collisionP1withP2() {
		Rectangle r1 = p1.getBounds();
		Rectangle r2 = p2.getBounds();
		if (r1.intersects(r2)) {
			if (r2.getMinY() <= r1.getMaxY() && p2.direction == Direction.DOWN)
				p2.setY((int) r2.getMinY() - p2.getHeight());
			else if (r2.getMaxY() >= r1.getMinY() && p2.direction == Direction.UP)
				p2.setY((int) r2.getMaxY());
			else if (r2.getMaxX() >= r1.getMinX() && p2.direction == Direction.LEFT)
				p2.setX((int) r2.getMaxX());
			else if (r2.getMinX() <= r1.getMaxX() && p2.direction == Direction.RIGHT)
				p2.setX((int) r2.getMinX() - p2.getWidth());
		}
			if (r1.intersects(r2)) {
				if (r2.getMinY() <= r1.getMaxY() && p1.direction == Direction.DOWN)
					p1.setY((int) r2.getMinY() - p1.getHeight());
				else if (r2.getMaxY() >= r1.getMinY() && p1.direction == Direction.UP)
					p1.setY((int) r2.getMaxY());
				else if (r2.getMaxX() >= r1.getMinX() && p1.direction == Direction.LEFT)
					p1.setX((int) r2.getMaxX());
				else if (r2.getMinX() <= r1.getMaxX() && p1.direction == Direction.RIGHT)
					p1.setX((int) r2.getMinX() - p1.getWidth());
			
		}
	}

	private void collisionP1withLife() {
		Rectangle r1 = p1.getBounds();
		for (int i=0; i<lifes.size(); i++) {
			Rectangle r2 = lifes.get(i).getBounds();
			if (r1.intersects(r2)) {
				p1.setHp(p1.getHp() + 1);
				lifes.remove(i);
			}
		}
	}
	
	private void collisionP2withLife() {
		Rectangle r1 = p2.getBounds();
		for (int i=0; i<lifes.size(); i++) {
			Rectangle r2 = lifes.get(i).getBounds();
			if (r1.intersects(r2)) {
				p2.setHp(p2.getHp() + 1);
				lifes.remove(i);
			}
		}
	}
	
	private void collisionP2withBlocks() {
		Rectangle r1 = p2.getBounds();
		for (Block b1 : blocks) {
			Rectangle r2 = b1.getBounds();
			if (r1.intersects(r2)) {
				if (r2.getMinY() <= r1.getMaxY() && p2.direction == Direction.DOWN)
					p2.setY((int) r2.getMinY() - p2.getHeight());

				else if (r2.getMaxY() >= r1.getMinY() && p2.direction == Direction.UP)
					p2.setY((int) r2.getMaxY());

				else if (r2.getMaxX() >= r1.getMinX() && p2.direction == Direction.LEFT)
					p2.setX((int) r2.getMaxX());

				else if (r2.getMinX() <= r1.getMaxX() && p2.direction == Direction.RIGHT)
					p2.setX((int) r2.getMinX() - p2.getWidth());
			}
		}
	}

	private void collisionP1MissilesWithBlocks() {
		for (Block b1 : blocks) { // sprawdzanie czy pociski trafiaja w bloki
			Rectangle r2 = b1.getBounds();
			for (Missile m1 : p1.getMissiles()) {
				Rectangle r3 = m1.getBounds();
				if (r2.intersects(r3))
					m1.setActive(false);
			}
		}
	}

	private void collisionP2MissilesWithBlocks() {
		for (Block b1 : blocks) { // sprawdzanie czy pociski trafiaja w bloki
			Rectangle r2 = b1.getBounds();
			for (Missile m1 : p2.getMissiles()) {
				Rectangle r3 = m1.getBounds();
				if (r2.intersects(r3))
					m1.setActive(false);
			}
		}
	}

	private void collisionP1MissilesWithP2() {
		Rectangle r1 = p2.getBounds();
		for (Missile m1 : p1.getMissiles()) {
			Rectangle r3 = m1.getBounds();
			if (r1.intersects(r3)) {
				m1.setActive(false);
				p2.hit();
				
			}
		}
	}

	private void collisionP2MissilesWithP1() {
		Rectangle r1 = p1.getBounds();
		for (Missile m1 : p2.getMissiles()) {
			Rectangle r3 = m1.getBounds();
			if (r1.intersects(r3)) {
				m1.setActive(false);
				p1.hit();
			}
		}
	}

	private void collision() {

		collisionP1withBlocks();
		collisionP2withBlocks();

		collisionP1MissilesWithBlocks();
		collisionP2MissilesWithBlocks();

		collisionP1MissilesWithP2();
		collisionP2MissilesWithP1();

		collisionP1withLife();
		collisionP2withLife();
		
		collisionP1withP2();
	}

	// tlo
	public Image getBackgroundImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(background));
		return i.getImage();
	}

	public Image getBackgroundENDImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(backgroundEnd));
		return i.getImage();
	}

	public Image getLifeImage() {
		ImageIcon life = new ImageIcon(getClass().getResource(lifePath));
		return life.getImage();
	}

	public static void setEndOfGame(boolean v) {
		endOfGame = v;

	}

	@Override
	public void keyPressed(KeyEvent k) {

		p1.keyPressed(k);

	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		p1.keyReleased(k);
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
	
	
	public int choseStageDialog(){
		
//		Panel1 sss = new Panel1();
//		add(sss);
//		StageChoice = sss.mapa;
		Random generator = new Random();
		int i = generator.nextInt(2) + 1;
//		System.out.println("Stage: " + i);
		
		return i;
		
//		return 4;
		
	}
	

	public int createServerOrClient(int c) throws InterruptedException, ExecutionException, NumberFormatException, IOException {
		if (c == 1) {
			int chooseStage=0;
			chooseStage=choseStageDialog(); // tu wystepuje Exception!
//			int chooseStage=2;//tutaj jakaœ interakcja co do wyboru stage'a, najlepiej w innej funkci zeby przejrzyscie bylo
			p2 = new Player2("p2/playerDown.png", c);
			cs = new ConnectionServer(p2, chooseStage);
			p1 = new Player("p1/playerUp.png", cs.getServer());
			return chooseStage;
		}
		else if (c == 2) {
			p2 = new Player2("p1/playerUp.png", c);
			cc = new ConnectionClient(p2);
			p1 = new Player("p2/playerDown.png", cc.getClient());
			int s=0;
			while(s==0)
			{
				s=cc.getClient().getWhichStage();
			}
			
			return s;
		}
		return 0;
	}

	Stage(int c) throws InterruptedException, ExecutionException, NumberFormatException, IOException {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		setFocusable(true);
		addKeyListener(this);

		int st=createServerOrClient(c);
			
		if(st==1)
			setStage1();
		else if(st==2)
			setStage2();
		else if(st==3)
			setStage3();
		else if(st==4)
			setStage4();

		
		timer = new Timer(1000 / 60, (ActionListener) this);// 60 fps
		timer.start();
		
	}

	// pusta mapa
	public void setStageClear() {
		blocks.clear();
	}

	public void setLifes(){
		lifes.add(new Life("life.png", 5, 5));
		lifes.add(new Life("life.png", 610, 425));
	}
	
	public void setLifes(int x, int y){
		lifes.add(new Life("life.png", x, y));
	}
	
	
	// mapa1, rozmiar brick to 20x20
	public void setStage1() {
		blocks.clear();
		
		blocks.add(new Block("rock.png", 480, 180));
		
		blocks.add(new Block("brick2.png", 30, 360));
		blocks.add(new Block("brick2.png", 30, 330));
		blocks.add(new Block("brick2.png", 30, 300));
		
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
		setLifes();
		
	}

	public void setStage2() {
		blocks.clear();

		// pionowa linia brick
		int y = 130;
		int setX = 450;
		for (int i = 0; i <= 10; i++) {
			blocks.add(new Block("brick.png", setX, y));
			y = y + 20;
		}

		// pionowa linia brick
		y = 130;
		setX = 130;
		for (int i = 0; i <= 10; i++) {
			blocks.add(new Block("brick.png", setX, y));
			y = y + 20;
		}

		// pozioma linia brick
		int x = 190;
		int setY = 230;
		for (int i = 0; i <= 10; i++) {
			blocks.add(new Block("brick.png", x, setY));
			x = x + 20;
		}

		// pozioma
		x = 150;
		setY = 130;
		for (int i = 0; i <= 12; i++) {
			blocks.add(new Block("brick.png", x, setY));
			x = x + 20;
		}

		// pozioma
		x = 190;
		setY = 330;
		for (int i = 0; i <= 12; i++) {
			blocks.add(new Block("brick.png", x, setY));
			x = x + 20;
		}

		
		blocks.add(new Block("tankWreck2.png", 540, 120));
		setLifes();

	}
	
	
	public void setStage3() {
		blocks.clear();
		int y =0;
		int setX = 0;
		
		blocks.add(new Block("tankWreck1.png", 100, 200));
		
		y = 90;
		setX = 260;
		for (int i = 0; i <= 13; i++) {
			blocks.add(new Block("brick.png", setX, y));
			y = y + 20;
		}
		
		y = 90;
		setX = 350;
		for (int i = 0; i <= 13; i++) {
			blocks.add(new Block("brick.png", setX, y));
			y = y + 20;
		}

		
		y = 140;
		setX = 80;
		for (int i = 0; i <= 6; i++) {
			blocks.add(new Block("brick.png", setX, y));
			y = y + 20;
			setX = setX + 20;
		}
		
		y = 140;
		setX = 420;
		for (int i = 0; i <= 6; i++) {
			blocks.add(new Block("brick.png", setX, y));
			y = y + 20;
			setX = setX + 20;
		}

		setLifes(140, 180);
		setLifes(480, 220);

	}
	
	public void setStage4() {
		blocks.clear();

		int y =0;
		int x = 0;
		
		y = 80;
		x = 20;
		for (int i = 0; i <= 9; i++) {
			blocks.add(new Block("brick.png", x, y));
//			y = y +60;
			x= x +81;
		}

		y = 360;
		x = 20;
		for (int i = 0; i <= 9; i++) {
			blocks.add(new Block("brick2.png", x, y));
//			y = y +60;
			x= x +81;
		}
		

		setLifes(60, 220);
		setLifes(300, 220);
		setLifes(480, 220);

	}
	


}

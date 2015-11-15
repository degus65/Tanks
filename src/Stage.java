import java.awt.BorderLayout;
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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Stage extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SZEROKOSC = 640;
	public static final int WYSOKOSC = 480;
	private ArrayList<Block> blocks=new ArrayList<Block>();

	private String background = "/img/background.png"; // sciezka do tla

	Player p1 = new Player("playerUp.png");
	Player2 p2 = new Player2("tank.png");

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(getBackgroundImage(), 0, 0, this); // ustawienie t³a
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(p1.getSprite(), p1.getX(), p1.getY(), this);
		g.drawImage(p2.getSprite(), p2.getX(), p2.getY(), this); // rysuj drugiego gracza
		
		for (Block b1 : blocks) {    
        	g.drawImage(b1.getSprite(), b1.getX(), b1.getY(), this);
    }
		
		for (Missile m1 : p1.getMissiles()) {    
            	g.drawImage(m1.getSprite(), m1.getX(), m1.getY(), this);
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
	}

	public void gameLoop() {
		repaint();
		updateWorld();
	}
	
	public void collision(){
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
		
		//to dzia³a
		for(Block b1: blocks){   //sprawdzanie czy pociski trafiaja w bloki
			Rectangle r2=b1.getBounds();
			for(Missile m1: p1.getMissiles()){
				Rectangle r3=m1.getBounds();
				if(r2.intersects(r3))
					m1.setActive(false);
			}
		}
	}

	// tlo
	public Image getBackgroundImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(background));
		return i.getImage();
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		p1.keyPressed(k);
		p2.keyPressed(k);
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
		
		blocks.add(new Block("brick.png", 200, 200));
		blocks.add(new Block("brick.png", 227, 200));
	}

}

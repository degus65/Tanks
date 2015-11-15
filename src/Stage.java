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
		
		for (Missile m1 : p1.getMissile()) {    
            	g.drawImage(m1.getSprite(), m1.getX(), m1.getY(), this);
        }

	}

	public void updateWorld() {
		p1.act();
		p2.act(); // drugi gracz
		
		Rectangle r1=p1.getBounds();
		for (Block b1 : blocks) {    
        	Rectangle r2=b1.getBounds();
        	if(!r1.intersects(r2))
        	{
        		//tu bêdzie obs³uga kolizji ale jeszcze nie ma xD
        	}
    }
		
		for (Missile m1 : p1.getMissile()) {
            Missile m = (Missile) m1;
            m.act();
        }
	}

	public void gameLoop() {
		repaint();
		updateWorld();
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
	}

}

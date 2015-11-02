import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Stage extends JPanel implements KeyListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SZEROKOSC = 640;
	public static final int WYSOKOSC = 480;
	public long usedTime=0;
	
	Player p1=new Player("playerUp.png");
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(p1.getSprite(), p1.getX(), p1.getY(),this);
		g.setColor(Color.WHITE);
		if (usedTime > 0)
			g.drawString(String.valueOf(1000/usedTime)+" fps",5,WYSOKOSC-50);
		else 
			g.drawString("--- fps",5,WYSOKOSC-50);
		
	}
	
	public void updateWorld()
	{
		p1.act();
	}
	
	public void gameLoop()
	{
		long startTime = System.currentTimeMillis();
		updateWorld();
		repaint();
		usedTime = System.currentTimeMillis()-startTime;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
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
	
	Stage()
	{
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		Timer timer = new Timer(1000/60, (ActionListener) this);
        timer.start();
        
		setFocusable(true);
		addKeyListener(this);
	}

	
}

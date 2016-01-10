package Mechanics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Stage.SZEROKOSC /2 - 50, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Stage.SZEROKOSC /2 - 50, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Stage.SZEROKOSC /2 - 50, 350, 100, 50);
	
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		Font ftn0 = new Font("arial", Font.BOLD, 50);
		g.setFont(ftn0);
		g.setColor(Color.white);
		g.drawString("TANK", Stage.WYSOKOSC /2, 100);
		
		Font ftn1= new Font("arial", Font.BOLD, 30);
		g.setFont(ftn1);
		g.drawString("PLAY", playButton.x + 15, playButton.y +30);
		Button pbtn = new Button("playButton.png", Stage.SZEROKOSC /2 - 50, 150);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 15, helpButton.y +30);
		g2d.draw(helpButton);
		g.drawString("QUIT", quitButton.x + 15, quitButton.y +30);
		g2d.draw(quitButton);
	}
	
	
}

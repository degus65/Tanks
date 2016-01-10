package Mechanics;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MouseInput implements MouseListener {

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		
		/**
		public Rectangle playButton = new Rectangle(Stage.SZEROKOSC /2 - 50, 150, 100, 50);
		public Rectangle helpButton = new Rectangle(Stage.SZEROKOSC /2 - 50, 250, 100, 50);
		public Rectangle quitButton = new Rectangle(Stage.SZEROKOSC /2 - 50, 350, 100, 50);
		*/
		
		//play button
		if(mx >= Stage.SZEROKOSC / 2 - 50 && mx <= Stage.SZEROKOSC / 2 + 50 )
		{
			if(my >= 150 && my <= 200 ){
				//pressed PLayButton
				Stage.State = Stage.STATE.SINGLEPLAYER;
			}
		}
		//tak na razie doda³em bo mi by³o potzebne
		if(mx >= Stage.SZEROKOSC / 2 - 50 && mx <= Stage.SZEROKOSC / 2 + 50 )
		{
			if(my >= 200 && my <= 275){
				Stage.State = Stage.STATE.SERVER;
			}
		}
		if(mx >= Stage.SZEROKOSC / 2 - 50 && mx <= Stage.SZEROKOSC / 2 + 50 )
		{
			if(my >= 275 && my <= 350){
				Stage.State = Stage.STATE.CLIENT;

			}
		}
		
		//quit button
				if(mx >= Stage.SZEROKOSC / 2 - 50 && mx <= Stage.SZEROKOSC / 2 + 50 )
				{
					if(my >= 350 && my <= 500 ){
						//pressed quitButton
						System.exit(1);
					}
				}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

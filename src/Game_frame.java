import java.awt.EventQueue;
import javax.swing.JFrame;



public class Game_frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stage contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game_frame frame = new Game_frame();
					frame.setVisible(true);
					frame.contentPane.gameLoop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game_frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 800, 500);
		setSize(Stage.SZEROKOSC, Stage.WYSOKOSC);
		setResizable(false);
		contentPane = new Stage();
		add(contentPane);
		
		
	}
	

}

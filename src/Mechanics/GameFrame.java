package Mechanics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4263958977751929035L;
	
	private JPanel contentPane;
	private Stage contentPaneStage;
	private static GameFrame frame;

	public static final int SZEROKOSC = 640;
	public static final int WYSOKOSC = 480;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Stage.SZEROKOSC, Stage.WYSOKOSC);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnSingleServer = new JButton("Single / Server");
		btnSingleServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				contentPaneStage = new Stage(1);//gdy 1 tworzymy serwer
				frame.setContentPane(contentPaneStage);
				
				frame.contentPaneStage.gameLoop();
				System.out.println("STAGE");
			}
		});
		
		btnSingleServer.setBounds(254, 146, 125, 35);
		contentPane.add(btnSingleServer);
		
		JButton btnMultiClient = new JButton("Multi / Client");
		btnMultiClient.setBounds(254, 223, 125, 35);
		contentPane.add(btnMultiClient);
		
		btnMultiClient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				contentPaneStage = new Stage(2);//gdy 2 tworzymy klienta
				frame.setContentPane(contentPaneStage);
				
				frame.contentPaneStage.gameLoop();
			}
		});
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("EXIT");

				System.exit(0);
			}
		});
		btnExit.setBounds(254, 299, 125, 35);
		contentPane.add(btnExit);
	}

}

package Mechanics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4263958977751929035L;
	
	private JPanel contentPane;
	private Stage contentPaneStage;
	private static GameFrame frame;

	public static final int SZEROKOSC = 800;
	public static final int WYSOKOSC = 600;

	public static String ipAdress = "127.0.0.1";
	private JTextField textFieldIP;
	private JTextField textFieldServIp;
	

	
	
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
	public int chooseStage()
	{
		Object[] possibilities = {"1 - Pustynna burza", "2 - Wiezien labiryntu", "3 - Autostrada smierci", "4 - Iracki kociol"};
		String s = (String)JOptionPane.showInputDialog(
		                    this,
		                    "Wybierz mape:",
		                    "Wybor mapy",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    "1 - Pustynna burza");
		
			if(s.equals("1 - Pustynna burza")){
				return 1;
			}else if(s.equals("2 - Wiezien labiryntu")){
				return 2;
			}
			else if(s.equals("3 - Autostrada smierci")){
				return 3;
				}
			else
					return 4;
	}
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public GameFrame() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Stage.SZEROKOSC, Stage.WYSOKOSC);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JButton btnSingleServer = new JButton("Stworz Gre");
		btnSingleServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ipAdress = textFieldIP.getText();
				int x=chooseStage();
				//JOptionPane.showMessageDialog(contentPane, "Oczekiwanie na klienta", "Serwer", JOptionPane.INFORMATION_MESSAGE, null);
				contentPane.setVisible(false);
				try {
					contentPaneStage = new Stage(1, x);//gdy 1 tworzymy serwer
				} catch (InterruptedException | ExecutionException | NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setContentPane(contentPaneStage);
				
				frame.contentPaneStage.gameLoop();
				//System.out.println("STAGE");
			}
		});
		
		btnSingleServer.setBounds(170, 144, 125, 35);
		contentPane.add(btnSingleServer);
		
		JButton btnMultiClient = new JButton("Dolacz do gry:");
		btnMultiClient.setBounds(170, 223, 125, 35);
		contentPane.add(btnMultiClient);
		
		btnMultiClient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				try {
					contentPaneStage = new Stage(2, 0);//gdy 2 tworzymy klienta
				} catch (InterruptedException | ExecutionException | NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setContentPane(contentPaneStage);
				
				frame.contentPaneStage.gameLoop();
				
			}
		});
		
		JButton btnExit = new JButton("WYJSCIE");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("EXIT");

				System.exit(0);
			}
		});
		btnExit.setBounds(254, 299, 125, 35);
		contentPane.add(btnExit);
		
		textFieldIP = new JTextField();
		textFieldIP.setText("127.0.0.1");
		textFieldIP.setBounds(340, 230, 125, 20);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		
		textFieldServIp = new JTextField();
		textFieldServIp.setBounds(340, 151, 125, 20);
		contentPane.add(textFieldServIp);
		textFieldServIp.setColumns(10);
		try {
			textFieldServIp.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textFieldServIp.setEditable(false);
	}
}

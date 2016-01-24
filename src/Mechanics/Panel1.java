package Mechanics;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panel1 extends JPanel {
	private JTextField textField;
	public int mapa = 2;

	/**
	 * Create the panel.
	 */
	public Panel1() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(161, 119, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapa = 1;
				setVisible(false);
			}
		});
		button.setBounds(103, 180, 89, 23);
		add(button);

	}
}

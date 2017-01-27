package applet;

import javax.swing.JApplet;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.JButton;

public class Applet extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the applet.
	 */
	public Applet() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFirewall = new JLabel("Firewall");
		panel.add(lblFirewall, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(6,1, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(6, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_4.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_4.add(lblNewLabel_2);

	}

}

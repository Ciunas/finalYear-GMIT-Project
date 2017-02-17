package transalteIMS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class IMS {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMS window = new IMS();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
					}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IMS() {
		
		 JFrame frame = new JFrame();
		 LogInScreen pframe = null;
		 pframe = new LogInScreen(frame);
		 pframe.setVisible(true);
		initialize();		
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 451, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblInstantMessaging = new JLabel("Instant Messaging", SwingConstants.CENTER);
		frame.getContentPane().add(lblInstantMessaging, BorderLayout.NORTH);
	}

}

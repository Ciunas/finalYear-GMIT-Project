package transalteIMS;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class IMS_Client {

    private Socket connection;
    private DataInputStream input;
    private DataOutputStream output;
	private JFrame frame;
	private String hostname = "localhost";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMS_Client window = new IMS_Client();
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
	public IMS_Client() {

		try {

			// make connection
			connection = new Socket(hostname , 1234);

			// get streams
			input = new DataInputStream(connection.getInputStream());
			output = new DataOutputStream(connection.getOutputStream());
		}

		// catch problems setting up connection and streams
		catch (IOException ioException) {
			// clientLogger.warning("Client Stream error" + " IOExcepton"+
			// ioException);
			ioException.printStackTrace();
		}
		
		JFrame frame = new JFrame();
		LogInScreen pframe = null;
		pframe = new LogInScreen(frame, input, output);
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

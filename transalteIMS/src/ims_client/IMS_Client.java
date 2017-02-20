package ims_client;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class IMS_Client {

    private Socket clinetSocket;
	private JFrame frame;
	private String hostname = "localhost";
	private BufferedReader bReader;
	private PrintWriter dataOut;

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
			clinetSocket = new Socket(hostname , 1234);

			// get streams
			bReader = new BufferedReader(new InputStreamReader(clinetSocket.getInputStream()));
			dataOut = new PrintWriter(clinetSocket.getOutputStream());
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
		JFrame frame = new JFrame();
		LogInScreen pframe = null;
		pframe = new LogInScreen(frame, bReader, dataOut);
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

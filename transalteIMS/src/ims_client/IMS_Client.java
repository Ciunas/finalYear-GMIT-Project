package ims_client;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import ims_user.IMS_User;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * @author ciunas
 *
 */
public class IMS_Client {

    private Socket clinetSocket;
	private JFrame frame;
	private String hostname = "localhost";
	private BufferedReader bReader;
	private PrintWriter dataOut;
	ObjectInputStream in = null;
	ObjectOutputStream out  = null; 
	private JLabel label;

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
	 * Create the JFrame application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public IMS_Client() throws ClassNotFoundException, IOException {

		try {

			// make connection
			clinetSocket = new Socket(hostname , 1234);
			// get streams
			bReader = new BufferedReader(new InputStreamReader(clinetSocket.getInputStream()));
			dataOut = new PrintWriter(clinetSocket.getOutputStream());			
			in = new ObjectInputStream(clinetSocket.getInputStream());
			out = new ObjectOutputStream(clinetSocket.getOutputStream());
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
		LogInScreen pframe = null;
		pframe = new LogInScreen(frame, bReader, dataOut);
		pframe.setVisible(true);
		
		if(pframe.isCancel())
			System.exit(0);

		IMS_User user = new IMS_User();
		
		
		user = getUserObject();
		in.close();
		out.close();
		System.out.println("UserName: " + user.getName() + " User Launguage: "+ user.getLaunguage() + " Size of ArryayList: " + user.getLabel(2));
		
		initialize(user);

	}
	

	
	/**
	 * Sends a IMS_User Object to the client application after user authenticated.
	 * @return IMS_User
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private IMS_User getUserObject() throws ClassNotFoundException, IOException {
		
		System.out.println("getting user object");
		IMS_User user = (IMS_User) in.readObject();
		return user;
	}


	/**
	 * Initialize the contents of the frame.
	 * @param IMS_User
	 */
	private void initialize( IMS_User user ) {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 451, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		JLabel lblInstantMessaging = new JLabel("Instant Messaging", SwingConstants.CENTER);
		frame.getContentPane().add(lblInstantMessaging, BorderLayout.NORTH);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel();
		label.setText(user.getLabel(8));
		panel.add(label, BorderLayout.CENTER);

		
		
		
	}

}

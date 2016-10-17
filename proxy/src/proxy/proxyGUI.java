package proxy;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class proxyGUI {

	private JFrame frame;
	private JTextField textField;
	ServerSocket serverSocket = null;
    boolean listening = true;
    int portNum;
    static boolean run = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				
				try {
					proxyGUI window = new proxyGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			
		});
		
	}
	
	
	/**
	 *Create a thread to check for an incoming connection from a client
	 */
	  public class MyRunnable implements Runnable {

		    public void run(){
		       try {
		            serverSocket = new ServerSocket(portNum);
		            System.out.println("Started on: " + portNum);
		        } catch (IOException e) {
		            System.err.println("Could not listen on port: " );
		            System.exit(-1);
		        }

		        while (listening) {
		            try {
						new ProxyThread(serverSocket.accept()).start();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		        try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		  }
	

	/**
	 * Create the application.
	 */
	public proxyGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 56, 416, 328);
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		
		textField = new JTextField();
		textField.setBounds(168, 25, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JScrollPane scrollPane = new JScrollPane();
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		panel.add(scrollPane);
		
		//Create button to start server on specified thread
		JButton btnStartProxy = new JButton("Start Proxy");
		btnStartProxy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartProxy.setEnabled(false);
				portNum = Integer.parseInt(textField.getText());
				textField.setText("");
				textArea.append("Proxy server started on port: " + portNum);
				Thread thread = new Thread(new MyRunnable());
				thread.start();
				System.out.println("listen you piece of shit");
				//btnStartProxy.setEnabled(true);

			}
		});
		
		btnStartProxy.setBounds(321, 22, 117, 25);
		frame.getContentPane().add(btnStartProxy);
		
		JLabel lblEnterPortNumber = new JLabel("Enter Port Number.");
		lblEnterPortNumber.setBounds(12, 27, 154, 15);
		frame.getContentPane().add(lblEnterPortNumber);	
		
		JButton btnNewButton = new JButton("Shutdown Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(22, 396, 209, 25);
		frame.getContentPane().add(btnNewButton);
		System.out.println("over");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

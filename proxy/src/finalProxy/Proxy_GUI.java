package finalProxy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Proxy_GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	static JTextArea output;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
					 UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
//					 UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
					Proxy_GUI window = new Proxy_GUI();
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
	public Proxy_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 931, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		
		JLabel lblProxy = new JLabel("Proxy");
		panel.add(lblProxy);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblChoseIntereface = new JLabel("Enter Interface IP address for proxy to bind to:");
		panel_3.add(lblChoseIntereface);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblChoosePort = new JLabel("Enter Port number for proxy to listen on: ");
		panel_3.add(lblChoosePort);
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		output = new JTextArea();
		output.setText("Proxy Output.");
		scrollPane.setViewportView(output);
		
		JButton btnStart = new JButton("Start Proxy");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				output.setText("");
				new Thread(new proxyRunnable()).start();
			}
		});
		panel_4.add(btnStart);
		
		JButton btnStopProxy = new JButton("Stop Proxy");
		btnStopProxy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		panel_4.add(btnStopProxy);
		
	}
	
	
	/*
	 * Thread that waits for a connection to a socket and then passes that socket to another thread for processing. 
	 */
	public class proxyRunnable implements Runnable {

	    public void run() {
	        ServerSocket serverSocket = null;
	        boolean proxyRunnig = true;
	        int server_Port = 0;	
	        InetAddress server_IP = null;
	        
	        
			try {
				server_Port = Integer.parseInt(textField_1.getText());
				server_IP = InetAddress.getByName(textField.getText());
			
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			
	        try {
	            serverSocket = new ServerSocket(server_Port,50, server_IP);
	            displayInGui("Proxy Binded to interface with IP \"" + textField.getText() + "\" and listening on port \"" + textField_1.getText() + "\"");
	            //System.out.println("Started on: " + server_Port);
	        } catch (IOException e) {
	            System.exit(-1);
	        }

	        while (proxyRunnig) {
	            try {
					new Proxy_ThreadBuilder(serverSocket.accept()).start();
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
	
	
	/*
	 * Update the GUI using the EDT  and swingUtilities
	 */
	static void displayInGui(String info){
		
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	output.append(info + "\n");
		    }
		});
	}
	
	
	
	
	
	
	
	
	
	

}

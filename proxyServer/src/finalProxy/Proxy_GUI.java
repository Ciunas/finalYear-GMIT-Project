package finalProxy;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JTextPane;

public class Proxy_GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	static JTextPane output;
	boolean proxyRunnig = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					 UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
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
		frame.setBounds(100, 100, 1003, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);

		JLabel lblProxy = new JLabel("Ciunas Proxy");
		panel.add(lblProxy);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new MigLayout("", "[550px][100][grow]", "[58px]"));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(
				new TitledBorder(null, "Enter Required Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_3, "cell 0 0,alignx left,aligny top");
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);

		JLabel lblChoseIntereface = new JLabel("IP address for proxy to bind to:      ");
		panel_5.add(lblChoseIntereface);
		lblChoseIntereface.setFont(new Font("Dialog", Font.BOLD, 16));

		textField = new JTextField("");
		textField.setFont(new Font("Dialog", Font.BOLD, 16));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(textField);
		textField.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);

		JLabel lblChoosePort = new JLabel("Port number for proxy to listen on: ");
		panel_6.add(lblChoosePort);
		lblChoosePort.setFont(new Font("Dialog", Font.BOLD, 16));

		textField_1 = new JTextField("");
		textField_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_6.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Proxy_GUI.class.getResource("/resources/Proxy.png")));
		panel_2.add(lblNewLabel, "cell 1 0");

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Start/Stop", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_4, "cell 2 0,grow");

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		output = new JTextPane();
		output.setFont(new Font("Dialog", Font.PLAIN, 16));
		output.setEditable(false);
		output.setText("Proxy Output.");
		scrollPane.setViewportView(output);

		JButton btnStart = new JButton("Start Proxy");
		btnStart.setPreferredSize(new Dimension(200, 35));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_1.getText().equals("") || textField.getText().equals("")) {

					JOptionPane.showMessageDialog(frame, "Nothing entered in fields");
				} else {
					output.setText("");
					proxyRunnig = true;
					new Thread(new proxyRunnable()).start();
				}
			}
		});
		panel_4.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		panel_4.add(btnStart, "cell 0 0,alignx center,aligny center");

		JButton btnStopProxy = new JButton("Stop Proxy");
		btnStopProxy.setPreferredSize(new Dimension(200, 35));
		btnStopProxy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proxyRunnig = false;
				output.setText("Proxy Stopped");
				textField_1.setText("");
				textField.setText("");
			}
		});
		panel_4.add(btnStopProxy, "cell 1 0,alignx center,aligny center");

	}

	/**
	 * Thread that waits for a connection to a socket and then passes that
	 * socket to a backgournd thread for processing.
	 */
	public class proxyRunnable implements Runnable {

		public void run() {
			ServerSocket serverSocket = null;
			int server_Port = 0;
			InetAddress server_IP = null;

			try {
				server_Port = Integer.parseInt(textField_1.getText());
				server_IP = InetAddress.getByName(textField.getText());

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}

			try {
				serverSocket = new ServerSocket(server_Port, 50, server_IP);
				displayInGui("Proxy Binded to interface with IP \"" + textField.getText()
						+ "\" and listening on port \"" + textField_1.getText() + "\"", "BLACK");
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

	/**
	 * Update the GUI with proxy info using the EDT and swingUtilities
	 * 
	 * @param info
	 *            Data that is displayed in GUI
	 */
	static void displayInGui(String info, String colour) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Color c = null;
				if (colour.contains("BLACK")) {
					c = Color.BLACK;
				} else if (colour.contains("RED")) {
					c = Color.RED;
				} else if (colour.contains("BLUE")) {
					c = Color.BLUE;
				}

				Document doc = output.getDocument();
				try {
					Style style = output.addStyle("I'm a Style", null);
					StyleConstants.setForeground(style, c);
					doc.insertString(doc.getLength(), info + "\n", style);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
	}

}

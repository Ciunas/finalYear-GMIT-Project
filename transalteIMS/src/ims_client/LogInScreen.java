package ims_client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 * @author ciunas
 *
 */
public class LogInScreen extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnLogin;
	private JButton btnCancel;

	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField userName;
	private JPasswordField passwordField;
	
	private boolean succeeded;
	protected boolean chBox;
	private boolean cancel = false;

	public String name = null;
	public String password = null;
	public List<String> labels = new ArrayList<String>();

	private String[] launguage = { "English", "German", "Spanish", "French", "Polish" };
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	
	
	/**
	 * LogInScreen.
	 * 
	 * Creates a login panel, and authenticates a user.
	 * 
	 * @param Frame
	 * @param BufferedReader
	 * @param PrintWriter
	 */
	@SuppressWarnings("rawtypes")
	public LogInScreen(Frame parent, BufferedReader bReader, PrintWriter dataOut) {
		super(parent, "Login", true);

		setResizable(false);
		setLocation(new Point(450, 400));
		setPreferredSize(new Dimension(400, 200));
		JPanel panel = new JPanel();

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (authenticate(bReader, dataOut)) {
						succeeded = true;
						setName(getUsername());
						setPassword(getPassword());
						dispose();

					} else {
						JOptionPane.showMessageDialog(LogInScreen.this, "Invalid username or password", "Login",
								JOptionPane.ERROR_MESSAGE);
						userName.setText("");
						passwordField.setText("");
						succeeded = false;

					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cancel = true;
				dispose();
			}
		});

		JPanel bp = new JPanel();

		bp.setLayout(new GridLayout(0, 2, 0, 0));
		bp.add(btnLogin);
		bp.add(btnCancel);

		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(12, 51, 205, 55);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		panel.add(panel_1);

		lblNewLabel_3 = new JLabel("UserName", SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);

		userName = new JTextField();
		panel_1.add(userName);

		lblNewLabel_2 = new JLabel("Password", SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		panel_1.add(passwordField);

		lblNewLabel_1 = new JLabel("Lgin to IMS", SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(55, 9, 128, 30);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LogInScreen.class.getResource("/resources/Apps-Messaging-Metro-icon.png")));
		lblNewLabel.setBounds(250, 12, 128, 121);
		panel.add(lblNewLabel);

		JCheckBox rdbtnNewUser = new JCheckBox("New User");
		rdbtnNewUser.setBounds(12, 119, 99, 23);
		rdbtnNewUser.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				btnLogin.setText("New User");
				if (chBox == true) {
					chBox = false;
					btnLogin.setText("Login");
				} else {
					chBox = true;
					btnLogin.setText("New User");
				}

			}
		});
		panel.add(rdbtnNewUser);

		comboBox = new JComboBox(launguage);
		comboBox.setBounds(118, 118, 99, 24);
		panel.add(comboBox);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		pack();
	}

	
	/**
	 * authenticate.
	 * Connects to a server and authenticates user. 
	 * @param BufferedReader
	 * @param PrintWriter
	 * @return boolean
	 * @throws IOException
	 */
	private boolean authenticate(BufferedReader bReader, PrintWriter dataOut) throws IOException {

		String temp;
		dataOut.println("Start Authentication");
		dataOut.flush();
		if ((temp = bReader.readLine()).compareTo("Authentication started") == 0) {
			
			dataOut.println(getUsername());
			dataOut.println(getPassword());
			dataOut.println(String.valueOf(comboBox.getSelectedItem()));
			if (chBox == true)
				dataOut.println("New");
			else
				dataOut.println("Not New");
			
			dataOut.println("Data Sent");
			dataOut.flush();
		}
		if ((temp = bReader.readLine()).compareTo("Success") == 0) {
			return true;
		} else
			return false;

	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return userName.getText().trim();
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
}

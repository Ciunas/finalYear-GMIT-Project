package applet;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import firewallObject.FirewallRule;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Applet extends JApplet {
	public Applet() {
	}

	private static final long serialVersionUID = 1L;
	private JLabel setMessageLabel;
	private JPanel panel;
	private JPanel panel_21;
	private JLabel lblFirewall;
	private JPanel panel_1;
	private JPanel panel_12;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel_6;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_7;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JTable table;
	private JScrollPane resultTableScrollPane;
	private JPanel panel_8;
	private JTextField text;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnAdd;
	private JButton btnAdd_1;
	private JButton btnAdd_2;
	private JPanel panel_9;
	private JLabel lblSetDefaultChain;
	private JCheckBox input;
	private JCheckBox output;
	private JCheckBox forward;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JLabel lblAllowHttphttpsTraffic;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JLabel lblAllowPing;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
	private JPanel panel_10;
	private JLabel lblPortForwarding;
	private JLabel lblIpaddress;
	private JTextField textField_2;
	private JLabel lblPortToForward;
	private JTextField textField;
	private JLabel lblToPort;
	private JTextField textField_1;
	private JPanel panel_11;
	private JLabel lblAlllowLoopbackO;
	private JPanel panel_14;
	private JPanel panel_16;
	private JPanel panel_15;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_13;
	private JPanel panel_20;
	private JTabbedPane pane;
	private JPanel panel_22;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JPanel panel_23;
	private JPanel panel_24;
	private JLabel firewallLabe;
	private JPanel panel_25;
	private Object dialog;
	
	@Override
	public void init() {

		/* Create and display the applet */
		try {
			java.awt.EventQueue.invokeAndWait(new Runnable() {
				public void run() {

					initComponents();
					getContentPane().setBackground(Color.WHITE);
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the applet.
	 */
	@SuppressWarnings("serial")
	private void initComponents() {

		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(1, 1));

		lblFirewall = new JLabel("Firewall");
		panel.add(lblFirewall, BorderLayout.NORTH);

		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		pane = new JTabbedPane();
		panel.add(pane, BorderLayout.NORTH);
		panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout(0, 0));		
		panel_20 = new JPanel(new BorderLayout(0, 0));
		
		pane.addTab("1st Tab", panel_20);
		pane.addTab("2nd Tab", panel_2);
		//Tab1//////////////////////////////////////////
		
		panel_22 = new JPanel();
		panel_22.setPreferredSize(new Dimension(10, 20));
		panel_20.add(panel_22, BorderLayout.SOUTH);
		panel_22.setLayout(new GridLayout(1, 2, 0, 0));
		panel_23 = new JPanel(new GridLayout(1, 2, 0, 0));
		panel_20.add(panel_23, BorderLayout.CENTER);
		panel_24 = new JPanel();
		panel_23.add(panel_24, BorderLayout.WEST);
		ImageIcon img1 = new ImageIcon(getClass().getResource("/resources/resources/firewall-no-fill-hi.png"));
		firewallLabe = new JLabel(img1);
		panel_24.add(firewallLabe);
		panel_25 = new JPanel();
		panel_23.add(panel_25, BorderLayout.EAST);
		dialog =  new JTextArea();
		//panel_25.add(dialog);
		
		
		btnNewButton_6 = new JButton("View");
		btnNewButton_6.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_22.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					viewRule(evt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btnNewButton_5.setEnabled(true);
			}
		});

		btnNewButton_5 = new JButton("Delete");
		btnNewButton_5.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_22.add(btnNewButton_5);
		btnNewButton_5.setEnabled(false);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			try {
				deleteRule(evt);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
		});	
		
		
		//Tab1end//////////////////////////////////////
		//Tab2/////////////////////////////////////////
		
		
		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(130, 10));
		panel_3.setBorder(UIManager.getBorder("Menu.border"));
		panel_2.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(6, 1, 0, 0));
		
		ImageIcon img = new ImageIcon(getClass().getResource("/resources/add.jpg"));
		btnNewButton = new JButton(img);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				 
				String rule = text.getText();
			}
		});
		panel_3.add(btnNewButton);
		
		btnNewButton_3 = new JButton(img);
		

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_3.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton(img);
		panel_3.add(btnNewButton_4);
		
		btnAdd = new JButton(img);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_3.add(btnAdd);
		
		btnAdd_1 = new JButton(img);
		panel_3.add(btnAdd_1);
		
		btnAdd_2 = new JButton(img);
		panel_3.add(btnAdd_2);

		panel_7 = new JPanel();
		panel_7.setLayout(new GridLayout(7, 1, 0, 0));
		panel_2.add(panel_7, BorderLayout.CENTER);
		
		panel_21 = new JPanel();
		panel_21.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_21);
		panel_21.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_21.add(panel_13);
		
		lblSetDefaultChain = new JLabel("Set default Chain policies");
		panel_13.add(lblSetDefaultChain);
		lblSetDefaultChain.setFont(new Font("FreeSerif", Font.BOLD, 22));
		
		panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Choose", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_21.add(panel_12);
		
		input = new JCheckBox("Input");
		input.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_12.add(input);
		
		output = new JCheckBox("Output");
		output.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_12.add(output);
		
		forward = new JCheckBox("Forward");
		forward.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_12.add(forward);
		
		panel_4 = new JPanel();
		panel_4.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground")));
		panel_4.add(panel_14);
		
		lblNewLabel_1 = new JLabel("Alllow  ssh incoming/outgoing");
		panel_14.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Choose", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_4.add(panel_16);
		
		chckbxNewCheckBox_2 = new JCheckBox("Incoming");
		panel_16.add(chckbxNewCheckBox_2);
		chckbxNewCheckBox_2.setFont(new Font("Dialog", Font.BOLD, 18));
		
		chckbxNewCheckBox_3 = new JCheckBox("Outgoing");
		panel_16.add(chckbxNewCheckBox_3);
		chckbxNewCheckBox_3.setFont(new Font("Dialog", Font.BOLD, 18));
		
		panel_5 = new JPanel();
		panel_5.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_15 = new JPanel();
		panel_5.add(panel_15);
		
		lblAllowHttphttpsTraffic = new JLabel("Allow http/https traffic");
		panel_15.add(lblAllowHttphttpsTraffic);
		lblAllowHttphttpsTraffic.setFont(new Font("Dialog", Font.BOLD, 18));
		
		panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Choose", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_5.add(panel_17);
		
		chckbxNewCheckBox = new JCheckBox("Http");
		panel_17.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setFont(new Font("Dialog", Font.BOLD, 18));
		
		chckbxNewCheckBox_1 = new JCheckBox("Https");
		panel_17.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setFont(new Font("Dialog", Font.BOLD, 18));
		
		panel_6 = new JPanel();
		panel_6.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_18 = new JPanel();
		panel_6.add(panel_18);
		
		lblAllowPing = new JLabel("Allow ping ");
		panel_18.add(lblAllowPing);
		lblAllowPing.setFont(new Font("Dialog", Font.BOLD, 18));
		
		panel_19 = new JPanel();
		panel_19.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Choose", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_6.add(panel_19);
		
		chckbxNewCheckBox_4 = new JCheckBox("Outside to Inside");
		panel_19.add(chckbxNewCheckBox_4);
		chckbxNewCheckBox_4.setFont(new Font("Dialog", Font.BOLD, 18));
		
		chckbxNewCheckBox_5 = new JCheckBox("Inside to Outside");
		panel_19.add(chckbxNewCheckBox_5);
		chckbxNewCheckBox_5.setFont(new Font("Dialog", Font.BOLD, 18));
		
		panel_10 = new JPanel();
		panel_10.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_10);
		
		lblPortForwarding = new JLabel("Port Forwarding");
		lblPortForwarding.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_10.add(lblPortForwarding);
		
		lblIpaddress = new JLabel("Ipaddress");
		lblIpaddress.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_10.add(lblIpaddress);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_10.add(textField_2);
		textField_2.setColumns(10);
		
		lblPortToForward = new JLabel("port to forward");
		lblPortToForward.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_10.add(lblPortToForward);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_10.add(textField);
		textField.setColumns(10);
		
		lblToPort = new JLabel("to Port");
		lblToPort.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_10.add(lblToPort);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_10.add(textField_1);
		textField_1.setColumns(10);
		
		panel_9 = new JPanel();
		panel_9.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Block a Specific ip-address");
		lblNewLabel.setFont(new Font("FreeSerif", Font.BOLD, 22));
		panel_9.add(lblNewLabel, BorderLayout.WEST);
		
		text = new JTextField();
		text.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_9.add(text, BorderLayout.CENTER);
		
		panel_11 = new JPanel();
		panel_11.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_11);
		
		lblAlllowLoopbackO = new JLabel("Allow Loopback Connections");
		lblAlllowLoopbackO.setFont(new Font("FreeSerif", Font.BOLD, 22));
		panel_11.add(lblAlllowLoopbackO);

		panel_8 = new JPanel(new GridLayout(1, 2));
		panel_8.setPreferredSize(new Dimension(0, 20));
		panel_2.add(panel_8, BorderLayout.SOUTH);

		btnNewButton_1 = new JButton("View");
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_8.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					viewRule(evt);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				btnNewButton_2.setEnabled(true);
			}
		});

		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_8.add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					deleteRule(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});	
		panel_6 = new JPanel();
		
		panel_1.add(panel_6, BorderLayout.SOUTH);

		resultTableScrollPane = new JScrollPane();
		table = new JTable();
		table.setBackground(Color.WHITE);
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(255, 0, 0));
		header.setForeground(Color.WHITE);
		panel_6.setLayout(new BorderLayout(0, 0));

		resultTableScrollPane.setBackground(new Color(255, 255, 255));
		resultTableScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Rules",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Calibri", 1, 14), new Color(51, 153, 255))); // NOI18N

		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(new Object[][] { { null } }, new String[] { "NUM", "RULE", }) {
			Class[] types = new Class[] { int.class, String.class };
			boolean[] canEdit = new boolean[] { false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		});

		table.setFillsViewportHeight(true);
		table.setGridColor(new Color(51, 102, 255));
		table.setInheritsPopupMenu(true);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		resultTableScrollPane.setViewportView(table);
		resultTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setWidth(5);
		columnModel.getColumn(1).setPreferredWidth(800);
		panel_6.add(resultTableScrollPane, BorderLayout.SOUTH);

		panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		setMessageLabel = new JLabel();
		setMessageLabel.setBackground(new Color(51, 153, 255));
		setMessageLabel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
		setMessageLabel.setForeground(new Color(255, 0, 0));
		setMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		setMessageLabel.setText("Test");
		panel_5.add(setMessageLabel);
		clearResults();

	}

	/**
	 * Delete Rules added already
	 * 
	 * @throws IOException
	 */
	private void deleteRule(ActionEvent evt) throws IOException {
		
		URL link = new URL("http://192.168.122.112:8080/firewallservlet/firewallDeleteRule");
		//URL link = new URL( getCodeBase() + "/firewallDeleteRule" );
		//URL link = new URL("http://localhost:8080/FirewallDeleteRule");
		HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();
		urlconnection.setDoOutput(true);
		urlconnection.setDoInput(true);
		urlconnection.setUseCaches(false);
		urlconnection.setDefaultUseCaches(false);
		urlconnection.setRequestProperty("Content-Type", "application/octet-stream");
		String rule =  null;
		try {
			int row = table.getSelectedRow();
			if (row < 0) {
				setMessageLabel.setText("No Row Selected");
				return;
			}

			ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
			rule = (String) table.getValueAt(row, 1);
			FirewallRule r = new FirewallRule(rule);
			oos.writeObject(r); // send the object
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());
			int count = ois.readInt(); // read back the number of rows deleted
			oos.close();
			ois.close();

			DefaultTableModel tm = (DefaultTableModel) table.getModel();
			tm.removeRow(row); // remove the row from the screen

		} catch (IOException e) {
			e.printStackTrace();
			setMessageLabel.setText("Error processing request");
		} finally {
			setMessageLabel.setText("Rule \"" + rule  + "\" Deleted");
		}
	}

	/**
	 * View Rules added already
	 * 
	 * @throws ClassNotFoundException
	 */
	private void viewRule(ActionEvent evt) throws ClassNotFoundException {

		clearResults();
		DefaultTableModel tm = (DefaultTableModel) table.getModel();

		try {
			
			URL link = new URL("http://192.168.122.112:8080/firewallservlet/firewallViewRules");
			//URL link = new URL("http://localhost:8080/FirewallViewRules");
			//URL link = new URL( getCodeBase() + "/firewallViewRules");
			HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();

			urlconnection.setDoOutput(true);
			urlconnection.setDoInput(true);
			urlconnection.setUseCaches(false);
			urlconnection.setDefaultUseCaches(false);
			urlconnection.setRequestProperty("Content-Type", "application/octet-stream");
			ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());

			while (true) {

				FirewallRule r = (FirewallRule) ois.readObject();
				if (r.isEnd()) // true boolean indicates last rule
					break;
				else {

					Object[] row = { tm.getRowCount(), r.getRule() };
					tm.addRow(row);
				}
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			setMessageLabel(tm.getRowCount() + " Rules in database");
		}
		
		//-A INPUT -s 65.55.44.100 -j DROP
	}

	/**
	 * Clear the Update tab fields
	 * 
	 * @param evt
	 */
	private void addRule(java.awt.event.ActionEvent evt) throws IOException {
		Integer count = null;
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		FirewallRule r = null;
		try {
			URL link = new URL("http://192.168.122.112:8080/firewallservlet/firewallAddRule");
			//URL link = new URL("http://localhost:8080/servlet/FirewallRuleAdd");
			HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();

			urlconnection.setDoOutput(true);
			urlconnection.setDoInput(true);
			urlconnection.setUseCaches(false);
			urlconnection.setDefaultUseCaches(false);

			// Specify the content type that we will send binary data
			urlconnection.setRequestProperty("Content-Type", "application/octet-stream");

			ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
			String newRule = text.getText();
			r = new FirewallRule(newRule);
			oos.writeObject(r);
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());
			count = ois.readInt(); // read back int
			oos.close();
			ois.close();

		} catch (Exception ex) {
			setMessageLabel("Unable to process request " + ex.toString());
		} finally {
			Object[] row = { tm.getRowCount(), r.getRule() };
			tm.addRow(row);
			if(count.compareTo(0) >= 0)
				setMessageLabel("Rule added");
			else
				setMessageLabel("Error when adding rule.");
		}

	}

	/**
	 * setMessageLabel
	 * 
	 * changes the text in the message label at the bottom of the applet.
	 * 
	 * @param msg
	 *            String to be placed in the message label
	 */
	public void setMessageLabel(String msg) {

		SwingUtilities.invokeLater(new Runnable() { // inner class to ensure GUI
													// updates properly

			public void run() {
				setMessageLabel.setText(msg);
			}
		} // end inner class
		); // end call to SwingUtilities.invokeLater

	}

	//Method to clear the rule table of any data.
	public void clearResults() {
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		tm.setRowCount(0); // clear the table
	}
	

}

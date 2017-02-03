package applet;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import firewallObject.FirewallRule;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Applet extends JApplet {
	public Applet() {
	}

	private static final long serialVersionUID = 1L;
	private JLabel setMessageLabel;
	private JPanel panel;
	private JLabel lblFirewall;
	private JPanel panel_1;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
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
		panel.setLayout(new BorderLayout(0, 0));

		lblFirewall = new JLabel("Firewall");
		panel.add(lblFirewall, BorderLayout.NORTH);

		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(6, 1, 0, 0));

		lblNewLabel = new JLabel("New label");
		panel_4.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("New label");
		panel_4.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("New label");
		panel_4.add(lblNewLabel_2_1);

		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(6, 1, 0, 0));

		btnNewButton = new JButton("Add Rule");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					
					addRule(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		panel_3.add(btnNewButton);

		panel_7 = new JPanel();
		panel_7.setLayout(new GridLayout(6, 1, 0, 0));
		
		text = new JTextField();
		panel_7.add(text);
		
		panel_2.add(panel_7, BorderLayout.CENTER);

		panel_8 = new JPanel(new GridLayout(1, 2));
		panel_2.add(panel_8, BorderLayout.SOUTH);

		btnNewButton_1 = new JButton("View");
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
		panel_6.add(resultTableScrollPane, BorderLayout.CENTER);

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

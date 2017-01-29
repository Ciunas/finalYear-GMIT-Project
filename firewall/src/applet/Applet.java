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
	private JTable table;
	private JScrollPane resultTableScrollPane;

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
		// panel_6.add(table);

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

		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					AddRule(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		panel_3.add(btnNewButton);

		btnNewButton_1 = new JButton("New button");
		panel_3.add(btnNewButton_1);

		panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.CENTER);

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
		table.setModel(
				new DefaultTableModel(new Object[][] { { null } },
						new String[] { "ID", }) {
					Class[] types = new Class[] { String.class };
					boolean[] canEdit = new boolean[] { false};

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
		table.setPreferredSize(new java.awt.Dimension(30, 16));
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		resultTableScrollPane.setViewportView(table);

		//resultTableScrollPane.setBounds(30, 10, 70, 300);
		resultTableScrollPane.setPreferredSize(new java.awt.Dimension(360, 160));
		panel_6.add(resultTableScrollPane, BorderLayout.CENTER);

		panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		setMessageLabel = new JLabel();
		// Label.setBackground(new Color(51, 153, 255));
		// Label.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
		// Label.setForeground(new Color(255, 255, 255));
		setMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		setMessageLabel.setText("Test");
		panel_5.add(setMessageLabel);

	}

	/**
	 * 
	 * Clear the Update tab fields
	 * 
	 * @param evt
	 */
	private void AddRule(java.awt.event.ActionEvent evt) throws IOException {

		// setMessageLabel("Connected");

		try {

			URL link = new URL("http://localhost:8080/FirewallRuleAdd");
			// System.out.println("connecting to tomcat");
			HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();

			urlconnection.setDoOutput(true);
			urlconnection.setDoInput(true);
			urlconnection.setUseCaches(false);
			urlconnection.setDefaultUseCaches(false);

			// Specify the content type that we will send binary data
			urlconnection.setRequestProperty("Content-Type", "application/octet-stream");

			ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
			Rules r = new Rules("help", "help", "help", 2, "Help");
			oos.writeObject(r);
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());
			// while(true)
			// {
			// Customer c = ( Customer)ois.readObject();
			// if(c.id.length() == 0) // empty id indicates last customer
			// break;
			// else
			// {
			// // Object[] row =
			// {c.id,c.firstName,c.lastName,c.address1,c.address2,c.city,c.state,c.zip,c.phone};
			//
			// }
			// }
			oos.close();
			ois.close();

		} catch (Exception ex) {
			setMessageLabel("Unable to process request " + ex.toString());
		} finally {
			setMessageLabel("Connected2");
		}

	}// GEN-LAST:event_AddRule

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

}

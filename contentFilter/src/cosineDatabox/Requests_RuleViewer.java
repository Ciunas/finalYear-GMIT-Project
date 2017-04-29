package cosineDatabox;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import org.jdesktop.swingx.prompt.PromptSupport;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 * @author ciunas
 *
 */

public class Requests_RuleViewer {

	private JFrame frame;
	private JTable table;
	private JPanel panel_1;
	private JScrollPane resultTableScrollPane;
	public Vector<String> myVector = new Vector<String>();
	private JTextField textField;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				try {
					Requests_RuleViewer window = new Requests_RuleViewer();
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
	public Requests_RuleViewer() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setName("");
		frame.setBounds(100, 100, 574, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[100px,grow][grow][grow]"));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "cell 0 0,grow");
		panel_3.setLayout(new MigLayout("", "[grow][]", "[100px,grow]"));

		JButton btnAdd = new JButton("Check Phrase");
		panel_3.add(btnAdd, "cell 1 0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				addRule();

			}

		});
		panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 1,grow");

		textField = new JTextField();
		panel_3.add(textField, "cell 0 0,growx");
		textField.setColumns(10);
		PromptSupport.setPrompt("Enter New Phrase", textField);

		resultTableScrollPane = new JScrollPane();
		table = new JTable();
		table.setBackground(Color.WHITE);
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(0, 255, 50)); // change colour of colume
														// headers
		header.setForeground(Color.BLACK);
		panel_1.setLayout(new BorderLayout(0, 0));

		resultTableScrollPane.setBackground(new Color(255, 255, 255));
		resultTableScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Phrases",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Calibri", 1, 16), new Color(51, 153, 255))); // NOI18N

		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(new Object[][] { { null } }, new String[] { "NUM", "RULE", }) {

			private static final long serialVersionUID = 1L;

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
		panel_1.add(resultTableScrollPane, BorderLayout.CENTER);
		clearResults();
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "View/Delete",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_4, "cell 0 2,grow");
		panel_4.setLayout(new MigLayout("", "[50px][][100px][50px][][50px][100px][][50px]", "[]"));

		JPanel lblViewAllRules = new JPanel();
		lblViewAllRules.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(lblViewAllRules, "cell 1 0");
		lblViewAllRules.setLayout(new BorderLayout(0, 0));

		JLabel lblViewAllRule = new JLabel("View All Rules");
		lblViewAllRules.add(lblViewAllRule);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.RED, null));
		panel_4.add(panel_2, "cell 2 0,grow");
		panel_2.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("View");
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewRules();
			}
		});

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.add(panel_6, "cell 4 0,grow");
		panel_6.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("");
		panel_6.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Requests_RuleViewer.class.getResource("/resources/linux_tox-128.png")));

		JPanel lblDeleteARule = new JPanel();
		lblDeleteARule.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.add(lblDeleteARule, "cell 6 0");
		lblDeleteARule.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("Delete A Rule");
		lblDeleteARule.add(lblNewLabel_2);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.RED, null));
		panel_4.add(panel_5, "cell 7 0,grow");
		panel_5.setLayout(new BorderLayout(0, 0));

		JButton btnDelete = new JButton("Delete");
		panel_5.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				removeWord();
			}
		});

		JLabel lblNewLabel = new JLabel("Rule Viewer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
	}

	
	/**
	 * viewRules
	 * 
	 * publishes all phrases contained in the content phrases file the content filter uses to a
	 * JPanel, using swing utilities to make it thread safe.
	 */
	private void viewRules() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				myVector.clear();

				try {

					BufferedReader br = new BufferedReader(new FileReader("/home/ciunas/RULES.txt"));
					String temp = null;
					while ((temp = br.readLine()) != null) {
						myVector.add(temp);
					}
					br.close();
				} catch (IOException ie) {
					System.out.println("exception");
				}

				clearResults();
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				for (int i = 0; i < myVector.size(); i++) {
					Object[] row = { tm.getRowCount(), myVector.get(i) };
					tm.addRow(row);
				}

			}
		});

	}

	
	/**
	 * removeWord
	 * 
	 * removes a highlighted phrase in the Jtable, from the text file containing
	 * all the content filter phrases.
	 */
	private void removeWord() {

		int row = table.getSelectedRow();

		if (row < 0) {
			textField.setText("No Row Selected");
			return;
		}

		String temp = (String) table.getValueAt(row, 1);
		try {
			PrintWriter writer = new PrintWriter(new File("/home/ciunas/RULES.txt"));

			for (int i = 0; i < myVector.size(); i++) {
				if (myVector.get(i).contains(temp)) {

				} else {
					writer.print(myVector.get(i) + "\n");
					writer.flush();

				}

			}
			writer.close();
			viewRules();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * addRule
	 * 
	 * adds a new phrase to the file containing all the phrases used by the
	 * content filter, gives the user a list of synonoums realating to phrase
	 * and option to add them to list also
	 */
	private void addRule() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String temp = textField.getText();
				textField.setText("");
				if (!temp.isEmpty()) {
					try {

						Requests_Synonyms rq = new Requests_Synonyms();
						String[] parts = rq.requests_Synonyms(temp, "en_US", "password", "xml");

						PrintWriter writer = new PrintWriter(
								new FileOutputStream(new File("/home/ciunas/RULES.txt"), true));
						writer.append(temp + "\n");
						int selectedOption = JOptionPane.showConfirmDialog(frame, parts, "Add Generated Phrases",
								JOptionPane.YES_NO_OPTION);
						if (selectedOption == JOptionPane.YES_OPTION) {

							for (int i = 0; i < parts.length; i++) {
								writer.append(parts[i] + "\n");
							}
						}
						writer.flush();
						writer.close();
						viewRules();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "No Entry in field");
				}
			}
		});
	}

	/**
	 * clearResults
	 * 
	 * Method to clear the rule table of any data.
	 */
	public void clearResults() {

		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		tm.setRowCount(0); // clear the table
	}

}

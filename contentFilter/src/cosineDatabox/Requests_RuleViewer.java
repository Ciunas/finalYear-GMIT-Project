package cosineDatabox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
		frame.setBounds(100, 100, 606, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[100px,grow][grow][grow]"));
		
		textField = new JTextField();
		panel.add(textField, "cell 0 0,growx");
		textField.setColumns(10);
		panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 1 1 2,grow");

		resultTableScrollPane = new JScrollPane();
		table = new JTable();
		table.setBackground(Color.WHITE);
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(255, 255, 0));			//change colour of colume headers
		header.setForeground(Color.BLACK);
		panel_1.setLayout(new BorderLayout(0, 0));

		resultTableScrollPane.setBackground(new Color(255, 255, 255));
		resultTableScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Words",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Calibri", 1, 16), new Color(51, 153, 255))); // NOI18N

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
		panel_1.add(resultTableScrollPane, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Rule Viewer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new MigLayout("", "[]", "[grow][grow][grow][grow][grow]"));

		JButton btnNewButton = new JButton("View");
		panel_2.add(btnNewButton, "cell 0 1");

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				removeWord();
			}
		});
		panel_2.add(btnDelete, "cell 0 2");

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRule();
			}
			
		});
		panel_2.add(btnAdd, "cell 0 3");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewRules();
			}
		});
	}

	private void viewRules() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				myVector.clear();
				
				try {
					// opening file in read mode using BufferedReader stream
					BufferedReader br = new BufferedReader(new FileReader("/home/ciunas/RULES.txt"));
					String temp = null;
					while ((temp = br.readLine()) != null) {
						myVector.add(temp);
					}
					br.close(); // closing BufferedReader stream
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

	private  void removeWord() {
		
		int row = table.getSelectedRow();
		
//		if (row < 0) {
//			setMessageLabel.setText("No Row Selected");
//			return;
//		}
		
		String temp = (String) table.getValueAt(row, 1);
		System.out.println("value chooesn" + temp);
		 try {
			PrintWriter writer =  new PrintWriter(  new File("/home/ciunas/RULES.txt"));
			
			for (int i = 0; i < myVector.size(); i++) {
				if( myVector.get(i).contains(temp)){
					
				}else{
					System.out.println("Printing rule");
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

	private void addRule() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String temp = textField.getText();
				textField.setText("");
				System.out.println(temp);
				if (!temp.isEmpty()) {
					try {
						System.out.println("here");
						PrintWriter writer = new PrintWriter(
								new FileOutputStream(new File("/home/ciunas/RULES.txt"), true));
						writer.append(temp + "\n");
						writer.flush();
						writer.close();
						viewRules();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	// Method to clear the rule table of any data.
	public void clearResults() {
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		tm.setRowCount(0); // clear the table
	}


}

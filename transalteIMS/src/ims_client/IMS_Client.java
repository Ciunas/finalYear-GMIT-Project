package ims_client;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import ims_server.IMS_Server_DataAccess;
import ims_server.IMS_Server_DataBaseAccess;
import ims_user.IMS_User;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;

/**
 * @author ciunas
 *
 */
public class IMS_Client {

	private IMS_Server_DataAccess database;
	private Socket clinetSocket;
	private JFrame frmInstantMessaginService;
	private String hostname = "192.168.122.228";
	private BufferedReader bReader;
	private PrintWriter dataOut;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	private JTable table;
	DefaultTableModel tableModel;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblLoggedInAs;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					 //UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
					// UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
					// UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					// UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					// UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					//UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
					// UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
					// UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
					 UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
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
					IMS_Client window = new IMS_Client();
					window.frmInstantMessaginService.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the JFrame application.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public IMS_Client() throws ClassNotFoundException, IOException {

		IMS_User user = new IMS_User();
		try {

			// make connection
			clinetSocket = new Socket(hostname, 1234);
			// get streams
			bReader = new BufferedReader(new InputStreamReader(clinetSocket.getInputStream()));
			dataOut = new PrintWriter(clinetSocket.getOutputStream());
			in = new ObjectInputStream(clinetSocket.getInputStream());
			out = new ObjectOutputStream(clinetSocket.getOutputStream());
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		IMS_Client_LogInScreen pframe = null;
		pframe = new IMS_Client_LogInScreen(frmInstantMessaginService, bReader, dataOut);
		pframe.setVisible(true);

		if (pframe.isCancel())
			System.exit(0);

		user = getUserObject();
		in.close();
		out.close();
		System.out.println("UserName: " + user.getName() + " User Launguage: " + user.getLaunguage()
				+ " Size of ArryayList: " + user.labels.size());

		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param IMS_User
	 *            takes a user objcet
	 * @throws UnknownHostException
	 */
	private void initialize(IMS_User user) throws UnknownHostException {

		frmInstantMessaginService = new JFrame();

		frmInstantMessaginService.setBounds(100, 100, 554, 408);
		frmInstantMessaginService.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frmInstantMessaginService.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[grow][][grow][::25px]"));

		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));

		lblLoggedInAs = new JLabel(user.getLabel(7) + " " + user.getName());
		panel_2.add(lblLoggedInAs, "cell 0 0 1 2");

		scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 1,grow");

		String[] columns = new String[] { // headers for the table
				user.getLabel(3), user.getLabel(8), "IP" };

		// create my table using table modeel so rows does not need to be
		// specified
		tableModel = new DefaultTableModel(columns, 0); // The 0 argument is
														// number rows.
		table = new JTable(tableModel);

		for (int i = 0; i < user.onlineUsers.size(); i += 2) {

			Object[] objs = { i + 1, user.getOnlineUsers(i), user.getOnlineUsers(i + 1) };

			tableModel.addRow(objs);
		}

		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 2,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow]"));

		frmInstantMessaginService.setTitle(user.getLabel(9));

		JButton btnConnect = new JButton(user.getLabel(2));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRowIndex = table.getSelectedRow();
				String name = (String) table.getModel().getValueAt(selectedRowIndex, 0);
				String ip = (String) table.getModel().getValueAt(selectedRowIndex, 1);

				IMS_Client_ClientConnectThreadGUI cThreadframe = new IMS_Client_ClientConnectThreadGUI("Frank",
						"ws://localhost:8887");
				new Thread(cThreadframe).start();
			}
		});
		panel_1.add(btnConnect, "cell 0 0,growx");

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3, "cell 0 3,grow");
		panel_3.setLayout(new MigLayout("", "[144px][grow][]", "[16px]"));

		lblNewLabel = new JLabel(user.getLabel(4) + " Ciunas Bennett");
		panel_3.add(lblNewLabel, "cell 0 0,alignx left,aligny top");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Cantarell", Font.BOLD | Font.ITALIC, 12));

		btnNewButton = new JButton(user.getLabel(6));
		btnNewButton.setMaximumSize(new Dimension(112, 20));
		btnNewButton.setPreferredSize(new Dimension(111, 20));
		panel_3.add(btnNewButton, "cell 2 0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				updateStatus(user.getName(), 0);
			}
		});

		startWebsocketServer();

		frmInstantMessaginService.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				// Create database connection
				try {
					database = new IMS_Server_DataBaseAccess();
				} catch (Exception exception) {
					exception.printStackTrace();
					System.exit(1);
				}
				database.changeStatus(user.getName(), 0);
				database.close();

			}
		});
	}

	/**
	 * Sends a IMS_User Object to the client application after user
	 * authenticated.
	 * 
	 * @return IMS_User returns a user object
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private IMS_User getUserObject() throws ClassNotFoundException, IOException {

		System.out.println("getting user object");
		IMS_User user = (IMS_User) in.readObject();
		System.out.println(user.onlineUsers.size());
		return user;
	}

	/**
	 * Starts a websocket server thread, on port 8887.
	 * 
	 * @throws UnknownHostException
	 */
	private void startWebsocketServer() throws UnknownHostException {

		new Thread(new IMS_Client_ChatServer(8887)).start();

	}


	/**
	 * updates the status of the user in a database
	 * @param name of user to chage status of
	 * @param status new status to change to 
	 */
	void updateStatus(String name, int status) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				// Create database connection
				try {
					database = new IMS_Server_DataBaseAccess();
				} catch (Exception exception) {
					exception.printStackTrace();
					System.exit(1);
				}

				if (JOptionPane.showConfirmDialog(frmInstantMessaginService, "Are you sure to close this window?",
						"Really Closing?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					database.changeStatus(name, status);
					database.close();
					System.exit(0);
				}

			}
		});
	}

	/**
	 * Not yet implemented will update users online in real time when done.
	 * 
	 */
	void updateOnlineUsers() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

			}
		});
	}

}

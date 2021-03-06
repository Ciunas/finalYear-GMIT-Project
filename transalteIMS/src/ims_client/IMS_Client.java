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
import ims_user.IMS_User;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
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

	private String ip = "192.168.122.228";
	private Socket clinetSocket;
	private JFrame frmInstantMessaginService;
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
	private String name;
	private IMS_Client_PushConnection cThread;
	private boolean flag = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
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
		IMS_Client_LogInScreen pframe = null;
		IMS_User user = new IMS_User();
		try {

			// make connection
			clinetSocket = new Socket(ip, 1234);
			// get streams
			bReader = new BufferedReader(new InputStreamReader(clinetSocket.getInputStream()));
			dataOut = new PrintWriter(clinetSocket.getOutputStream());
			in = new ObjectInputStream(clinetSocket.getInputStream());
			out = new ObjectOutputStream(clinetSocket.getOutputStream());
			flag = true;
		} catch (IOException ioException) {
			ioException.printStackTrace();
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JOptionPane.showMessageDialog(frmInstantMessaginService, "Exception", "No Server Available", JOptionPane.ERROR_MESSAGE);
					flag = false;
				}
			});
		}

		if(flag){
			
			pframe = new IMS_Client_LogInScreen(frmInstantMessaginService, bReader, dataOut);
			pframe.setVisible(true);

			if (pframe.isCancel())
				System.exit(0);

			user = getUserObject();
			in.close();
			out.close();
			// System.out.println("UserName: " + user.getName() + " User
			// Launguage: " + user.getLaunguage()
			// + " Size of ArryayList: " + user.labels.size());

			initialize(user);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param IMS_User
	 *            takes a user objcet
	 * @throws UnknownHostException
	 * @wbp.parser.entryPoint
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

		// create my table using table model so rows does not need to be
		// specified
		tableModel = new DefaultTableModel(columns, 0); // The 0 argument is
														// number rows.
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 2,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow]"));

		frmInstantMessaginService.setTitle(user.getLabel(9));

		JButton btnConnect = new JButton(user.getLabel(2));
		btnConnect.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {

				int selectedRowIndex = table.getSelectedRow();
				name = (String) table.getModel().getValueAt(selectedRowIndex, 1);
				String ip = (String) table.getModel().getValueAt(selectedRowIndex, 2);
				System.out.println(user.getLaunguage());
				IMS_Client_ClientConnectThreadGUI cThreadframe = new IMS_Client_ClientConnectThreadGUI(user, name ,
						"ws://"+ ip + ":8887");
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

				updateStatus( 0 , user);
				
			}
		});

		startWebsocketServer( 8887,user);

		frmInstantMessaginService.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				cThread.sentMessage( "#" + user.getName() );
				cThread.setRun(false); 
							
			}
		});
		
		cThread = new IMS_Client_PushConnection( true , 
				"ws://"+ ip + ":8888" , tableModel, user.getName());
		new Thread(cThread).start();
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
	private void startWebsocketServer(int port,  IMS_User user) throws UnknownHostException {

		new Thread(new IMS_Client_ChatServer(port, user)).start();

	}


	/**
	 * updates the status of the user in a database
	 * @param name of user to chage status of
	 * @param status new status to change to 
	 */
	void updateStatus(int status, IMS_User user) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
 
				if (JOptionPane.showConfirmDialog(frmInstantMessaginService, user.getLabel(12),
						"", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					cThread.sentMessage( "#" + user.getName() );
					cThread.setRun(false); 
					System.exit(0);
				}
			}
		});
	}

}

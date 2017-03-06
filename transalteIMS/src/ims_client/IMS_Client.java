package ims_client;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import ims_server.IMS_Server_ThreadBuilder;
import ims_user.IMS_User;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;

/**
 * @author ciunas
 *
 */
public class IMS_Client {

    private Socket clinetSocket;
	private JFrame frmInstantMessaginService;
	private String hostname = "localhost";
	private BufferedReader bReader;
	private PrintWriter dataOut;
	ObjectInputStream in = null;
	ObjectOutputStream out  = null;
	private JTable table;
	DefaultTableModel tableModel;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblLoggedInAs;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public IMS_Client() throws ClassNotFoundException, IOException {

		IMS_User user = new IMS_User();
//		try {
//
//			// make connection
//			clinetSocket = new Socket(hostname , 1234);
//			// get streams
//			bReader = new BufferedReader(new InputStreamReader(clinetSocket.getInputStream()));
//			dataOut = new PrintWriter(clinetSocket.getOutputStream());			
//			in = new ObjectInputStream(clinetSocket.getInputStream());
//			out = new ObjectOutputStream(clinetSocket.getOutputStream());
//		}
//		catch (IOException ioException) {
//			ioException.printStackTrace();
//		}
//		
//		LogInScreen pframe = null;
//		pframe = new LogInScreen(frmInstantMessaginService, bReader, dataOut);
//		pframe.setVisible(true);
//		
//		if(pframe.isCancel())
//			System.exit(0);
//	
//		
//		
//		user = getUserObject();
//		in.close();
//		out.close();
//		System.out.println("UserName: " + user.getName() + " User Launguage: "+ user.getLaunguage() + " Size of ArryayList: " 
//		+ user.labels.size() );
		
		initialize(user);

	}
	

	
	/**
	 * Sends a IMS_User Object to the client application after user authenticated.
	 * @return IMS_User
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
	 * Initialize the contents of the frame.
	 * @param IMS_User
	 */
	private void initialize( IMS_User user ) {
		
		frmInstantMessaginService = new JFrame();

		frmInstantMessaginService.setBounds(100, 100, 372, 356);
		frmInstantMessaginService.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		panel = new JPanel();
		frmInstantMessaginService.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[grow][][grow]"));
		
		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		lblLoggedInAs = new JLabel(user.getLabel(7) + " " + user.getName());
		panel_2.add(lblLoggedInAs, "cell 0 0 1 2");
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 1,grow");
	
     
		
	    String[] columns = new String[] {	//headers for the table
	    		user.getLabel(3),user.getLabel(8), "IP" };
        
        //create my table using table modeel so rows does not need to be specified
        tableModel = new DefaultTableModel(columns, 0);      // The 0 argument is number rows.
		table = new JTable(tableModel); 
		
		
		
		for (int i = 0; i < user.onlineUsers.size(); i+=2) {
			
			
			Object[] objs = { i+1,user.getOnlineUsers(i), user.getOnlineUsers(i+1) };

			tableModel.addRow(objs);
		}

	
		
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 2,grow");
		panel_1.setLayout(new MigLayout("", "[grow][][grow]", "[grow]"));
		
		frmInstantMessaginService.setTitle(user.getLabel(9));
		
		JButton btnConnect = new JButton(user.getLabel(2)); 
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int selectedRowIndex = table.getSelectedRow();
				String  name = (String) table.getModel().getValueAt(selectedRowIndex, 0);
				String  ip = (String) table.getModel().getValueAt(selectedRowIndex, 1);
				
//				IMS_Client_ConnectThread cThreadframe = null;
//				cThreadframe = new IMS_Client_ConnectThread(name, ip);
//				cThreadframe.setVisible(true);
				
				IMS_Client_ConnectThreadGUI cThreadframe = new IMS_Client_ConnectThreadGUI(name, ip);
		        new Thread(cThreadframe).start();
//				System.out.println(name);
//				System.out.println(ip);
			}
		});
		panel_1.add(btnConnect, "cell 1 0");
		
		lblNewLabel = new JLabel("<dynamic> Ciunas Bennett");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Cantarell", Font.BOLD | Font.ITALIC, 12));
		frmInstantMessaginService.getContentPane().add(lblNewLabel, BorderLayout.SOUTH);


		
		
	}

}

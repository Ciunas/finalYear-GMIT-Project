package ims_client;

import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.swingx.prompt.PromptSupport;
import ims_translate.Language;
import ims_translate.Translate;
import ims_user.IMS_User;
import org.java_websocket.WebSocket; 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 * @author ciunas
 *
 */

public class IMS_Client_ServerConnectThreadGUI extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StyledDocument doc;
	private SimpleAttributeSet left;
	private SimpleAttributeSet right;
	private String name = null;
	private String connectedLaunguage = null;
	private String translatedText;
	private boolean run = true;
	private JFrame frame;
	private JTextField txtTypeAMessage;
	private JTextPane textPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private WebSocket ws;
	private IMS_User  user;
	private JPanel panel;
	private JLabel lblNewLabel;

	

	/**
	 * Used to Launch the application.
	 * 
	 */
	public IMS_Client_ServerConnectThreadGUI(WebSocket conn, IMS_User user) {
		this.ws = conn;
		this.user =  user;
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
				initialize();
			}
		});
	}

	/**
	 *  initialise JFrame and alll its components
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 454, 574);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		JPanel panel_2 = new JPanel();

		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][50px][]"));
		{
			scrollPane_1 = new JScrollPane();
			panel_2.add(scrollPane_1, "cell 0 0 1 4,grow");
			{
				textPane = new JTextPane();
				textPane.setEditable(false);
				textPane.setBackground(Color.LIGHT_GRAY);
				textPane.setFont(new Font("Dialog", Font.BOLD, 14));
				doc = textPane.getStyledDocument();
				left = new SimpleAttributeSet();
				StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
				StyleConstants.setForeground(left, Color.RED);
				right = new SimpleAttributeSet();
				StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
				StyleConstants.setForeground(right, Color.BLUE);
				scrollPane_1.setViewportView(textPane);
			}

			scrollPane_2 = new JScrollPane();
			panel_2.add(scrollPane_2, "cell 0 4,grow");
			{
				txtTypeAMessage = new JTextField();
				PromptSupport.setPrompt (user.getLabel(13), txtTypeAMessage);
				txtTypeAMessage.setFont(new Font("Dialog", Font.BOLD, 14));
				txtTypeAMessage.setColumns(10);
				scrollPane_2.setViewportView(txtTypeAMessage);

			}

			JPanel panel_3 = new JPanel();
			
			//Disconnect from  websocket
			panel_2.add(panel_3, "cell 0 5,grow");
			{

				JButton btnNewButton = new JButton(user.getLabel(6));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						ws.close();
						run = false;
						frame.dispose();					
					}
				});
				panel_3.add(btnNewButton);
			}
		}
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel(user.getLabel(10) );
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);

		//Send message using the "Enter" button that is in JTextField.
		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				sentMessage();
				
			}
		};
		txtTypeAMessage.addActionListener(action);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				ws.close();
				run = false;
				frame.dispose();							
			}
		});


		frame.setVisible(true);
	}

	/**
	 * run
	 */
	@Override
	public void run() {
	
		while (run == true) {
			
		}	
	}

	
	/**
	 * message that is read from text field, sent to websocket using swingutilities,
	 * then GUI is updated using left formatting
	 */
	void sentMessage() {
		
		IMS_Client_Message messageObject = new IMS_Client_Message();

		messageObject.setName(user.getName());
		messageObject.setLaunguage(user.getLaunguage());
		messageObject.setMessage(txtTypeAMessage.getText());
		
		IMS_Client_JsonEncode jec = new IMS_Client_JsonEncode(messageObject);
		String messageCreate = jec.encodeToString();
		
		System.out.println(messageCreate);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
					if (ws != null) {						//check websocket is still connected
						ws.send(messageCreate);
					}
									
					doc.setParagraphAttributes(doc.getLength(), 1, left, false);
					doc.insertString(doc.getLength(), "\nYou:\n" + txtTypeAMessage.getText() + "\n", left);
					txtTypeAMessage.setText("");
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
	}


	/**
	 *  Sends the recieved message to GUI and updates using right formatting.
	 *  
	 * @param message (read through websocket)
	 */
	void recievedMessage(String message) {

		//System.out.println("Message to translate: " + message);
		if ( message.compareTo("SetUp") == 0) {
			//System.out.println("Setup");
			lblNewLabel.setText( user.getLabel(10)  + " " + name );
		} else {
			try {
				translatedText = Translate.execute(message, Language.fromString(connectedLaunguage),
						Language.fromString(user.getLaunguage()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("translating message");

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {

					try {						
						doc.setParagraphAttributes(doc.getLength(), 1, right, false);
						doc.insertString(doc.getLength(), "\n" + name + ": \n" + translatedText + "\n", right);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}

				}
			});
		}
	}
	
	/**
	 *  Sends the recieved message to GUI and updates using right formatting.
	 *  
	 * @param message (read through websocket)
	 */
	void closeConnection() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
//				JOptionPane.showMessageDialog(frame, user.getLabel(11), "",
//						JOptionPane.INFORMATION_MESSAGE);
				run = false;
				frame.dispose();				
			}
		});
	}




	//Getters and Setters
	public String getName() {
		return name;
	}

	public String getConnectedLaunguage() {
		return connectedLaunguage;
	}

	public void setConnectedLaunguage(String connectedLaunguage) {
		this.connectedLaunguage = connectedLaunguage;
	}


	public void setName(String name) {
		this.name = name;
	}
	

	public WebSocket getWs() {
		return ws;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

}

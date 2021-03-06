package ims_client;

import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.jdesktop.swingx.prompt.PromptSupport;
import ims_translate.Language;
import ims_translate.Translate;
import ims_user.IMS_User;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author ciunas
 *
 */

public class IMS_Client_ClientConnectThreadGUI extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StyledDocument doc;
	private SimpleAttributeSet left;
	private SimpleAttributeSet right;
	private String name;
	private String ip;
	private WebSocketClient wsc;
	private boolean run;
	private JFrame frame;
	private JTextField txtTypeAMessage;
	private JTextPane textPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private Object translatedText;
	private IMS_User user;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 * 
	 */
	public IMS_Client_ClientConnectThreadGUI(IMS_User user, String name, String ip) {
		this.user = user;
		this.name = name;
		this.ip = ip;
		initialize();
	}

	/**
	 * initialise the Jframe and all its components
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
				PromptSupport.setPrompt(user.getLabel(13), txtTypeAMessage);
				txtTypeAMessage.setFont(new Font("Dialog", Font.BOLD, 14));
				txtTypeAMessage.setColumns(10);
				scrollPane_2.setViewportView(txtTypeAMessage);

			}

			JPanel panel_3 = new JPanel();

			// Disconnect from websocket
			panel_2.add(panel_3, "cell 0 5,grow");
			{

				JButton btnNewButton = new JButton(user.getLabel(6));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						wsc.close();
						// sentMessage();

					}
				});
				panel_3.add(btnNewButton);
			}
		}
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel( user.getLabel(10) + " " + name);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel_1);
		
           

		// Send message using the "Enter" button that is in JTextField.
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
				frame.dispose();
				run = false;
			}
		});

		frame.setVisible(true);
	}

	/**
	 * run
	 */
	@Override
	public void run() {

		WebSocketImpl.DEBUG = true;
		connectWebCocket();
		while (run == true) {

		}

	}

	/**
	 * creates the websocket connection, listens for any change in state and
	 * posts to swingutilites with any messages.
	 */
	void connectWebCocket() {

		try {

			wsc = new WebSocketClient(new URI(ip)) {

				@Override
				public void onMessage(String message) {

					recievedMessage(message);
				}

				@Override
				public void onOpen(ServerHandshake handshake) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							IMS_Client_Message messageObject = new IMS_Client_Message();
							messageObject.setName(user.getName());
							messageObject.setMessage("SetUp");
							messageObject.setLaunguage(user.getLaunguage());

							IMS_Client_JsonEncode jec = new IMS_Client_JsonEncode(messageObject);
							String messageCreate = jec.encodeToString();
							if (wsc != null) { // check websocket is still connected
								wsc.send(messageCreate);
							}
							
//							JOptionPane.showMessageDialog(frame, user.getLabel(10) + ": " + name, "",
//									JOptionPane.YES_NO_CANCEL_OPTION);
						}
					});
				}

				@Override
				public void onClose(int code, String reason, boolean remote) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
//							JOptionPane.showMessageDialog(frame, user.getLabel(11), "",
//									JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
							run = false;
						}
					});
				}

				@Override
				public void onError(Exception ex) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							JOptionPane.showMessageDialog(frame, "Exception", "Error", JOptionPane.ERROR_MESSAGE);
						}
					});

					ex.printStackTrace();
				}
			};

			wsc.connect(); // connect to the websocket that is specified by
							// variable "ip"

		} catch (URISyntaxException ex) {

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JOptionPane.showMessageDialog(frame, "Exception", "URIError", JOptionPane.ERROR_MESSAGE);
				}
			});
		}
	}

	/**
	 * message that is read from text field, sent to websocket using
	 * swingutilities, then GUI is updated using left formatting
	 */
	void sentMessage() {

		IMS_Client_Message messageObject = new IMS_Client_Message();

		System.out.println("Printing my Name: " + user.getName());
		messageObject.setName(user.getName());
		messageObject.setMessage(txtTypeAMessage.getText());
		messageObject.setLaunguage(user.getLaunguage());

		IMS_Client_JsonEncode jec = new IMS_Client_JsonEncode(messageObject);
		String messageCreate = jec.encodeToString();

		System.out.println(messageCreate);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					if (wsc != null) { // check websocket is still connected
						wsc.send(messageCreate);
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
	 * Sends the recieved message to the GUI and updates using right formatting.
	 * 
	 * @param message
	 *            (read through websocket)
	 */
	void recievedMessage(String message) {

		IMS_Client_JsonDecode jdc = new IMS_Client_JsonDecode(message);

		IMS_Client_Message messageObject = jdc.decodeFormString();

		try {
			translatedText = Translate.execute(messageObject.getMessage(),
					Language.fromString(messageObject.getLaunguage()), Language.fromString(user.getLaunguage()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				try {
					doc.setParagraphAttributes(doc.getLength(), 1, right, false);
					doc.insertString(doc.getLength(), "\n" + messageObject.getName() + ": \n" + translatedText + "\n",
							right);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

			}
		});
	}

}

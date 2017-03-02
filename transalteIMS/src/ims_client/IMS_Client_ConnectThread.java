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
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JTextPane;

public class IMS_Client_ConnectThread extends JFrame implements Runnable {

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
	private JTextField textField;
	private JTextPane textPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;

	/**
	 * Launch the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMS_Client_ConnectThread window = new IMS_Client_ConnectThread("ham", "test");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IMS_Client_ConnectThread(String name, String ip) {
		this.name = name;
		this.ip = ip;
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 372, 356);
		frame.getContentPane().setLayout(new BorderLayout());

		JPanel panel_2 = new JPanel();

		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][]"));
		{
			textPane = new JTextPane();
			scrollPane_1 = new JScrollPane(textPane);
			panel_2.add(scrollPane_1, "cell 0 0 1 4,grow");
			{
				textPane = new JTextPane();
				textPane.setFont(new Font("Dialog", Font.BOLD, 16));

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
				textField = new JTextField();
				textField.setColumns(10);
				scrollPane_2.setViewportView(textField);

			}

			JPanel panel_3 = new JPanel();
			panel_2.add(panel_3, "cell 0 5,grow");
			{

				JButton btnNewButton = new JButton("New button");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						sentMessage();

					}
				});
				panel_3.add(btnNewButton);
				JButton button = new JButton("New button");
				panel_3.add(button);
			}
		}

		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				sentMessage();
			}
		};
		textField.addActionListener(action);

		frame.setVisible(true);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void run() {

		// cThreadframe.setVisible(true);
		// initialize();

		WebSocketImpl.DEBUG = true;
		while (run = true) {

		}
		// this.dispose();
	}

	void connectWebCocket() {

		try {

			wsc = new WebSocketClient(new URI(ip)) {

				@Override
				public void onMessage(String message) {

					recievedMessage(message);
					// ta.append( "got: " + message + "\n" );
					// ta.setCaretPosition( ta.getDocument().getLength() );
				}

				@Override
				public void onOpen(ServerHandshake handshake) {

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							JOptionPane.showMessageDialog(new Frame(), "You are connected to", name,
									JOptionPane.YES_NO_CANCEL_OPTION);
							// stop();
						}
					});
					// ta.append("You are connected to ChatServer: " + getURI()
					// + "\n");
					// ta.setCaretPosition(ta.getDocument().getLength());
				}

				@Override
				public void onClose(int code, String reason, boolean remote) {
					// ta.append("You have been disconnected from: " + getURI()
					// + "; Code: " + code + " " + reason + "\n");
					// ta.setCaretPosition(ta.getDocument().getLength());
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							JOptionPane.showMessageDialog(new Frame(), "You have been disconnected", "Error",
									JOptionPane.WARNING_MESSAGE);
							// stop();
						}
					});
				}

				@Override
				public void onError(Exception ex) {

					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							JOptionPane.showMessageDialog(new Frame(), "Exception", "Error", JOptionPane.ERROR_MESSAGE);
							// stop();
						}
					});

					ex.printStackTrace();
				}
			};
			wsc.connect();
		} catch (URISyntaxException ex) {

			// ta.append(uriField.getText() + " is not a valid WebSocket
			// URI\n");
		}
	}

	// Sent message, update GUI
	void sentMessage() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					doc.setParagraphAttributes(doc.getLength(), 1, left, false);
					doc.insertString(doc.getLength(), "\nYou: \n" + textField.getText() + "\n", left);
					textField.setText("");
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
	}

	// Recieved message, update GUI
	void recievedMessage(String name1) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				try {
					doc.setParagraphAttributes(doc.getLength(), 1, right, false);
					doc.insertString(doc.getLength(), "\n" + name1 + ": \n" + textField.getText() + "\n", right);
					textField.setText("");
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

			}
		});
	}

}

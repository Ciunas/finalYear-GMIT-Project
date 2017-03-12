package ims_client;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import ims_translate.Language;
import ims_translate.Translate;

/**
 * @author ciunas
 *
 */

public class IMS_Client_PushConnection implements Runnable{
	
	private boolean run;
	private WebSocketClient wsc;

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
							JOptionPane.showMessageDialog(frame, "You are connected to", name,
									JOptionPane.YES_NO_CANCEL_OPTION);
						}
					});
				}

				@Override
				public void onClose(int code, String reason, boolean remote) {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							JOptionPane.showMessageDialog(frame, "You have been disconnected", "Error",
									JOptionPane.WARNING_MESSAGE);
							run = false;
							System.exit(0);

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

		IMS_Client_Message user = new IMS_Client_Message();

		user.setName(myName);
		user.setMessage(txtTypeAMessage.getText());
		user.setLaunguage(launguage);

		IMS_Client_JsonEncode jec = new IMS_Client_JsonEncode(user);
		String messageCreate = jec.encodeToString();

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
			translatedText = Translate.execute( messageObject.getMessage() , Language.fromString(messageObject.getLaunguage()) , Language.fromString(launguage) );
		} catch (Exception e) {
			e.printStackTrace();
		}


		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				try {
					doc.setParagraphAttributes(doc.getLength(), 1, right, false);
					doc.insertString(doc.getLength(), "\n" + messageObject.getName() + ": \n" + translatedText + "\n", right);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

			}
		});
	}
}

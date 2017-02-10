package applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultCaret;

import firewallObject.FirewallRule;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class TerminalEmulator extends JDialog {
	
		JPanel panel;
	 	JTextArea textArea;
	 	JScrollPane scrollPane;
	 	JPanel panel_1 ;

	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	  private JTextField textField;
		
	    public TerminalEmulator(Frame parent) {
	        super(parent, "Terminal Emulator", true);
	        setLocation(new Point(450, 400));
	        setResizable(false);
	        setPreferredSize(new Dimension(800, 500));
	        panel = new JPanel();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        panel.setLayout(new BorderLayout(0, 0));
	        
	        panel_1 = new JPanel();
	        panel.add(panel_1, BorderLayout.CENTER);
	        panel_1.setLayout(new BorderLayout(0, 0));
	        
	        scrollPane = new JScrollPane();
	        panel_1.add(scrollPane);
	        
	        textArea = new JTextArea();
	        textArea.setFont(new Font("Bitstream Charter", Font.PLAIN, 18));
	        textArea.setForeground(Color.GREEN);
	        textArea.setBackground(Color.DARK_GRAY);
	        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
	        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	        scrollPane.setViewportView(textArea);
	        
	        textField = new JTextField();
	        panel.add(textField, BorderLayout.NORTH);
	        textField.setColumns(10);
	        
	        
	        Action action = new AbstractAction()
	        {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            public void actionPerformed(ActionEvent e)
	            {
	            	FirewallRule r = null;
					try {
						r = new FirewallRule();
					} catch (SocketException e2) {
						e2.printStackTrace();
					}
	            	r.setCommand(textField.getText());
	            	try {
						terminalEmulate(e, r);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            	textArea.append(textField.getText() + "\n");
	            	System.out.println(textField.getText());
	                textField.setText("");	               
	            }
	        };
	       
	        textField.addActionListener( action );
	        pack();
	    }
	 
	    
		public void displayTerminal(FirewallRule r) throws SocketException {

			SwingUtilities.invokeLater(new Runnable() { // inner class to ensure GUI
														// // updates properly
				public void run() {
					System.out.println("Here1: " +  r.myVector.size());
					for (int i = 0; i < r.myVector.size(); i++) {
						textArea.append(r.myVector.get(i) + "\n");
					}
				}
			});

		}
	    
	    
		/**
		 * addRule
		 * 
		 * Clear the Update tab fields
		 * 
		 * @param evt
		 */
		private void terminalEmulate(java.awt.event.ActionEvent evt, FirewallRule r) throws IOException {
			
			try {
				URL link = new URL("http://192.168.122.112:8080/firewallservlet/terminalEmulator");
				//URL link = new URL("http://localhost:8080/TerminalEmulator");
				HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();

				urlconnection.setDoOutput(true);
				urlconnection.setDoInput(true);
				urlconnection.setUseCaches(false);
				urlconnection.setDefaultUseCaches(false);

				// Specify the content type that we will send binary data
				urlconnection.setRequestProperty("Content-Type", "application/octet-stream");

				ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
		
				oos.writeObject(r);
				oos.flush();

				ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());
				
				FirewallRule r1 = (FirewallRule) ois.readObject();
				
				displayTerminal(r1);
				oos.close();
				ois.close();

			} catch (Exception ex) {
			} finally {
			
			}

		}
	    
	    
}

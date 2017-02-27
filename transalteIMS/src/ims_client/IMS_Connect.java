package ims_client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel; 
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane; 
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;

public class IMS_Connect extends JDialog  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane text_1;
	private JTextField text_2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel buttonPane;
	private StyledDocument doc;
	private SimpleAttributeSet left;
	private SimpleAttributeSet right;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			IMS_Connect dialog = new IMS_Connect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public IMS_Connect() {
		setBounds(100, 100, 450, 443);
		getContentPane().setLayout(new BorderLayout());
			
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							doc.setParagraphAttributes(doc.getLength(), 1, right, false);
							doc.insertString(doc.getLength(), "\nJohn: \n" + text_2.getText() + "\n", right);
							

						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}

						System.out.println(text_2.getText());
						text_2.setText("");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
			{
				scrollPane = new JScrollPane();
				panel.add(scrollPane, "cell 0 0 0 6,grow");
				{
					text_1 = new JTextPane();
					text_1.setFont(new Font("Dialog", Font.BOLD, 16));

			        doc = text_1.getStyledDocument();

			        left = new SimpleAttributeSet();
			        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
			        StyleConstants.setForeground(left, Color.RED);

			        right = new SimpleAttributeSet();
			        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
			        StyleConstants.setForeground(right, Color.BLUE);
					scrollPane.setViewportView(text_1);
				}
			}
			{
				scrollPane1 = new JScrollPane();
				panel.add(scrollPane1, "cell 0 6,grow");
				{
					text_2 = new JTextField();
					text_2.setFont(new Font("Bitstream Charter", Font.PLAIN, 16));
					scrollPane1.setViewportView(text_2);
				}
			}
		}
		
	      
        Action action = new AbstractAction()
        {
            
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e)
            {
            	
				
            	   try
                   {
            		   doc.setParagraphAttributes(doc.getLength(), 1, left, false);
            		   doc.insertString(doc.getLength(), "\nYou: \n" + text_2.getText() + "\n", left );
                       text_2.setText("");	
                   } catch(Exception e1) { System.out.println(e); }
            }
        };
		 
        text_2.addActionListener( action );
	}
	
	 

}

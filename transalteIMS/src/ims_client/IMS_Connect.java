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
import java.awt.Font;
import java.awt.event.ActionEvent;

public class IMS_Connect extends JDialog  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea text_1;
	private JTextField text_2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel buttonPane;
	private JTextField textField;

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
					text_1 = new JTextArea();
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
            	
				text_1.append(text_2.getText() + "\n");
            	System.out.println(text_2.getText());
            	text_2.setText("");	               
            }
        };
		 
        text_2.addActionListener( action );
	}

}

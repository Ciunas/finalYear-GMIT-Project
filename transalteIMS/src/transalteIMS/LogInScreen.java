package transalteIMS;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;


public class LogInScreen extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    private JPanel panel_1;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JTextField userName;
    private JPasswordField passwordField;
    private DataAccess database;
	protected boolean chBox;
	
	/**
	 * Create the dialog.
	 */
	public LogInScreen(Frame parent) {
        super(parent, "Login", true);
        
        // create database connection
        try {
            database = new DataBaseAccess();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }


        setResizable(false);
        setLocation(new Point(450, 400));
        setPreferredSize(new Dimension(400, 200));
        JPanel panel = new JPanel();
        // reference to database access object
   
 
        btnLogin = new JButton("Login"); 
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	IMS_User user = new IMS_User(getUsername(),getPassword() );
            	
                if ( database.newUser(user) ) {
                	succeeded = true;
                    dispose(); 

                } else {
                    JOptionPane.showMessageDialog(LogInScreen.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    userName.setText("");
                    passwordField.setText("");
                    succeeded = false;
 
                }
            }
        });
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        
        JPanel bp = new JPanel();

        bp.setLayout(new GridLayout(0, 2, 0, 0));
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        
        panel_1 = new JPanel();
        panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        panel_1.setBounds(12, 66, 205, 55);
        panel.add(panel_1);
        panel_1.setLayout(new GridLayout(2, 2, 0, 0));
        
        lblNewLabel_3 = new JLabel("UserName", SwingConstants.CENTER);
        panel_1.add(lblNewLabel_3);
        
        userName = new JTextField();
        panel_1.add(userName);
        
        lblNewLabel_2 = new JLabel("Password", SwingConstants.CENTER);
        panel_1.add(lblNewLabel_2);
        
        passwordField = new JPasswordField();
        panel_1.add(passwordField);
        
        lblNewLabel_1 = new JLabel("Lgin to IMS", SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
        lblNewLabel_1.setBounds(51, 24, 128, 30);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(LogInScreen.class.getResource("/resources/Apps-Messaging-Metro-icon.png")));
        lblNewLabel.setBounds(250, 12, 128, 121);
        panel.add(lblNewLabel);
        
        JCheckBox rdbtnNewUser = new JCheckBox("Create New User");
        rdbtnNewUser.setBounds(44, 134, 149, 23);
        rdbtnNewUser.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				btnLogin.setText("New User");
				if(chBox == true){
						chBox = false;
						btnLogin.setText("Login");
				}
				else{
					chBox = true;
					btnLogin.setText("New User");
				}
					
			}
		});
        panel.add(rdbtnNewUser);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
	}
    public String getUsername() {
        return userName.getText().trim();
    }
 
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
    
    public boolean authenticate(String username, String password) {
        if (username.equals("1") && password.equals("1")) {
            return true;
        }
        return false;
    }
}

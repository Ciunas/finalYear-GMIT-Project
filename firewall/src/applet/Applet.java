package applet;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import firewallObject.FirewallRule;
import servlet.TestFrame1;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JScrollBar;
import javax.swing.Icon;
import javax.swing.border.EtchedBorder;

public class Applet extends JApplet {
	public Applet() {
		setSize(new Dimension(800, 800));
	}

	private static final long serialVersionUID = 1L;
	private JLabel setMessageLabel;
	private JPanel panel;
	private JPanel panel_21;
	private JLabel lblFirewall;
	private JPanel panel_1;
	private JPanel panel_12;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel_6;
	private JPanel panel_2;
	private JPanel panel_7;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JTable table;
	private JScrollPane resultTableScrollPane;
	private JPanel panel_8;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnAdd;
	private JButton btnAdd_1;
	private JButton btnAdd_2;
	private JLabel lblSetDefaultChain;
	private JCheckBox input;
	private JCheckBox output;
	private JCheckBox forward;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JLabel lblAllowHttphttpsTraffic;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JLabel lblAllowPing;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
	private JPanel panel_10;
	private JLabel lblPortForwarding;
	private JLabel lblIpaddress;
	private JTextField textField_2;
	private JLabel lblPortToForward;
	private JTextField textField;
	private JLabel lblToPort;
	private JTextField textField_1;
	private JPanel panel_11;
	private JLabel lblAlllowLoopbackO;
	private JPanel panel_14;
	private JPanel panel_16;
	private JPanel panel_15;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_13;
	private JPanel panel_20;
	private JTabbedPane pane;
	private JPanel panel_22;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JPanel panel_23;
	private JPanel panel_24;
	private JPanel panel_25;
	private JPanel panel_26;
	private JLabel lblAllowhttphttpsTrafficOutgoing;
	private JPanel panel_27;
	private JPanel panel_28;
	private JCheckBox chckbxNewCheckBox_6;
	private JCheckBox chckbxNewCheckBox_7;
	private JPanel panel_29;
	private JPanel panel_30;
	private JLabel lblFirewall_1;
	private JButton btnNewButton_8;
	private JTextArea textArea;
	private JScrollPane scrollBar;
	private JPanel panel_3;
	private JPanel panel_9;
	private JPanel panel_31;
	private JPanel panel_32;
	private JPanel panel_33;
	private JPanel panel_34;
	private JButton button;
	private JButton button_1;
	private JPanel panel_36;
	private JLabel lblFirewallConfiguration;

	@Override
	public void init() {

		/* Create and display the applet */
		try {
			java.awt.EventQueue.invokeAndWait(new Runnable() {
				public void run() {

			      try {
//			        UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//				      UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
//				      UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//				      UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//				      UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
//				      UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
//				      UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
//				      UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
				      UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//				      UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			      
					JFrame frame = new JFrame();
					PasswordFrame pframe = null;
					pframe = new PasswordFrame(frame);
					pframe.setVisible(true);
					if (pframe.isSucceeded()) {
						initComponents();
						getContentPane().setBackground(Color.WHITE);
					} else {
						System.out.println("You loose");
					}
					
//					initComponents();
//					getContentPane().setBackground(Color.WHITE);
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the applet.
	 */
	@SuppressWarnings("serial")
	private void initComponents() {
		

		panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 400));
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(1, 1));

		lblFirewall = new JLabel("Firewall");
		panel.add(lblFirewall, BorderLayout.NORTH);

		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		pane = new JTabbedPane();
		pane.setPreferredSize(new Dimension(5, 475));
		panel.add(pane, BorderLayout.NORTH);
		panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_20 = new JPanel(new BorderLayout(0, 0));

		pane.addTab("1st Tab", panel_2);
		pane.addTab("2nd Tab", panel_20);
		pane.setForegroundAt(1, Color.BLUE);
		// Tab1//////////////////////////////////////////

		panel_22 = new JPanel();
		panel_22.setPreferredSize(new Dimension(10, 20));
		panel_20.add(panel_22, BorderLayout.SOUTH);
		panel_22.setLayout(new GridLayout(1, 2, 0, 0));
		panel_23 = new JPanel(new GridLayout(1, 2, 0, 0));
		panel_20.add(panel_23, BorderLayout.CENTER);
		panel_24 = new JPanel();

		ImageIcon img = new ImageIcon(getClass().getResource("/resources/add.jpg"));
		
		panel_25 = new JPanel();
		panel_25.setMaximumSize(new Dimension(32767, 200));

		panel_23.add(panel_25, BorderLayout.WEST);
		panel_25.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setFont(new Font("FreeSerif", Font.PLAIN, 18));
		textArea.setMaximumSize(new Dimension(2147483647, 400));
		scrollBar = new JScrollPane(textArea);
		panel_25.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setMaximumSize(new Dimension(32767, 400));
		
		btnNewButton_8 = new JButton("List network Interfaces.");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Enumeration<NetworkInterface> nets = null;
				textArea.setText("");
				try {
					nets = NetworkInterface.getNetworkInterfaces();
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        for (NetworkInterface netint : Collections.list(nets))
					try {
						displayInterfaces(netint);
					} catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});
		panel_25.add(btnNewButton_8, BorderLayout.SOUTH);
		panel_23.add(panel_24, BorderLayout.EAST);

		panel_10 = new JPanel();
		panel_23.add(panel_10);
		panel_10.setMinimumSize(new Dimension(10, 8));

		lblPortForwarding = new JLabel("Port Forwarding");
		lblPortForwarding.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_10.add(lblPortForwarding);

		lblIpaddress = new JLabel("Ipaddress");
		lblIpaddress.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_10.add(lblIpaddress);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_10.add(textField_2);
		textField_2.setColumns(10);

		lblPortToForward = new JLabel("port to forward");
		lblPortToForward.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_10.add(lblPortToForward);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_10.add(textField);
		textField.setColumns(10);

		lblToPort = new JLabel("to Port");
		lblToPort.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_10.add(lblToPort);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_10.add(textField_1);
		textField_1.setColumns(10);

		btnNewButton_6 = new JButton("View");
		btnNewButton_6.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_22.add(btnNewButton_6);
		
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					viewRule(evt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btnNewButton_5.setEnabled(true);
			}
		});

		btnNewButton_5 = new JButton("Delete");
		btnNewButton_5.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_22.add(btnNewButton_5);
		btnNewButton_5.setEnabled(false);
		
		panel_36 = new JPanel();
		panel_36.setFont(new Font("Dialog", Font.PLAIN, 26));
		panel_36.setForeground(Color.RED);
		panel_36.setBackground(Color.DARK_GRAY);
		panel_36.setPreferredSize(new Dimension(1, 50));
		panel_36.setMinimumSize(new Dimension(165, 65));
		panel_20.add(panel_36, BorderLayout.NORTH);
		panel_36.setLayout(new BorderLayout(0, 0));
		
		lblFirewallConfiguration = new JLabel("Firewall Configuration", SwingConstants.CENTER);
		lblFirewallConfiguration.setFont(new Font("Dialog", Font.BOLD, 26));
		lblFirewallConfiguration.setForeground(Color.RED);
		panel_36.add(lblFirewallConfiguration);
		
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					deleteRule(evt);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});

		

		panel_7 = new JPanel();
		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_7.setLayout(new GridLayout(7, 1, 0, 0));
		panel_2.add(panel_7, BorderLayout.CENTER);
		
		panel_30 = new JPanel();
		panel_30.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_7.add(panel_30);
		panel_30.setLayout(new BorderLayout(0, 0));
		
		lblFirewall_1 = new JLabel("Firewall Configuration", SwingConstants.CENTER);
		lblFirewall_1.setOpaque(true);
		lblFirewall_1.setBackground(Color.DARK_GRAY);
		lblFirewall_1.setForeground(Color.RED);
		lblFirewall_1.setFont(new Font("Dialog", Font.BOLD, 26));
		panel_30.add(lblFirewall_1, BorderLayout.CENTER);

		panel_21 = new JPanel();
		panel_21.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_21);
		panel_21.setLayout(new GridLayout(1, 2, 0, 0));

		panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_21.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		lblSetDefaultChain = new JLabel("Set default Chain policies", SwingConstants.CENTER);
		lblSetDefaultChain.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSetDefaultChain.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_13.add(lblSetDefaultChain);
		lblSetDefaultChain.setFont(new Font("FreeSerif", Font.BOLD, 22));

		panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Choose", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_21.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));

		btnNewButton_3 = new JButton(img);
		panel_12.add(btnNewButton_3, BorderLayout.EAST);

		panel_3 = new JPanel();
		panel_12.add(panel_3, BorderLayout.CENTER);

		input = new JCheckBox("Input");
		panel_3.add(input);
		input.setFont(new Font("Dialog", Font.BOLD, 18));

		output = new JCheckBox("Output");
		panel_3.add(output);
		output.setFont(new Font("Dialog", Font.BOLD, 18));

		forward = new JCheckBox("Forward");
		panel_3.add(forward);
		forward.setFont(new Font("Dialog", Font.BOLD, 18));

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		panel_4 = new JPanel();
		panel_4.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));

		panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_4.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));

		lblNewLabel_1 = new JLabel("Alllow  ssh incoming/outgoing");
		panel_14.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Choose", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_4.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
				btnNewButton_4 = new JButton(img);
				panel_16.add(btnNewButton_4, BorderLayout.EAST);
				
				panel_9 = new JPanel();
				panel_16.add(panel_9, BorderLayout.CENTER);
				
						chckbxNewCheckBox_3 = new JCheckBox("Outgoing");
						panel_9.add(chckbxNewCheckBox_3);
						chckbxNewCheckBox_3.setFont(new Font("Dialog", Font.BOLD, 18));
						
								chckbxNewCheckBox_2 = new JCheckBox("Incoming");
								panel_9.add(chckbxNewCheckBox_2);
								chckbxNewCheckBox_2.setFont(new Font("Dialog", Font.BOLD, 18));

		panel_5 = new JPanel();
		panel_5.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 2, 0, 0));

		panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_5.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));

		lblAllowHttphttpsTraffic = new JLabel("Allow http/https traffic incoming", SwingConstants.CENTER);
		panel_15.add(lblAllowHttphttpsTraffic);
		lblAllowHttphttpsTraffic.setFont(new Font("Dialog", Font.BOLD, 18));

		panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Choose", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_5.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
				btnAdd = new JButton(img);
				panel_17.add(btnAdd, BorderLayout.EAST);
				
				panel_31 = new JPanel();
				panel_17.add(panel_31, BorderLayout.CENTER);
						
								chckbxNewCheckBox = new JCheckBox("Http");
								panel_31.add(chckbxNewCheckBox);
								chckbxNewCheckBox.setFont(new Font("Dialog", Font.BOLD, 18));
				
						chckbxNewCheckBox_1 = new JCheckBox("Https");
						panel_31.add(chckbxNewCheckBox_1);
						chckbxNewCheckBox_1.setFont(new Font("Dialog", Font.BOLD, 18));
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
		
		panel_26 = new JPanel();
		panel_7.add(panel_26);
		panel_26.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_29 = new JPanel();
		panel_29.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_26.add(panel_29);
		panel_29.setLayout(new BorderLayout(0, 0));
		
		lblAllowhttphttpsTrafficOutgoing = new JLabel("Allow Http/Https traffic outgoing", SwingConstants.CENTER);
		panel_29.add(lblAllowhttphttpsTrafficOutgoing);
		lblAllowhttphttpsTrafficOutgoing.setFont(new Font("FreeSerif", Font.BOLD, 22));
		
		panel_28 = new JPanel();
		panel_28.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Choose", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_26.add(panel_28);
		panel_28.setLayout(new BorderLayout(0, 0));
		
		panel_27 = new JPanel();
		panel_28.add(panel_27);
		
		chckbxNewCheckBox_7 = new JCheckBox("Http");
		chckbxNewCheckBox_7.setFont(new Font("FreeSerif", Font.BOLD, 22));
		panel_27.add(chckbxNewCheckBox_7);
		
		chckbxNewCheckBox_6 = new JCheckBox("Https");
		panel_27.add(chckbxNewCheckBox_6);
		chckbxNewCheckBox_6.setFont(new Font("FreeSerif", Font.BOLD, 22));
		
				btnAdd_1 = new JButton(img);
				panel_28.add(btnAdd_1, BorderLayout.EAST);

		panel_6 = new JPanel();
		panel_6.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 2, 0, 0));

		panel_18 = new JPanel();
		panel_18.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_6.add(panel_18);
		panel_18.setLayout(new BorderLayout(0, 0));

		lblAllowPing = new JLabel("Allow ping ", SwingConstants.CENTER);
		lblAllowPing.setVerticalAlignment(SwingConstants.CENTER);
		panel_18.add(lblAllowPing);
		lblAllowPing.setFont(new Font("Dialog", Font.BOLD, 18));

		panel_19 = new JPanel();
		panel_19.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Choose", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_6.add(panel_19);
		panel_19.setLayout(new BorderLayout(0, 0));

		btnAdd_2 = new JButton(img);
		btnAdd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_19.add(btnAdd_2, BorderLayout.EAST);

		panel_32 = new JPanel();
		panel_19.add(panel_32, BorderLayout.CENTER);

		chckbxNewCheckBox_5 = new JCheckBox("Inside to Outside");
		panel_32.add(chckbxNewCheckBox_5);
		chckbxNewCheckBox_5.setFont(new Font("Dialog", Font.BOLD, 18));

		chckbxNewCheckBox_4 = new JCheckBox("Outside to Inside");
		panel_32.add(chckbxNewCheckBox_4);
		chckbxNewCheckBox_4.setFont(new Font("Dialog", Font.BOLD, 18));

		panel_11 = new JPanel();
		panel_11.setBorder(null);
		panel_11.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_11);
		panel_11.setLayout(new GridLayout(1, 2, 0, 0));

		panel_34 = new JPanel();
		panel_34.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_11.add(panel_34);
		panel_34.setLayout(new BorderLayout(0, 0));

		lblAlllowLoopbackO = new JLabel("Allow Loopback Connections", SwingConstants.CENTER);
		panel_34.add(lblAlllowLoopbackO, BorderLayout.CENTER);
		lblAlllowLoopbackO.setFont(new Font("FreeSerif", Font.BOLD, 22));

		button = new JButton(new ImageIcon(Applet.class.getResource("/resources/add.jpg")));
		button.setPreferredSize(new Dimension(162, 138));
		panel_34.add(button, BorderLayout.EAST);

		panel_33 = new JPanel();
		panel_33.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_11.add(panel_33);
		panel_33.setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel("Allow outbound DNS", SwingConstants.CENTER);
		panel_33.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setFont(new Font("FreeSerif", Font.BOLD, 22));

		button_1 = new JButton(new ImageIcon(Applet.class.getResource("/resources/add.jpg")));
		button_1.setPreferredSize(new Dimension(162, 138));
		panel_33.add(button_1, BorderLayout.EAST);

		panel_8 = new JPanel(new GridLayout(1, 2));
		panel_8.setPreferredSize(new Dimension(0, 20));
		panel_2.add(panel_8, BorderLayout.SOUTH);

		btnNewButton_1 = new JButton(" View Current Rules");
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_8.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					viewRule(evt);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				btnNewButton_2.setEnabled(true);
			}
		});

		btnNewButton_2 = new JButton("Delete A Current Rule");
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_8.add(btnNewButton_2);
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					deleteRule(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		panel_6 = new JPanel();

		panel_1.add(panel_6, BorderLayout.SOUTH);

		resultTableScrollPane = new JScrollPane();
		table = new JTable();
		table.setBackground(Color.WHITE);
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(255, 0, 0));
		header.setForeground(Color.WHITE);
		panel_6.setLayout(new BorderLayout(0, 0));

		resultTableScrollPane.setBackground(new Color(255, 255, 255));
		resultTableScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Rules",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Calibri", 1, 14), new Color(51, 153, 255))); // NOI18N

		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(new Object[][] { { null } }, new String[] { "NUM", "RULE", }) {
			Class[] types = new Class[] { int.class, String.class };
			boolean[] canEdit = new boolean[] { false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		});

		table.setFillsViewportHeight(true);
		table.setGridColor(new Color(51, 102, 255));
		table.setInheritsPopupMenu(true);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		resultTableScrollPane.setViewportView(table);
		resultTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setWidth(5);
		columnModel.getColumn(1).setPreferredWidth(800);
		panel_6.add(resultTableScrollPane, BorderLayout.SOUTH);

		panel_5 = new JPanel();
		panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		setMessageLabel = new JLabel();
		setMessageLabel.setBackground(new Color(51, 153, 255));
		setMessageLabel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
		setMessageLabel.setForeground(new Color(255, 0, 0));
		setMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		setMessageLabel.setText("Test");
		panel_5.add(setMessageLabel);
		clearResults();

	}

	/**
	 * deleteRule
	 * 
	 * Delete any Rule in firewall database.
	 * 
	 * @throws IOException
	 * 
	 * @param evt
	 */
	private void deleteRule(ActionEvent evt) throws IOException {

		URL link = new URL("http://192.168.122.112:8080/firewallservlet/firewallDeleteRule");
		// URL link = new URL( getCodeBase() + "/firewallDeleteRule" );
		// URL link = new URL("http://localhost:8080/FirewallDeleteRule");
		HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();
		urlconnection.setDoOutput(true);
		urlconnection.setDoInput(true);
		urlconnection.setUseCaches(false);
		urlconnection.setDefaultUseCaches(false);
		urlconnection.setRequestProperty("Content-Type", "application/octet-stream");
		String rule = null;
		try {
			int row = table.getSelectedRow();
			if (row < 0) {
				setMessageLabel.setText("No Row Selected");
				return;
			}

			ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
			rule = (String) table.getValueAt(row, 1);
			FirewallRule r = new FirewallRule(rule);
			oos.writeObject(r); // send the object
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());
			int count = ois.readInt(); // read back the number of rows deleted
			oos.close();
			ois.close();

			DefaultTableModel tm = (DefaultTableModel) table.getModel();
			tm.removeRow(row); // remove the row from the screen

		} catch (IOException e) {
			e.printStackTrace();
			setMessageLabel.setText("Error processing request");
		} finally {
			setMessageLabel.setText("Rule \"" + rule + "\" Deleted");
		}
	}

	/**
	 * viewRule
	 * 
	 * View Rules added already in firewall.
	 * 
	 * @throws ClassNotFoundException
	 * 
	 * @param evt
	 */
	private void viewRule(ActionEvent evt) throws ClassNotFoundException {

		clearResults();
		DefaultTableModel tm = (DefaultTableModel) table.getModel();

		try {

			URL link = new URL("http://192.168.122.112:8080/firewallservlet/firewallViewRules");
			// URL link = new URL("http://localhost:8080/FirewallViewRules");
			// URL link = new URL( getCodeBase() + "/firewallViewRules");
			HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();

			urlconnection.setDoOutput(true);
			urlconnection.setDoInput(true);
			urlconnection.setUseCaches(false);
			urlconnection.setDefaultUseCaches(false);
			urlconnection.setRequestProperty("Content-Type", "application/octet-stream");
			ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());

			while (true) {

				FirewallRule r = (FirewallRule) ois.readObject();
				if (r.isEnd()) // true boolean indicates last rule
					break;
				else {

					Object[] row = { tm.getRowCount(), r.getRule() };
					tm.addRow(row);
				}
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			setMessageLabel(tm.getRowCount() + " Rules in database");
		}

		// -A INPUT -s 65.55.44.100 -j DROP
	}

	/**
	 * addRule
	 * 
	 * Clear the Update tab fields
	 * 
	 * @param evt
	 */
	private void addRule(java.awt.event.ActionEvent evt) throws IOException {
		Integer count = null;
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		FirewallRule r = null;
		try {
			URL link = new URL("http://192.168.122.112:8080/firewallservlet/firewallAddRule");
			// URL link = new
			// URL("http://localhost:8080/servlet/FirewallRuleAdd");
			HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();

			urlconnection.setDoOutput(true);
			urlconnection.setDoInput(true);
			urlconnection.setUseCaches(false);
			urlconnection.setDefaultUseCaches(false);

			// Specify the content type that we will send binary data
			urlconnection.setRequestProperty("Content-Type", "application/octet-stream");

			ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
			//String newRule = text.getText();
			r = new FirewallRule("put text area here");
			oos.writeObject(r);
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());
			count = ois.readInt(); // read back int
			oos.close();
			ois.close();

		} catch (Exception ex) {
			setMessageLabel("Unable to process request " + ex.toString());
		} finally {
			Object[] row = { tm.getRowCount(), r.getRule() };
			tm.addRow(row);
			if (count.compareTo(0) >= 0)
				setMessageLabel("Rule added");
			else
				setMessageLabel("Error when adding rule.");
		}

	}

	/**
	 * setMessageLabel
	 * 
	 * changes the text in the message label at the bottom of the applet.
	 * 
	 * @param msg
	 *            String to be placed in the message label
	 */
	public void setMessageLabel(String msg) {

		SwingUtilities.invokeLater(new Runnable() { // inner class to ensure GUI													// updates properly
			public void run() {
				setMessageLabel.setText(msg);
			}
		});

	}
	
	public void displayInterfaces(NetworkInterface netint) throws SocketException {

		SwingUtilities.invokeLater(new Runnable() { // inner class to ensure GUI													// updates properly
			public void run() {
				textArea.append("Display name: "+ netint.getDisplayName() + "\n");
				
				textArea.append("Name: " + netint.getName() + "\n");
			        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
			        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			        	textArea.append("InetAddress: " + inetAddress + "\n");
			        }
			        textArea.append("\n");
			}
		});

	}
	

	// Method to clear the rule table of any data.
	public void clearResults() {
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		tm.setRowCount(0); // clear the table
	}

}

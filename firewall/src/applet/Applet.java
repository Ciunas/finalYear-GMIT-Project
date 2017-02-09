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
import javax.swing.text.MaskFormatter;

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
import java.text.ParseException;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JFormattedTextField;

public class Applet extends JApplet {
	public Applet() {
		setSize(new Dimension(800, 800));
	}

	private static final long serialVersionUID = 1L;
	private JLabel setMessageLabel;
	private JPanel panel;
	private JLabel lblFirewall;
	private JPanel panel_1;
	private JPanel panel_5;
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
	private JLabel lblSetDefaultChain;
	private JCheckBox input;
	private JCheckBox output;
	private JCheckBox forward;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JPanel panel_10;
	private JPanel panel_17;
	private JPanel panel_20;
	private JTabbedPane pane;
	private JPanel panel_22;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JPanel panel_23;
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
	protected boolean in = false;
	protected boolean out = false;
	protected boolean forw = false;
	protected boolean chBox2;
	protected boolean chBox3;
	private JPanel panel_24;
	private JPanel panel_33;
	private JPanel panel_34;
	private JPanel panel_35;
	private JButton btnPressToImplement;
	private JPanel panel_37;
	private JLabel lblTo;
	private JPanel panel_38;
	private JCheckBox chckbxNewCheckBox_8;
	private JCheckBox chckbxNewCheckBox_9;
	private JButton btnPressToImplement_1;
	private JPanel panel_11;
	private JLabel lblPressToDelete;
	private JButton btnNewButton;
	private JPanel panel_18;
	private JPanel panel_19;
	private JFormattedTextField textField;
	private JLabel lblUseThsiTo;
	private JLabel lblEnterIpTo;
	private MaskFormatter mf;
	private JButton btnNewButton_7;
	private JPanel panel_32;
	private JLabel lblPressToAllow;
	private JPanel panel_39;
	private JLabel lblNewLabel;
	private JTextField textField_1;
	protected boolean chBox9;
	protected boolean chBox8;
	private JPanel panel_40;
	private JScrollPane scrollPane;
	private JButton btnNewButton_9;
	private JTextArea textArea_1;
	private JPanel panel_43;
	private JPanel panel_45;
	private JPanel panel_4;
	private JLabel label;
	private JLabel lblBlockASpecific;
	private JTextField textField_2;
	private JPanel panel_13;
	private JLabel lblNewLabel_2;
	private JPanel panel_12;
	private JPanel panel_15;
	private JPanel panel_14;
	private JPanel panel_16;
	private JPanel panel_21;
	private JPanel panel_36;
	private JPanel panel_41;
	private JPanel panel_42;
	private JPanel panel_44;

	@Override
	public void init() {

		/* Create and display the applet */
		try {
			java.awt.EventQueue.invokeAndWait(new Runnable() {
				public void run() {

					try {
						// UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
						// UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
						// UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
						// UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
						// UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
						// UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
						// UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
						// UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
						// UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
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

					// JFrame frame = new JFrame();
					// PasswordFrame pframe = null;
					// pframe = new PasswordFrame(frame);
					// pframe.setVisible(true);
					// if (pframe.isSucceeded()) {
					// initComponents();
					// getContentPane().setBackground(Color.WHITE);
					// } else {
					// System.out.println("You loose");
					// }

					initComponents();
					getContentPane().setBackground(Color.WHITE);
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
		
		// Tab1////////////////////////////////////////////////////////////////////////////////////////////

		panel_22 = new JPanel();
		panel_22.setPreferredSize(new Dimension(10, 20));
		panel_20.add(panel_22, BorderLayout.SOUTH);
		panel_22.setLayout(new GridLayout(1, 2, 0, 0));
		panel_23 = new JPanel();
		panel_23.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_20.add(panel_23, BorderLayout.CENTER);

		ImageIcon img = new ImageIcon(getClass().getResource("/resources/add.jpg"));
		panel_23.setLayout(new BorderLayout(0, 0));

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
		btnNewButton_8.setPreferredSize(new Dimension(275, 25));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				try {
					viewInterfaces(arg0);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		panel_25.add(btnNewButton_8, BorderLayout.NORTH);

		panel_10 = new JPanel();
		panel_23.add(panel_10);
		panel_10.setMinimumSize(new Dimension(10, 8));
		panel_10.setLayout(new GridLayout(2, 2, 0, 0));

		panel_33 = new JPanel();
		panel_33.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, UIManager.getColor("CheckBoxMenuItem.acceleratorForeground")));
		panel_10.add(panel_33);
		panel_33.setLayout(new BorderLayout(0, 0));

		btnPressToImplement_1 = new JButton("Press to Implement");
		btnPressToImplement_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FirewallRule r = null;
				try {
					r = new FirewallRule();
				} catch (SocketException e1) {
					e1.printStackTrace();
				}
				r.myVector.add("-F");
				try {
					addRule(arg0, r);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		panel_33.add(btnPressToImplement_1, BorderLayout.SOUTH);

		panel_11 = new JPanel();
		panel_33.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));

		lblPressToDelete = new JLabel("Press to delete all firewalll rules in databse");
		lblPressToDelete.setFont(new Font("Bitstream Charter", Font.BOLD, 28));
		panel_11.add(lblPressToDelete, BorderLayout.CENTER);

		panel_24 = new JPanel();
		panel_24.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_10.add(panel_24);
		panel_24.setLayout(new BorderLayout(0, 0));

		btnPressToImplement = new JButton("Press to Implement");
		btnPressToImplement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirewallRule r = null;
				try {
					r = new FirewallRule();
				} catch (SocketException e2) {
					e2.printStackTrace();
				}

				if (chBox9) {
					r.myVector.add("-A INPUT -p icmp --icmp-type echo-request -j ACCEPT");
					chBox9 = false;
				}
				if (chBox8) {
					r.myVector.add("-A OUTPUT -p icmp --icmp-type echo-reply -j ACCEPT");
					chBox8 = false;
				}

				try {
					addRule(e, r);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		panel_24.add(btnPressToImplement, BorderLayout.SOUTH);

		panel_37 = new JPanel();
		panel_24.add(panel_37, BorderLayout.CENTER);
		panel_37.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_42 = new JPanel();
		panel_37.add(panel_42);

		lblTo = new JLabel("This gives the abiltiy to ping ");
		panel_42.add(lblTo);
		lblTo.setFont(new Font("Bitstream Charter", Font.BOLD, 28));

		panel_38 = new JPanel();
		panel_37.add(panel_38);

		chckbxNewCheckBox_8 = new JCheckBox("In->Out");
		panel_38.add(chckbxNewCheckBox_8);
		chckbxNewCheckBox_8.setFont(new Font("Dialog", Font.BOLD, 18));
		chckbxNewCheckBox_8.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				chBox8 = true;
			}
		});

		chckbxNewCheckBox_9 = new JCheckBox("Out->In");
		panel_38.add(chckbxNewCheckBox_9);
		chckbxNewCheckBox_9.setFont(new Font("Dialog", Font.BOLD, 18));
		chckbxNewCheckBox_9.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				chBox9 = true;
			}
		});

		panel_34 = new JPanel();
		panel_34.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_10.add(panel_34);
		panel_34.setLayout(new BorderLayout(0, 0));

		btnNewButton = new JButton("Press To Implement");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String temp = textField.getText();
				FirewallRule r = null;
				try {
					r = new FirewallRule();
				} catch (SocketException e1) {
					e1.printStackTrace();
				}
				r.myVector.add("-A INPUT -s " + temp + " -j DROP");
				try {
					addRule(arg0, r);
				} catch (IOException e) {
					e.printStackTrace();
				}
				textField.setText("");

			}
		});
		panel_34.add(btnNewButton, BorderLayout.SOUTH);

		panel_18 = new JPanel();
		panel_34.add(panel_18, BorderLayout.CENTER);
		panel_18.setLayout(new GridLayout(2, 1, 0, 0));

		lblUseThsiTo = new JLabel("Use this to block a specific IP address");
		lblUseThsiTo.setFont(new Font("Bitstream Charter", Font.BOLD, 28));
		panel_18.add(lblUseThsiTo);

		panel_19 = new JPanel();
		panel_18.add(panel_19);

		lblEnterIpTo = new JLabel("ENter IP to Block");
		panel_19.add(lblEnterIpTo);

		try {
			mf = new MaskFormatter("###.###.###.###");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		textField = new JFormattedTextField(mf);
		panel_19.add(textField);
		textField.setColumns(10);

		panel_35 = new JPanel();
		panel_35.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_10.add(panel_35);
		panel_35.setLayout(new BorderLayout(0, 0));

		btnNewButton_7 = new JButton("Press To Implement");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FirewallRule r = null;
				try {
					r = new FirewallRule();
				} catch (SocketException e2) {
					e2.printStackTrace();
				}
				String temp = textField_1.getText();
				r.myVector.add("-A OUTPUT -p udp -o " + temp + " --dport 53 -j ACCEPT");
				r.myVector.add("-A INPUT -p udp -i " + temp + " --sport 53 -j ACCEPT");
				try {
					addRule(e, r);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				textField_1.setText("");

			}
		});
		panel_35.add(btnNewButton_7, BorderLayout.SOUTH);

		panel_32 = new JPanel();
		panel_35.add(panel_32, BorderLayout.CENTER);
		panel_32.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_44 = new JPanel();
		panel_32.add(panel_44);

		lblPressToAllow = new JLabel("Press To allow DNS ");
		panel_44.add(lblPressToAllow);
		lblPressToAllow.setFont(new Font("Bitstream Charter", Font.BOLD, 28));

		panel_39 = new JPanel();
		panel_32.add(panel_39);

		lblNewLabel = new JLabel("Enter Interface");
		panel_39.add(lblNewLabel);

		textField_1 = new JTextField();
		panel_39.add(textField_1);
		textField_1.setColumns(10);

		btnNewButton_6 = new JButton("View Current Rules");
		btnNewButton_6.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_22.add(btnNewButton_6);

		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					viewRule(evt);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				btnNewButton_5.setEnabled(true);
			}
		});

		btnNewButton_5 = new JButton(" Delete a Current Rule");
		btnNewButton_5.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_22.add(btnNewButton_5);
		btnNewButton_5.setEnabled(false);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_20.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("Firewall Configuration", SwingConstants.CENTER);
		label.setOpaque(true);
		label.setForeground(Color.RED);
		label.setFont(new Font("Dialog", Font.BOLD, 26));
		label.setBackground(Color.DARK_GRAY);
		panel_4.add(label, BorderLayout.NORTH);

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
		panel_2.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));

		panel_40 = new JPanel();
		panel_7.add(panel_40, BorderLayout.WEST);
		panel_40.setMaximumSize(new Dimension(32767, 200));
		panel_40.setLayout(new BorderLayout(0, 0));

		textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setMaximumSize(new Dimension(2147483647, 400));
		textArea_1.setFont(new Font("FreeSerif", Font.PLAIN, 18));
		
		
		scrollPane = new JScrollPane(textArea_1);
		panel_40.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setMaximumSize(new Dimension(32767, 400));
		

		btnNewButton_9 = new JButton("List Network Interfaces");
		btnNewButton_9.setMinimumSize(new Dimension(204, 25));
		btnNewButton_9.setMaximumSize(new Dimension(204, 25));
		btnNewButton_9.setPreferredSize(new Dimension(275, 25));
		panel_40.add(btnNewButton_9, BorderLayout.NORTH);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_1.setText("");
				try {
					viewInterfaces(arg0);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});

		panel_5 = new JPanel();
		panel_5.setMinimumSize(new Dimension(10, 8));
		panel_7.add(panel_5);
		panel_5.setLayout(new GridLayout(2, 2, 0, 0));

		panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_5.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));

		btnAdd = new JButton((Icon) null);
		btnAdd.setText("Press To Implement");
		btnAdd.setPreferredSize(new Dimension(171, 25));
		panel_17.add(btnAdd, BorderLayout.SOUTH);

		panel_31 = new JPanel();
		panel_17.add(panel_31, BorderLayout.CENTER);
		panel_31.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_41 = new JPanel();
		panel_31.add(panel_41);
		
		lblBlockASpecific = new JLabel("Block a Specific Port");
		lblBlockASpecific.setFont(new Font("Bitstream Charter", Font.BOLD, 28));
		panel_41.add(lblBlockASpecific);
		
		panel_13 = new JPanel();
		panel_31.add(panel_13);
		
		lblNewLabel_2 = new JLabel("Enter Port number");
		panel_13.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_13.add(textField_2);
		textField_2.setColumns(10);

		panel_43 = new JPanel();
		panel_43.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_5.add(panel_43);
		panel_43.setLayout(new BorderLayout(0, 0));

		btnNewButton_3 = new JButton((Icon) null);
		btnNewButton_3.setText("Press To Implement");
		btnNewButton_3.setPreferredSize(new Dimension(171, 25));
		panel_43.add(btnNewButton_3, BorderLayout.SOUTH);

		panel_3 = new JPanel();
		panel_43.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));

		panel_21 = new JPanel();
		panel_3.add(panel_21);

		lblSetDefaultChain = new JLabel("Set default Chain policies", SwingConstants.CENTER);
		panel_21.add(lblSetDefaultChain);
		lblSetDefaultChain.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblSetDefaultChain.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSetDefaultChain.setFont(new Font("FreeSerif", Font.BOLD, 22));

		panel_15 = new JPanel();
		panel_3.add(panel_15);
		panel_15.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		input = new JCheckBox("Input");
		panel_15.add(input);
		input.setFont(new Font("Dialog", Font.BOLD, 18));

		output = new JCheckBox("Output");
		panel_15.add(output);
		output.setFont(new Font("Dialog", Font.BOLD, 18));

		forward = new JCheckBox("Forward");
		panel_15.add(forward);
		forward.setFont(new Font("Dialog", Font.BOLD, 18));
		forward.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				forw = true;
			}
		});
		output.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				out = true;
			}
		});
		input.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				in = true;
			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.setSelected(false); // Reset check boxes
				output.setSelected(false);
				forward.setSelected(false);
				FirewallRule r = null;
				try {
					r = new FirewallRule();
				} catch (SocketException e1) {
					e1.printStackTrace();
				}

				if (in) {
					r.myVector.add("-P INPUT ACCEPT");
					in = false;
				}
				if (out) {
					r.myVector.add("-P FORWARD DROP");
					out = false;
				}
				if (forw) {
					r.myVector.add("-P OUTPUT DROP");
					forw = false;
				}

				// addRule(e, r);
			}
		});

		panel_45 = new JPanel();
		panel_45.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_5.add(panel_45);
		panel_45.setLayout(new BorderLayout(0, 0));
		panel_9 = new JPanel();
		panel_45.add(panel_9);
				panel_9.setLayout(new GridLayout(2, 1, 0, 0));
				
				panel_36 = new JPanel();
				panel_9.add(panel_36);
		
				lblNewLabel_1 = new JLabel("Alllow  ssh incoming/outgoing");
				panel_36.add(lblNewLabel_1);
				lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_12 = new JPanel();
		panel_9.add(panel_12);

		chckbxNewCheckBox_3 = new JCheckBox("Outgoing");
		panel_12.add(chckbxNewCheckBox_3);
		chckbxNewCheckBox_3.setFont(new Font("Dialog", Font.BOLD, 18));
		
				chckbxNewCheckBox_2 = new JCheckBox("Incoming");
				panel_12.add(chckbxNewCheckBox_2);
				chckbxNewCheckBox_2.setFont(new Font("Dialog", Font.BOLD, 18));
				chckbxNewCheckBox_2.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						chBox2 = true;
					}
				});
		chckbxNewCheckBox_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				chBox3 = true;
			}
		});

		btnNewButton_4 = new JButton((Icon) null);
		btnNewButton_4.setText("Press To Implement");
		btnNewButton_4.setPreferredSize(new Dimension(171, 25));
		panel_45.add(btnNewButton_4, BorderLayout.SOUTH);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chckbxNewCheckBox_3.setSelected(false); // Reset check boxes
				chckbxNewCheckBox_2.setSelected(false);
				FirewallRule r = null;
				try {
					r = new FirewallRule();
				} catch (SocketException e2) {
					e2.printStackTrace();
				}

				if (chBox2) {
					r.myVector.add("-A INPUT -i eth0 -p tcp --dport 22 -m state --state NEW,ESTABLISHED -j ACCEPT");
					chBox2 = false;
				}
				if (chBox3) {
					r.myVector.add("-A OUTPUT -o eth0 -p tcp --sport 22 -m state --state ESTABLISHED -j ACCEPT");
					chBox3 = false;
				}

				try {
					addRule(e, r);						// Call addRule method to send new rules to Servlet.
				} catch (IOException e1) {
					e1.printStackTrace();
				} 

			}
		});

		panel_26 = new JPanel();
		panel_5.add(panel_26);
		panel_26.setLayout(new BorderLayout(0, 0));

		panel_29 = new JPanel();
		panel_29.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_26.add(panel_29);
		panel_29.setLayout(new BorderLayout(0, 0));

		panel_28 = new JPanel();
		panel_28.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rule", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_26.add(panel_28);
		panel_28.setLayout(new BorderLayout(0, 0));

		panel_27 = new JPanel();
		panel_28.add(panel_27);
		panel_27.setLayout(new GridLayout(2, 1, 0, 0));
		
		panel_14 = new JPanel();
		panel_27.add(panel_14);

		lblAllowhttphttpsTrafficOutgoing = new JLabel("Allow Http/Https traffic outgoing", SwingConstants.CENTER);
		panel_14.add(lblAllowhttphttpsTrafficOutgoing);
		lblAllowhttphttpsTrafficOutgoing.setFont(new Font("FreeSerif", Font.BOLD, 22));

		panel_16 = new JPanel();
		panel_27.add(panel_16);

		chckbxNewCheckBox_7 = new JCheckBox("Http");
		panel_16.add(chckbxNewCheckBox_7);
		chckbxNewCheckBox_7.setFont(new Font("FreeSerif", Font.BOLD, 22));

		chckbxNewCheckBox_6 = new JCheckBox("Https");
		panel_16.add(chckbxNewCheckBox_6);
		chckbxNewCheckBox_6.setFont(new Font("FreeSerif", Font.BOLD, 22));

		btnAdd_1 = new JButton((Icon) null);
		btnAdd_1.setText("Press To Implement");
		btnAdd_1.setPreferredSize(new Dimension(171, 25));
		panel_28.add(btnAdd_1, BorderLayout.SOUTH);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirewallRule r = null;
				try {
					r = new FirewallRule();
				} catch (SocketException e2) {
					e2.printStackTrace();
				}

				r.myVector.add("-I INPUT -p tcp --dport " + textField_2.getText() +" -j ACCEPT");				//Create Rule to block specified port

				try {
					addRule(e, r); // Call addRule method to send new rules to Servlet.
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				textField_2.setText("");
			}
		});

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

		panel_30 = new JPanel();
		panel_2.add(panel_30, BorderLayout.NORTH);
		panel_30.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_30.setLayout(new BorderLayout(0, 0));

		lblFirewall_1 = new JLabel("Firewall Configuration", SwingConstants.CENTER);
		lblFirewall_1.setOpaque(true);
		lblFirewall_1.setBackground(Color.DARK_GRAY);
		lblFirewall_1.setForeground(Color.RED);
		lblFirewall_1.setFont(new Font("Dialog", Font.BOLD, 26));
		panel_30.add(lblFirewall_1, BorderLayout.CENTER);
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
		//URL link = new URL("http://localhost:8080/FirewallDeleteRule");
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
			oos.writeObject(r); 															// send the object
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());
			int count = ois.readInt(); 														// read back the number of rows deleted
			oos.close();
			ois.close();

			DefaultTableModel tm = (DefaultTableModel) table.getModel();
			tm.removeRow(row); 																// remove the row from the screen

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
			//URL link = new URL("http://localhost:8080/FirewallViewRules");
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

	}

	/**
	 * addRule
	 * 
	 * Clear the Update tab fields
	 * 
	 * @param evt
	 */
	private void addRule(java.awt.event.ActionEvent evt, FirewallRule r) throws IOException {
		Integer count = null;
		DefaultTableModel tm = (DefaultTableModel) table.getModel();

		try {
			URL link = new URL("http://192.168.122.112:8080/firewallservlet/firewallAddRule");
			//URL link = new URL("http://localhost:8080/FirewallAddRule");
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
			count = ois.readInt(); // read back int
			oos.close();
			ois.close();

		} catch (Exception ex) {
			setMessageLabel("Unable to process request " + ex.toString());
		} finally {
			for(int i =0; i< r.myVector.size(); i++){
				Object[] row = { tm.getRowCount(), r.myVector.get(i) };
				tm.addRow(row);
			}

			if (count.compareTo(0) >= 0)
				setMessageLabel("Rule added");
			else
				setMessageLabel("Error when adding rule.");
		}

	}

	/**
	 * viewInterfaces
	 * 
	 * View the interfaces no firewall machine
	 * 
	 * @param evt
	 * @throws ClassNotFoundException
	 */
	private void viewInterfaces(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
		FirewallRule r = null;
		try {
			URL link = new URL("http://192.168.122.112:8080/firewallservlet/firewallViewInterfaces");
			//URL link = new URL("http://localhost:8080/FirewallViewInterfaces");
			HttpURLConnection urlconnection = (HttpURLConnection) link.openConnection();

			urlconnection.setDoOutput(true);
			urlconnection.setDoInput(true);
			urlconnection.setUseCaches(false);
			urlconnection.setDefaultUseCaches(false);
			urlconnection.setRequestProperty("Content-Type", "application/octet-stream");
			ObjectOutputStream oos = new ObjectOutputStream(urlconnection.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(urlconnection.getInputStream());

			r = (FirewallRule) ois.readObject();
			System.out.println(r.Interfaces.get(0));
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			displayInterfaces(r);
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

		SwingUtilities.invokeLater(new Runnable() { // inner class to ensure GUI
													// // updates properly
			public void run() {
				setMessageLabel.setText(msg);
			}
		});

	}

	public void displayInterfaces(FirewallRule r) throws SocketException {

		SwingUtilities.invokeLater(new Runnable() { // inner class to ensure GUI
													// // updates properly
			public void run() {

				for (int i = 0; i < r.Interfaces.size(); i++) {
					textArea.append(r.Interfaces.get(i));
					textArea_1.append(r.Interfaces.get(i));
				}
				textArea.append("\n");
				textArea_1.append("\n");
			}
		});

	}

	// Method to clear the rule table of any data.
	public void clearResults() {
		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		tm.setRowCount(0); // clear the table
	}

}

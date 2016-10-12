package transalteIMS;

import java.awt.EventQueue;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class IMS_GUI {
	


	private JFrame frame;

	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Translate.setClientId("4579531");
		Translate.setClientSecret("9x5MHdWiJ13cVDRx9o5tb6kPoN2hS5G4A68xFDzr3RE=");
		String englishString = "Well unwell";
		String spanishTranslation = Translate.execute(englishString, Language.GERMAN);

		System.out.println("Original english phrase: " + englishString);
		System.out.println("Translated spanish phrase: " + spanishTranslation);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMS_GUI window = new IMS_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IMS_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIms = new JLabel("IMS");
		lblIms.setBounds(240, 26, 70, 15);
		frame.getContentPane().add(lblIms);
	}
}

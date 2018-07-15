import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;

public class Frame1 {

	public JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.GRAY);
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 377, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Allow");
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "user allow");
			}
		});
		
		lblNewLabel_4 = new JLabel("No significance risks of inference on private data");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setBounds(30, 258, 233, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblNewLabel_4);
		btnNewButton.setBounds(30, 355, 76, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDeny = new JButton("Deny");
		btnDeny.setBounds(116, 355, 76, 23);
		frame.getContentPane().add(btnDeny);
		String requestData = "Persistence";
		String value = "Continuous";
		String dataset = "Activity";
		lblNewLabel = new JLabel("<html>PDM Recommendation: <b>ALLOW</b></html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(30, 45, 178, 20);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\n\t Allow \"FitPro\" access on "+requestData+" = "+value+" for " + dataset);
		lblNewLabel_1.setText("<html><b> TP_B</b> asks for:</html>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(30, 169, 76, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		label_2 = new JLabel("<html>Private data inference risk: <i>SLEEP</i><html>");
		label_2.setForeground(Color.GRAY);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(30, 272, 173, 23);
		frame.getContentPane().add(label_2);
		Image img= new ImageIcon(this.getClass().getResource("/icon2.png")).getImage();
		
		lblNewLabel_2 = new JLabel("Max. risk value:  <45%");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(30, 294, 139, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("<html>Access your Data: <i>Continuously</i> </html>");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(30, 188, 162, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_5 = new JLabel("Reason of PDM recommendation:");
		lblNewLabel_5.setForeground(Color.GRAY);
		lblNewLabel_5.setBounds(30, 240, 178, 14);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("AID-S risk check: PASSED");
		lblNewLabel_6.setForeground(Color.GRAY);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(30, 312, 139, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JButton button = new JButton("Edit Preferences");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(202, 355, 142, 23);
		frame.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("<html><b>PDM</b> Request of Confirmation for <b>TP_B installation</b></html>");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(44, 11, 267, 23);
		frame.getContentPane().add(label_1);
		
		label_4 = new JLabel("Details:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(30, 64, 76, 23);
		frame.getContentPane().add(label_4);
		
		label_5 = new JLabel("<html>Your current preference for accessing your <i>ACTIVITY</i> data:</html>");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(30, 84, 296, 23);
		frame.getContentPane().add(label_5);
		
		label_6 = new JLabel("<html>&bull; Access your data: once (separate permission for each workout)</html>");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(30, 103, 321, 23);
		frame.getContentPane().add(label_6);
		
		label_7 = new JLabel("<html>&bull; Availability to modify access upon confirmation: Yes</html>");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(30, 123, 312, 23);
		frame.getContentPane().add(label_7);
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(img));
		label_3.setBounds(192, 144, 103, 112);
		frame.getContentPane().add(label_3);
		
	}
}

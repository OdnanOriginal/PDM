import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;

public class Frame1 {

	public JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;

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
		frame.setBounds(100, 100, 346, 184);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Allow");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "user allow");
			}
		});
		btnNewButton.setBounds(44, 109, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDeny = new JButton("Deny");
		btnDeny.setBounds(167, 109, 89, 23);
		frame.getContentPane().add(btnDeny);
		String requestData = "Persistence";
		String value = "Continuous";
		String dataset = "Activity";
		lblNewLabel = new JLabel("Your PDM recommends the following:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 11, 246, 35);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\n\t Allow TP with "+requestData+" = "+value+" for dataset#" + dataset);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(20, 38, 362, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		label_1 = new JLabel("AID-S Inference Risk Check: Passed ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(44, 57, 362, 23);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel(" Dataset at Risk: Dataset#Sleep risk value<45%  ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(44, 75, 362, 23);
		frame.getContentPane().add(label_2);
		
	}
}

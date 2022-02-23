import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LaurieMOOframe {

	private JFrame frame;
	private JTextField secretValueField;
	private JTextField guessField;
	
	RandomMooValue temp = new RandomMooValue();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaurieMOOframe window = new LaurieMOOframe();
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
	public LaurieMOOframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.getContentPane().setForeground(Color.ORANGE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel bigMooNumber = new JLabel("0");
		bigMooNumber.setHorizontalAlignment(SwingConstants.CENTER);
		bigMooNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		bigMooNumber.setBounds(182, 120, 63, 25);
		frame.getContentPane().add(bigMooNumber);
		
		JLabel littleMooNumber = new JLabel("0");
		littleMooNumber.setHorizontalAlignment(SwingConstants.CENTER);
		littleMooNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		littleMooNumber.setBounds(182, 159, 63, 21);
		frame.getContentPane().add(littleMooNumber);
		
		JLabel guessAnswer = new JLabel("Yes");
		guessAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		guessAnswer.setFont(new Font("Tahoma", Font.BOLD, 12));
		guessAnswer.setBounds(182, 195, 63, 22);
		frame.getContentPane().add(guessAnswer);
		
		secretValueField = new JTextField();
		secretValueField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userInput = Integer.parseInt(secretValueField.getText());
				if(temp.setSecretValue(userInput) == false) {
				}
				else {
					temp.setSecretValue(userInput);
				}
			}
		});
		secretValueField.setFont(new Font("Tahoma", Font.BOLD, 12));
		secretValueField.setBounds(154, 24, 115, 35);
		frame.getContentPane().add(secretValueField);
		secretValueField.setColumns(10);
		
		guessField = new JTextField();
		guessField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int guess = Integer.parseInt(guessField.getText());
				bigMooNumber.setText(String.valueOf(temp.getBigMooCount(guess)));
				littleMooNumber.setText(String.valueOf(temp.getLittleMooCount(guess)));
				if (temp.isCorrectGuess(guess) == true) {
					guessAnswer.setText("Yes");
				}
				else {
					guessAnswer.setText("No");
				}
			}
		});
		guessField.setFont(new Font("Tahoma", Font.BOLD, 12));
		guessField.setBounds(155, 70, 114, 35);
		frame.getContentPane().add(guessField);
		guessField.setColumns(10);
		
		JButton randomButton = new JButton("Random");
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setSecretValue();
				temp.getSecretValue();
			}
		});
		randomButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		randomButton.setBounds(322, 39, 85, 21);
		frame.getContentPane().add(randomButton);
		
		JLabel secretValueLabel = new JLabel("Secret Value");
		secretValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		secretValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		secretValueLabel.setBounds(10, 36, 85, 24);
		frame.getContentPane().add(secretValueLabel);
		
		JLabel guessLabel = new JLabel("Guess");
		guessLabel.setHorizontalAlignment(SwingConstants.CENTER);
		guessLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		guessLabel.setBounds(20, 70, 75, 35);
		frame.getContentPane().add(guessLabel);
		
		JLabel bigMooLabel = new JLabel("Number of MOO!");
		bigMooLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bigMooLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		bigMooLabel.setBounds(10, 115, 96, 34);
		frame.getContentPane().add(bigMooLabel);
		
		JLabel littleMooLabel = new JLabel("Number of moo.");
		littleMooLabel.setHorizontalAlignment(SwingConstants.CENTER);
		littleMooLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		littleMooLabel.setBounds(10, 159, 96, 26);
		frame.getContentPane().add(littleMooLabel);
		
		JLabel guessCorrectLabel = new JLabel("Is guess correct:");
		guessCorrectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		guessCorrectLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		guessCorrectLabel.setBounds(10, 195, 96, 29);
		frame.getContentPane().add(guessCorrectLabel);
	}
}

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;


public class SignUpGUI extends JFrame {

	private GUIController controller;
	private JPanel contentPane;
	private HashMap<String, JTextField> textFields;
	private HashMap<String, JButton> buttons;

	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpGUI frame = new SignUpGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUpGUI() {
		//JFrame
		setSize(1139, 830);
		setTitle("Account Login");
		Image img = new ImageIcon(this.getClass().getResource("/film.png")).getImage(); 
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//content Pane
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(111, 60, 897, 675);
		contentPane.add(panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(105, 196, 684, 283);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 284, 283);
		panel_3.add(panel_1);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setLayout(null);
		
		JLabel lblPassword = new JLabel("* Password");
		lblPassword.setBounds(0, 213, 117, 25);
		panel_1.add(lblPassword);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblPassword.setBackground(Color.BLACK);
		
		textField = new JTextField(20);
		textField.setBounds(0, 179, 284, 25);
		panel_1.add(textField);
		textField.setSelectedTextColor(Color.WHITE);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setCaretColor(Color.BLACK);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField.setBackground(Color.WHITE);
		textField.setActionCommand("");
		
		JLabel lblEmail = new JLabel("* Email");
		lblEmail.setBounds(0, 145, 117, 25);
		panel_1.add(lblEmail);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblEmail.setBackground(Color.BLACK);
		
		JLabel lblFullName = new JLabel("* Full Name");
		lblFullName.setBounds(0, 9, 117, 25);
		panel_1.add(lblFullName);
		lblFullName.setForeground(Color.BLACK);
		lblFullName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFullName.setBackground(Color.BLACK);
		
		textField_1 = new JTextField(20);
		textField_1.setBounds(0, 43, 284, 25);
		panel_1.add(textField_1);
		textField_1.setSelectedTextColor(Color.WHITE);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setCaretColor(Color.BLACK);
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_1.setBackground(Color.WHITE);
		textField_1.setActionCommand("");
		
		textField_2 = new JTextField(20);
		textField_2.setBounds(0, 111, 284, 25);
		panel_1.add(textField_2);
		textField_2.setSelectedTextColor(Color.WHITE);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setCaretColor(Color.BLACK);
		textField_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_2.setBackground(Color.WHITE);
		textField_2.setActionCommand("");
		
		JLabel lblCity = new JLabel("* City");
		lblCity.setBounds(0, 77, 117, 25);
		panel_1.add(lblCity);
		lblCity.setForeground(Color.BLACK);
		lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCity.setBackground(Color.BLACK);
		
		passwordField = new JPasswordField(20);
		passwordField.setBounds(0, 247, 284, 25);
		panel_1.add(passwordField);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setCaretColor(Color.BLACK);
		passwordField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		passwordField.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(400, 0, 284, 283);
		panel_3.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		
		JLabel lblNameOnCard = new JLabel("* Name On Card");
		lblNameOnCard.setForeground(Color.BLACK);
		lblNameOnCard.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNameOnCard.setBackground(Color.BLACK);
		lblNameOnCard.setBounds(0, 213, 184, 25);
		panel_2.add(lblNameOnCard);
		
		textField_3 = new JTextField(20);
		textField_3.setSelectedTextColor(Color.WHITE);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setCaretColor(Color.BLACK);
		textField_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_3.setBackground(Color.WHITE);
		textField_3.setActionCommand("");
		textField_3.setBounds(0, 179, 284, 25);
		panel_2.add(textField_3);
		
		JLabel lblExpirationDate = new JLabel("* Expiration Date");
		lblExpirationDate.setForeground(Color.BLACK);
		lblExpirationDate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblExpirationDate.setBackground(Color.BLACK);
		lblExpirationDate.setBounds(0, 145, 184, 25);
		panel_2.add(lblExpirationDate);
		
		JLabel lblCardNumber = new JLabel("* Card Number");
		lblCardNumber.setForeground(Color.BLACK);
		lblCardNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCardNumber.setBackground(Color.BLACK);
		lblCardNumber.setBounds(0, 9, 154, 25);
		panel_2.add(lblCardNumber);
		
		textField_4 = new JTextField(20);
		textField_4.setSelectedTextColor(Color.WHITE);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setCaretColor(Color.BLACK);
		textField_4.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_4.setBackground(Color.WHITE);
		textField_4.setActionCommand("");
		textField_4.setBounds(0, 43, 284, 25);
		panel_2.add(textField_4);
		
		textField_5 = new JTextField(20);
		textField_5.setSelectedTextColor(Color.WHITE);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_5.setCaretColor(Color.BLACK);
		textField_5.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_5.setBackground(Color.WHITE);
		textField_5.setActionCommand("");
		textField_5.setBounds(0, 111, 284, 25);
		panel_2.add(textField_5);
		
		JLabel lblCvv = new JLabel("* CVV");
		lblCvv.setForeground(Color.BLACK);
		lblCvv.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCvv.setBackground(Color.BLACK);
		lblCvv.setBounds(0, 77, 117, 25);
		panel_2.add(lblCvv);
		
		passwordField_1 = new JPasswordField(20);
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField_1.setCaretColor(Color.BLACK);
		passwordField_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		passwordField_1.setBackground(Color.WHITE);
		passwordField_1.setBounds(0, 247, 284, 25);
		panel_2.add(passwordField_1);
		
		JLabel lblNewLabel = new JLabel("SIGN UP");
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblNewLabel.setBounds(104, 67, 260, 47);
		panel.add(lblNewLabel);
		
		JLabel lblPleaseFillOut = new JLabel("Please fill out all required fields");
		lblPleaseFillOut.setForeground(new Color(128, 128, 128));
		lblPleaseFillOut.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblPleaseFillOut.setBackground(Color.BLACK);
		lblPleaseFillOut.setBounds(104, 136, 284, 25);
		panel.add(lblPleaseFillOut);
		
		JLabel lblMissingRequiredFields = new JLabel("**Missing required field(s)");
		lblMissingRequiredFields.setForeground(new Color(165, 42, 42));
		lblMissingRequiredFields.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblMissingRequiredFields.setBackground(Color.BLACK);
		lblMissingRequiredFields.setBounds(105, 161, 284, 25);
		panel.add(lblMissingRequiredFields);
		
		//PLEASE ADD EVENT LISTENER HERE SO THAT IT SUBMIT ALL TEXT FIELDS TO ACCOUNT DATABASE
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnSignUp.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
		btnSignUp.setBackground(new Color(100, 149, 237));
		btnSignUp.setBounds(304, 543, 288, 31);
		btnSignUp.addActionListener(controller);
		panel.add(btnSignUp);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(304, 585, 288, 27);
		panel.add(panel_4);
		
		JLabel lblAlreadyHaveAn = new JLabel("Already have an account?");
		lblAlreadyHaveAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlreadyHaveAn.setForeground(Color.GRAY);
		lblAlreadyHaveAn.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblAlreadyHaveAn.setBounds(0, 0, 178, 27);
		panel_4.add(lblAlreadyHaveAn);
		
		//PLEASE ADD EVENT LISTENER SO THIS BUTTON GOES BACK TO THE LOGIN PAGE
		JButton btnLogInHere = new JButton("Log In here");
		btnLogInHere.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnLogInHere.setBorder(null);
		btnLogInHere.setBackground(Color.WHITE);
		btnLogInHere.setBounds(188, 2, 85, 23);
		btnLogInHere.addActionListener(controller);
		panel_4.add(btnLogInHere);
	}

	public void setController(GUIController controller) {
        this.controller = controller;
	}
	
}

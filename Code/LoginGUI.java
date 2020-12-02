import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.util.HashMap;

public class LoginGUI extends JFrame{

    private JPanel contentPane;
    //private GUIController controller;
    private HashMap <String, JTextField> textFields;
    private HashMap <String, JButton> buttons;
    
    private JButton loginButton = new JButton("Login");
    private JButton signUpButton = new JButton("Sign Up");
    private JButton guestButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
        textFields = new HashMap<String,JTextField>();
        buttons = new HashMap<>();

		//JFrame
		setSize(1139, 830);
		setTitle("Cinema 480");
		Image img = new ImageIcon(this.getClass().getResource("/film.png")).getImage(); 
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//content Pane
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//Blue Pane - half of the screen
		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(new Color(100, 149, 237));
		bluePanel.setBounds(10, 11, 544, 773);
		contentPane.add(bluePanel);
		bluePanel.setLayout(null);
		Image whiteFilmIcon = new ImageIcon(this.getClass().getResource("/whiteFilm.png")).getImage();
		
		//Panel surrounding white film icon and "movie theatre" text
		JPanel movieIconPanel = new JPanel();
		movieIconPanel.setBackground(new Color(100, 149, 237));
		movieIconPanel.setBounds(128, 271, 288, 231);
		bluePanel.add(movieIconPanel);
		movieIconPanel.setLayout(null);
		
		JLabel whiteFilmIconLabel = new JLabel("");
		whiteFilmIconLabel.setBounds(76, 0, 136, 143);
		movieIconPanel.add(whiteFilmIconLabel);
		whiteFilmIconLabel.setIcon(new ImageIcon(whiteFilmIcon));
		
		JLabel lblWelcomeTo = new JLabel("CINEMA 480");
		lblWelcomeTo.setBounds(0, 177, 288, 54);
		movieIconPanel.add(lblWelcomeTo);
		lblWelcomeTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeTo.setForeground(new Color(255, 255, 255));
		lblWelcomeTo.setFont(new Font("Verdana", Font.PLAIN, 30));
		
		//White Panel - half of the screen
		JPanel whitePanel = new JPanel();
		whitePanel.setLayout(null);
		whitePanel.setBackground(new Color(255, 255, 255));
		whitePanel.setBounds(564, 11, 553, 773);
		contentPane.add(whitePanel);
		Image filmIcon = new ImageIcon(this.getClass().getResource("/film.png")).getImage();
		
		JLabel loginLabel = new JLabel("WELCOME BACK");
		loginLabel.setForeground(new Color(100, 149, 237));
		loginLabel.setBounds(132, 144, 288, 54);
		whitePanel.add(loginLabel);
		loginLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Sign in to continue");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Segoe UI Semilight", Font.BOLD, 18));
		lblNewLabel.setBounds(132, 260, 288, 31);
		whitePanel.add(lblNewLabel);
		
		JPanel LoginPanel = new JPanel();
		LoginPanel.setBackground(new Color(255, 255, 255));
		LoginPanel.setBounds(132, 368, 288, 171);
		whitePanel.add(LoginPanel);
		LoginPanel.setLayout(null);
		
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
        passwordText.setBounds(0, 131, 284, 25);
        textFields.put("password", passwordText);
		LoginPanel.add(passwordText);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		passwordLabel.setBounds(0, 92, 117, 25);
		LoginPanel.add(passwordLabel);
		
		JTextField emailText = new JTextField(20);
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailText.setActionCommand("");
		emailText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
        emailText.setBounds(0, 53, 284, 25);
        textFields.put("email", emailText);
		LoginPanel.add(emailText);
		
		JLabel label = new JLabel("Email");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		label.setBounds(0, 0, 117, 25);
		LoginPanel.add(label);
		
		//LOGIN BUTTON
		loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		loginButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
		loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setBounds(132, 566, 288, 31);
        //loginButton.addActionListener(controller);
        buttons.put("login", loginButton);
		whitePanel.add(loginButton);
		
		JPanel signUpPanel = new JPanel();
		signUpPanel.setBackground(new Color(255, 255, 255));
		signUpPanel.setBounds(195, 654, 188, 27);
		whitePanel.add(signUpPanel);
		signUpPanel.setLayout(null);
		
		JLabel NewUserLabel = new JLabel("New User?");
		NewUserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		NewUserLabel.setBounds(0, 0, 76, 27);
		signUpPanel.add(NewUserLabel);
		NewUserLabel.setForeground(new Color(128, 128, 128));
		NewUserLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		
		
		//SIGN UP BUTTON
		signUpButton.setBorder(null);
		signUpButton.setBackground(new Color(255, 255, 255));
		signUpButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		signUpButton.setBounds(86, 2, 85, 23);
        //signUpButton.addActionListener(controller);
        buttons.put("signup", signUpButton);
		signUpPanel.add(signUpButton);
		
		// GUEST BUTTON
		guestButton = new JButton("Continue as Guest");
		guestButton.setBorder(null);
		guestButton.setBackground(new Color(255, 255, 255));
		guestButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		guestButton.setBounds(132, 608, 288, 23);
        buttons.put("guest", signUpButton);
		whitePanel.add(guestButton);
		
    }
	
	public void addLoginListener(ActionListener listener)
	{
		loginButton.addActionListener(listener);
	}
	public void addSignUpListener(ActionListener listener)
	{
		signUpButton.addActionListener(listener);
	}
	public void addGuestListener(ActionListener listener)
	{
		guestButton.addActionListener(listener);
	}

    public HashMap<String, JTextField> getTextFields() {
        return textFields;
    }
    public HashMap<String, JButton> getButtons() {
        return buttons;
    }
    
    public void reset() {
		textFields.get("email").setText("");
		textFields.get("password").setText("");
	}
}
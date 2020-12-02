import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.MatteBorder;

class CancellationGUI extends JFrame {
	private JPanel blueContentPane;
	private HashMap<String, JTextField> textFields;
	private HashMap<String, JButton> buttons;
	private JTextField fullNameTextField;
	private JTextField emailTextField;
	private JPanel nonRegisteredPanel;
	private JLabel lblPleaseFillOut;
	private JLabel lblMissingRequiredFields;
	private JButton searchButton;
	private JButton btnCancel = new JButton("Cancel Ticket");
	private JTextField ticketId;
	private JTextArea ticketFound;
	private JButton returnButton;

	/**
	 * Create the frame.
	 */
	public CancellationGUI() {
		textFields = new HashMap<>();
		buttons = new HashMap<>();
		
		//JFrame
		setSize(1139, 830);
		setTitle("Cinema 480");
		Image img = new ImageIcon(this.getClass().getResource("/film.png")).getImage(); 
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//content Pane
		blueContentPane = new JPanel();
		blueContentPane.setBackground(new Color(100, 149, 237));
		blueContentPane.setLayout(null);
		setContentPane(blueContentPane);
		
		JPanel whitePanel = new JPanel();
		whitePanel.setLayout(null);
		whitePanel.setBackground(Color.WHITE);
		whitePanel.setBounds(111, 60, 897, 675);
		blueContentPane.add(whitePanel);
		
		JLabel cancelLabel = new JLabel("CANCEL TICKET");
		cancelLabel.setForeground(new Color(100, 149, 237));
		cancelLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		cancelLabel.setBounds(104, 67, 260, 47);
		whitePanel.add(cancelLabel);
		
		lblPleaseFillOut = new JLabel("Please fill out all required field(s)");
		lblPleaseFillOut.setForeground(new Color(128, 128, 128));
		lblPleaseFillOut.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		lblPleaseFillOut.setBackground(Color.BLACK);
		lblPleaseFillOut.setBounds(104, 136, 433, 25);
		whitePanel.add(lblPleaseFillOut);
		
		lblMissingRequiredFields = new JLabel("**Missing required field(s)");
		lblMissingRequiredFields.setForeground(new Color(165, 42, 42));
		lblMissingRequiredFields.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblMissingRequiredFields.setBackground(Color.BLACK);
		lblMissingRequiredFields.setBounds(105, 161, 284, 25);
		whitePanel.add(lblMissingRequiredFields);
		
		//PLEASE ADD EVENT LISTENER HERE SO THAT IT SUBMIT ALL TEXT FIELDS TO ACCOUNT DATABASE
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnCancel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
		btnCancel.setBackground(new Color(100, 149, 237));
		btnCancel.setBounds(304, 554, 288, 31);
		buttons.put("checkout", btnCancel);
		//btnCheckout.addActionListener(controller);
		whitePanel.add(btnCancel);
		
		nonRegisteredPanel = new JPanel();
		nonRegisteredPanel.setBounds(542, 151, 284, 376);
		whitePanel.add(nonRegisteredPanel);
		nonRegisteredPanel.setLayout(null);
		nonRegisteredPanel.setBackground(Color.WHITE);
		
		
		JLabel lblFullName = new JLabel("* Full Name");
		lblFullName.setBounds(0, 9, 117, 25);
		nonRegisteredPanel.add(lblFullName);
		lblFullName.setForeground(Color.BLACK);
		lblFullName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFullName.setBackground(Color.BLACK);
		
		fullNameTextField = new JTextField(20);
		fullNameTextField.setBounds(0, 46, 284, 25);
		nonRegisteredPanel.add(fullNameTextField);
		fullNameTextField.setSelectedTextColor(Color.WHITE);
		fullNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fullNameTextField.setCaretColor(Color.BLACK);
		fullNameTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		fullNameTextField.setBackground(Color.WHITE);
		textFields.put("name", fullNameTextField);
		fullNameTextField.setActionCommand("");
		
		JLabel emailLabel = new JLabel("* Email");
		emailLabel.setBounds(0, 83, 117, 25);
		nonRegisteredPanel.add(emailLabel);
		emailLabel.setForeground(Color.BLACK);
		emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		emailLabel.setBackground(Color.BLACK);
		
		emailTextField = new JTextField(20);
		emailTextField.setBounds(0, 120, 284, 25);
		nonRegisteredPanel.add(emailTextField);
		emailTextField.setSelectedTextColor(Color.WHITE);
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailTextField.setCaretColor(Color.BLACK);
		emailTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		emailTextField.setBackground(Color.WHITE);
		textFields.put("email", emailTextField);
		emailTextField.setActionCommand("");
		
		JLabel ticketIdLabel = new JLabel("* Ticket ID");
		ticketIdLabel.setBounds(115, 198, 133, 25);
		whitePanel.add(ticketIdLabel);
		ticketIdLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		ticketId = new JTextField();
		ticketId.setSelectedTextColor(Color.WHITE);
		ticketId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ticketId.setCaretColor(Color.BLACK);
		ticketId.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		ticketId.setBackground(Color.WHITE);
		ticketId.setBounds(234, 198, 246, 26);
		whitePanel.add(ticketId);
		ticketId.setColumns(10);
		
		searchButton = new JButton("Search");
		searchButton.setForeground(Color.WHITE);
		searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		searchButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
		searchButton.setBackground(new Color(100, 149, 237));
		searchButton.setBounds(234, 235, 117, 29);
		whitePanel.add(searchButton);
		
		ticketFound = new JTextArea();
		ticketFound.setEditable(false);
		ticketFound.setBounds(115, 292, 410, 235);
		ticketFound.setFont(new Font("Dialog", Font.PLAIN, 15));
		whitePanel.add(ticketFound);
		
		// cancel button
		returnButton = new JButton("Return to Menu");
		returnButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		returnButton.setBorder(null);
		returnButton.setBackground(Color.WHITE);
		returnButton.setBounds(392, 586, 117, 29);
		buttons.put("canel", returnButton);
		whitePanel.add(returnButton);
		
		
	}
	
	public void addSearchListener(ActionListener listener)
	{
		searchButton.addActionListener(listener);
	}
	
	
	public void addCancelListener(ActionListener listener)
	{
		btnCancel.addActionListener(listener);
	}
	
	public void addReturnListener(ActionListener listener)
	{
		returnButton.addActionListener(listener);
	}

	public HashMap<String, JButton> getButtons() {
		return buttons;
	}

	public HashMap<String, JTextField> getTextFields() {
		return textFields;
	}
	
	public String getTicketId() {
		return ticketId.getText();
	}
	
	public String getName() {
		return fullNameTextField.getText(); 
	}
	
	public String getEmail() {
		return emailTextField.getName();
	}
	
	public void setTicketFound(String ticket) {
		ticketFound.setText(ticket);
	}
	
	public void showNonRegisteredPanel(boolean b) {
		nonRegisteredPanel.setVisible(b);
	}
	
	public void reset() {
		ticketId.setText("");
		ticketFound.setText("");
		fullNameTextField.setText("");
		emailTextField.setText("");
	}
}
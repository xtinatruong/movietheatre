import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.MatteBorder;

class TransactionGUI extends JFrame {
	private JPanel blueContentPane;
	private HashMap<String, JTextField> textFields;
	private HashMap<String, JButton> buttons;
	private JTextField fullNameTextField;
	private JTextField emailTextField;
	private JTextField expDateTextField;
	private JTextField cardNoTextField;
	private JTextField cvvTextField;
	private JTextField nameOnCardTextField;
	private JTextArea ticketList;
	private JPanel cardInfoPanel;
	private JLabel lblPleaseFillOut;
	private JLabel lblMissingRequiredFields;
	
	private JButton btnCheckout = new JButton("Purchase");

	/**
	 * Create the frame.
	 */
	public TransactionGUI() {
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
		
		JLabel checkoutLabel = new JLabel("CHECKOUT");
		checkoutLabel.setForeground(new Color(100, 149, 237));
		checkoutLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		checkoutLabel.setBounds(104, 67, 260, 47);
		whitePanel.add(checkoutLabel);
		
		lblPleaseFillOut = new JLabel("Please fill out all required fields");
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
		btnCheckout.setForeground(Color.WHITE);
		btnCheckout.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnCheckout.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
		btnCheckout.setBackground(new Color(100, 149, 237));
		btnCheckout.setBounds(304, 571, 288, 31);
		buttons.put("checkout", btnCheckout);
		//btnCheckout.addActionListener(controller);
		whitePanel.add(btnCheckout);
		
		cardInfoPanel = new JPanel();
		cardInfoPanel.setBounds(542, 80, 284, 447);
		whitePanel.add(cardInfoPanel);
		cardInfoPanel.setLayout(null);
		cardInfoPanel.setBackground(Color.WHITE);
		
		JLabel lblNameOnCard = new JLabel("* Name On Card");
		lblNameOnCard.setForeground(Color.BLACK);
		lblNameOnCard.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNameOnCard.setBackground(Color.BLACK);
		lblNameOnCard.setBounds(0, 379, 184, 25);
		cardInfoPanel.add(lblNameOnCard);
		
		expDateTextField = new JTextField(20);
		expDateTextField.setSelectedTextColor(Color.WHITE);
		expDateTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		expDateTextField.setCaretColor(Color.BLACK);
		expDateTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		expDateTextField.setBackground(Color.WHITE);
		expDateTextField.setActionCommand("");
		expDateTextField.setBounds(0, 342, 284, 25);
		textFields.put("expDate", expDateTextField);
		cardInfoPanel.add(expDateTextField);
		
		JLabel lblExpirationDate = new JLabel("* Expiration Date");
		lblExpirationDate.setForeground(Color.BLACK);
		lblExpirationDate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblExpirationDate.setBackground(Color.BLACK);
		lblExpirationDate.setBounds(0, 305, 184, 25);
		cardInfoPanel.add(lblExpirationDate);
		
		JLabel lblCardNumber = new JLabel("* Card Number");
		lblCardNumber.setForeground(Color.BLACK);
		lblCardNumber.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCardNumber.setBackground(Color.BLACK);
		lblCardNumber.setBounds(0, 157, 154, 25);
		cardInfoPanel.add(lblCardNumber);
		
		cardNoTextField = new JTextField(20);
		cardNoTextField.setSelectedTextColor(Color.WHITE);
		cardNoTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cardNoTextField.setCaretColor(Color.BLACK);
		cardNoTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cardNoTextField.setBackground(Color.WHITE);
		cardNoTextField.setActionCommand("");
		cardNoTextField.setBounds(0, 194, 284, 25);
		textFields.put("cardNo", cardNoTextField);
		cardInfoPanel.add(cardNoTextField);
		
		cvvTextField = new JTextField(20);
		cvvTextField.setSelectedTextColor(Color.WHITE);
		cvvTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cvvTextField.setCaretColor(Color.BLACK);
		cvvTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cvvTextField.setBackground(Color.WHITE);
		cvvTextField.setActionCommand("");
		cvvTextField.setBounds(0, 268, 284, 25);
		textFields.put("cvv", cvvTextField);
		cardInfoPanel.add(cvvTextField);
		
		JLabel lblCvv = new JLabel("* CVV");
		lblCvv.setForeground(Color.BLACK);
		lblCvv.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblCvv.setBackground(Color.BLACK);
		lblCvv.setBounds(0, 231, 117, 25);
		cardInfoPanel.add(lblCvv);
		
		nameOnCardTextField = new JTextField(20);
		nameOnCardTextField.setSelectedTextColor(Color.WHITE);
		nameOnCardTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameOnCardTextField.setCaretColor(Color.BLACK);
		nameOnCardTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		nameOnCardTextField.setBackground(Color.WHITE);
		nameOnCardTextField.setActionCommand("");
		nameOnCardTextField.setBounds(0, 416, 284, 25);
		textFields.put("nameOnCard", nameOnCardTextField);
		cardInfoPanel.add(nameOnCardTextField);
		
		JLabel lblFullName = new JLabel("* Full Name");
		lblFullName.setBounds(0, 9, 117, 25);
		cardInfoPanel.add(lblFullName);
		lblFullName.setForeground(Color.BLACK);
		lblFullName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFullName.setBackground(Color.BLACK);
		
		fullNameTextField = new JTextField(20);
		fullNameTextField.setBounds(0, 46, 284, 25);
		cardInfoPanel.add(fullNameTextField);
		fullNameTextField.setSelectedTextColor(Color.WHITE);
		fullNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fullNameTextField.setCaretColor(Color.BLACK);
		fullNameTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		fullNameTextField.setBackground(Color.WHITE);
		textFields.put("name", fullNameTextField);
		fullNameTextField.setActionCommand("");
		
		JLabel emailLabel = new JLabel("* Email");
		emailLabel.setBounds(0, 83, 117, 25);
		cardInfoPanel.add(emailLabel);
		emailLabel.setForeground(Color.BLACK);
		emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		emailLabel.setBackground(Color.BLACK);
		
		emailTextField = new JTextField(20);
		emailTextField.setBounds(0, 120, 284, 25);
		cardInfoPanel.add(emailTextField);
		emailTextField.setSelectedTextColor(Color.WHITE);
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailTextField.setCaretColor(Color.BLACK);
		emailTextField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		emailTextField.setBackground(Color.WHITE);
		textFields.put("email", emailTextField);
		emailTextField.setActionCommand("");
		
		JLabel tickets = new JLabel("Your Ticket");
		tickets.setBounds(220, 198, 133, 25);
		whitePanel.add(tickets);
		tickets.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		ticketList = new JTextArea();
		ticketList.setEditable(false);
		ticketList.setBounds(104, 237, 372, 290);
		ticketList.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		whitePanel.add(ticketList);
		
	}
	
	public void addCheckoutListener(ActionListener listener)
	{
		btnCheckout.addActionListener(listener);
	}

	public HashMap<String, JButton> getButtons() {
		return buttons;
	}

	public HashMap<String, JTextField> getTextFields() {
		return textFields;
	}
	
	public void setTicketPurchased(String tickets) {
		ticketList.setText(tickets);
	}
	
	public void showPaymentPanel(boolean b) {
		cardInfoPanel.setVisible(b);
		lblMissingRequiredFields.setVisible(b);
		lblPleaseFillOut.setText("The card under your account will be automatically charged.");
	}
}
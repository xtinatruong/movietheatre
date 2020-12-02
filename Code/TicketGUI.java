import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;


public class TicketGUI extends JFrame {

	private JPanel blueContentPane;
	private HashMap<String, JTextField> textFields;
	private HashMap<String, JButton> buttons;
	
	private JTextArea purchasedTickets;
	
	private JButton btnReturnMenu = new JButton("Back to Movies");
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketGUI frame = new TicketGUI();
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
	public TicketGUI() {
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
		
		JLabel purchasedTicketsLabel = new JLabel("PURCHASED TICKETS");
		purchasedTicketsLabel.setForeground(new Color(100, 149, 237));
		purchasedTicketsLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		purchasedTicketsLabel.setBounds(104, 67, 553, 47);
		whitePanel.add(purchasedTicketsLabel);
		
		purchasedTickets = new JTextArea();
		purchasedTickets.setBounds(104, 154, 695, 353);
		purchasedTickets.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		purchasedTickets.setEditable(false);
		whitePanel.add(purchasedTickets);
		
		//PLEASE ADD EVENT LISTENER HERE SO THAT IT SUBMIT ALL TEXT FIELDS TO ACCOUNT DATABASE
		btnReturnMenu.setForeground(Color.WHITE);
		btnReturnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnReturnMenu.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
		btnReturnMenu.setBackground(new Color(100, 149, 237));
		btnReturnMenu.setBounds(304, 543, 288, 31);
		buttons.put("menu", btnReturnMenu);
		//btnSignUp.addActionListener(controller);
		whitePanel.add(btnReturnMenu);
		
		// cancel button
		cancelButton = new JButton("Cancel Ticket");
		cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cancelButton.setBorder(null);
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setBounds(392, 586, 117, 29);
		buttons.put("canel", cancelButton);
		whitePanel.add(cancelButton);
	}
	
	public void addReturnListener(ActionListener listener)
	{
		btnReturnMenu.addActionListener(listener);
	}
	
	public void addCancelListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}
	
	public void setTickets(String tickets) {
		purchasedTickets.setText(tickets);
	}


	public HashMap<String, JButton> getButtons() {
		return buttons;
	}

	public HashMap<String, JTextField> getTextFields() {
		return textFields;
	}
}

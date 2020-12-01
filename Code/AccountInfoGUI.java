import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;


public class AccountInfoGUI extends JFrame {

	private GUIController controller;
	private JPanel blueContentPane;
	private HashMap<String, JTextField> textFields;
	private HashMap<String, JButton> buttons;
	
	private JTextArea accountInfoText;
	
	private JButton btnReturnMenu = new JButton("Back to Movies");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountInfoGUI frame = new AccountInfoGUI();
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
	public AccountInfoGUI() {
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
		
		JLabel accountInfoLabel = new JLabel("ACCOUNT INFORMATION");
		accountInfoLabel.setForeground(new Color(100, 149, 237));
		accountInfoLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		accountInfoLabel.setBounds(104, 67, 553, 47);
		whitePanel.add(accountInfoLabel);
		
		//PLEASE ADD EVENT LISTENER HERE SO THAT IT SUBMIT ALL TEXT FIELDS TO ACCOUNT DATABASE
		btnReturnMenu.setForeground(Color.WHITE);
		btnReturnMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnReturnMenu.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
		btnReturnMenu.setBackground(new Color(100, 149, 237));
		btnReturnMenu.setBounds(304, 543, 288, 31);
		buttons.put("menu", btnReturnMenu);
		//btnSignUp.addActionListener(controller);
		whitePanel.add(btnReturnMenu);
		
		accountInfoText = new JTextArea();
		accountInfoText.setBounds(104, 154, 695, 353);
		accountInfoText.setEditable(false);
		whitePanel.add(accountInfoText);
	}
	
	public void addReturnListener(ActionListener listener)
	{
		btnReturnMenu.addActionListener(listener);
	}
	
	public void setInfo(String info) {
		accountInfoText.setText(info);
	}


	public HashMap<String, JButton> getButtons() {
		return buttons;
	}

	public HashMap<String, JTextField> getTextFields() {
		return textFields;
	}
}

import java.awt.Color; 
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class MenuGUI extends JFrame {

	private JPanel contentPane;
	
	private JTable movieTable;
	private JTable seatTable;
	private JLabel userNameLabel;
	private Choice theatreChoice;
	JButton btnCheckout = new JButton("Checkout");
	JButton loginButton = new JButton("Log In");
	JButton accountInfoButton = new JButton("Account");
	JButton btnPurchasedTickets = new JButton("Purchased Tickets");
	String userName = "Guest";
	private JTextField screenField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGUI frame = new MenuGUI();
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
	public MenuGUI() {
		// JFrame
		setSize(1139, 830);
		setTitle("Cinema 480");
		Image img = new ImageIcon(this.getClass().getResource("/film.png")).getImage();
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// content Pane
		JPanel ContentPane = new JPanel();
		ContentPane.setBackground(new Color(240, 248, 255));
		ContentPane.setLayout(null);
		setContentPane(ContentPane);

		JPanel bluePanel = new JPanel();
		bluePanel.setBackground(new Color(100, 149, 237));
		bluePanel.setBounds(10, 11, 310, 773);
		ContentPane.add(bluePanel);
		bluePanel.setLayout(null);

		JLabel welcomeLabel = new JLabel("WELCOME,");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 30));
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setBounds(41, 128, 228, 38);
		bluePanel.add(welcomeLabel);

		userNameLabel = new JLabel(userName);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground(Color.WHITE);
		userNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userNameLabel.setBounds(10, 168, 290, 27);
		bluePanel.add(userNameLabel);

		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBackground(new Color(100, 149, 237));
		loginPanel.setBounds(10, 11, 134, 27);
		bluePanel.add(loginPanel);

		JLabel regUserLabel = new JLabel("");
		regUserLabel.setHorizontalAlignment(SwingConstants.LEFT);
		regUserLabel.setForeground(new Color(255, 255, 255));
		regUserLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 10));
		regUserLabel.setBounds(0, -1, 76, 27);
		loginPanel.add(regUserLabel);

		loginButton.setHorizontalAlignment(SwingConstants.LEFT);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		loginButton.setBorder(null);
		loginButton.setBackground(new Color(100, 149, 237));
		loginButton.setBounds(81, 1, 53, 23);
		loginPanel.add(loginButton);

		// image icon for buttons
		Image ticketIcon = new ImageIcon(this.getClass().getResource("/shopping.png")).getImage(); // temp
		Image accountIcon = new ImageIcon(this.getClass().getResource("/account.png")).getImage();
		Image shoppingIcon = new ImageIcon(this.getClass().getResource("/shopping.png")).getImage();
		Image discountIcon = new ImageIcon(this.getClass().getResource("/discount.png")).getImage();
		Image seatMapIcon = new ImageIcon(this.getClass().getResource("/theatre.png")).getImage();

		JPanel accountButtonsPanel = new JPanel();
		accountButtonsPanel.setBackground(new Color(100, 149, 237));
		accountButtonsPanel.setBounds(21, 284, 268, 204);
		bluePanel.add(accountButtonsPanel);
		accountButtonsPanel.setLayout(null);

		accountInfoButton.setHorizontalAlignment(SwingConstants.LEFT);
		accountInfoButton.setBounds(28, 22, 212, 38);
		accountButtonsPanel.add(accountInfoButton);
		accountInfoButton.setForeground(Color.WHITE);
		accountInfoButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		accountInfoButton.setBorder(null);
		accountInfoButton.setBackground(new Color(100, 149, 237));
		accountInfoButton.setIcon(new ImageIcon(accountIcon));

		// purchased ticket button
		btnPurchasedTickets.setHorizontalAlignment(SwingConstants.LEFT);
		btnPurchasedTickets.setBounds(28, 82, 212, 38);
		accountButtonsPanel.add(btnPurchasedTickets);
		btnPurchasedTickets.setForeground(Color.WHITE);
		btnPurchasedTickets.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnPurchasedTickets.setBorder(null);
		btnPurchasedTickets.setBackground(new Color(100, 149, 237));
		btnPurchasedTickets.setIcon(new ImageIcon(discountIcon));
		
		// purchase discount button
		btnCheckout.setBounds(50, 671, 212, 38);
		bluePanel.add(btnCheckout);	
		btnCheckout.setHorizontalAlignment(SwingConstants.LEFT);
		btnCheckout.setForeground(Color.WHITE);
		btnCheckout.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnCheckout.setBorder(null);
		btnCheckout.setBackground(new Color(100, 149, 237));
		btnCheckout.setIcon(new ImageIcon(shoppingIcon));

		JPanel whitePanel = new JPanel();
		whitePanel.setBackground(new Color(255, 255, 255));
		whitePanel.setBounds(329, 11, 788, 773);
		ContentPane.add(whitePanel);
		whitePanel.setLayout(null);

		movieTable = new JTable();
		movieTable.setBackground(new Color(245, 245, 245));
		movieTable.setBounds(59, 130, 669, 221);
		movieTable.setRowSelectionAllowed(true);
		whitePanel.add(movieTable);

		theatreChoice = new Choice();
		theatreChoice.setBackground(new Color(245, 245, 245));
		theatreChoice.setBounds(280, 46, 227, 30);
		whitePanel.add(theatreChoice);

		JLabel chooseTheatreLabel = new JLabel("Choose Theatre");
		chooseTheatreLabel.setForeground(new Color(100, 149, 237));
		chooseTheatreLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		chooseTheatreLabel.setBounds(59, 36, 153, 27);
		whitePanel.add(chooseTheatreLabel);

		JLabel chooseMovieLabel = new JLabel("Choose Movie");
		chooseMovieLabel.setForeground(new Color(100, 149, 237));
		chooseMovieLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		chooseMovieLabel.setBounds(59, 92, 153, 27);
		whitePanel.add(chooseMovieLabel);

		JLabel chooseSeatsLabel = new JLabel("Choose Seats");
		chooseSeatsLabel.setForeground(new Color(100, 149, 237));
		chooseSeatsLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		chooseSeatsLabel.setBounds(59, 391, 153, 27);
		whitePanel.add(chooseSeatsLabel);
		
		screenField = new JTextField();
		screenField.setHorizontalAlignment(SwingConstants.CENTER);
		screenField.setForeground(Color.WHITE);
		screenField.setText("SCREEN");
		screenField.setEditable(false);
		screenField.setBackground(Color.BLACK);
		screenField.setBounds(215, 419, 350, 27);
		whitePanel.add(screenField);
		screenField.setColumns(10);
		
		seatTable = new JTable();
		seatTable.setBackground(new Color(245, 245, 245));
		seatTable.setBounds(59, 469, 669, 275);
//		seatTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 
//		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
//		seatTable.setDefaultRenderer(String.class, centerRenderer);
		seatTable.setCellSelectionEnabled(true);
		
		whitePanel.add(seatTable);
	}

	public void addCheckoutListener(ActionListener checkoutListener)
	{
		btnCheckout.addActionListener(checkoutListener);
	}

	public void addLoginListener(ActionListener loginListener)
	{
		loginButton.addActionListener(loginListener);
	}

	public void addInfoListener(ActionListener infoListener)
	{
		accountInfoButton.addActionListener(infoListener);
	}

	public void addPurchasedListener(ActionListener purchaseListener)
	{
		btnPurchasedTickets.addActionListener(purchaseListener);
	}
	
	public void addTheatreListener(ItemListener theatreListener) {
		theatreChoice.addItemListener(theatreListener);
	}
	
	public void addMovieListener(ListSelectionListener movieListener) {
		movieTable.getSelectionModel().addListSelectionListener(movieListener);
	}
	
	public void addSeatListener(ListSelectionListener seatListener) {
		seatTable.getSelectionModel().addListSelectionListener(seatListener);
	}
	
	

	public void setName(String name) {
		userName = name;
		userNameLabel.setText(userName);
	}
	
	public void addTheatre(String t) {
		theatreChoice.add(t);
	}
	
	public String getTheatre() {
		return theatreChoice.getSelectedItem();
	}
	
	public void setMovieTable(DefaultTableModel mTable) {
		movieTable.setModel(mTable);
	}
	
	public String getMovie() {
		try {
			String movie = movieTable.getValueAt(movieTable.getSelectedRow(), 0).toString();
			return movie;
		}
		catch(Exception e) {
			movieTable.clearSelection();
			return null;
		}
		
	}
	
	public void setSeatTable(DefaultTableModel sTable) {
		seatTable.setModel(sTable);
	}
	
	public String getSeat() {
		try {
			int row = seatTable.getSelectedRow();
			int col = seatTable.getSelectedColumn();
			String seat = seatTable.getValueAt(row, col).toString();
			return seat;
		} catch (Exception e) {
			return "No seats selected.";
		}
	}
	
	public void showMessage(String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Cinema 480", JOptionPane.INFORMATION_MESSAGE);
    }
}

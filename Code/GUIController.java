import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.event.*;

import javax.swing.table.DefaultTableModel;

class GUIController{
    private AccountSystem model;
    private LoginGUI loginGUI;
    private SignUpGUI signupGUI;
    private MenuGUI menuGUI;
    private AccountInfoGUI aGUI;
    private TransactionGUI tGUI;
    private FinancialInstitute fi;

    private RegisteredUser user;
    private Theatre theatre; 
    private Movie movie;
    private Seat seat;

    public GUIController(AccountSystem as, LoginGUI gui, SignUpGUI sgui, MenuGUI mgui, AccountInfoGUI agui, TransactionGUI tgui) {
    	fi = new FinancialInstitute("CIBC");
    	
        this.model = as;
        this.loginGUI = gui;
        this.signupGUI = sgui;
        this.menuGUI = mgui;
        this.aGUI = agui;
        this.tGUI = tgui;
        
        getTheatres();
        
        menuGUI.setVisible(true);
        loginGUI.setVisible(false);
        signupGUI.setVisible(false);
        aGUI.setVisible(false);
        tGUI.setVisible(false);
        
        signupGUI.addSignUpListener((ActionEvent event) -> {
			signup();
        });
        signupGUI.addLoginListener((ActionEvent event) -> {
            signupGUI.setVisible(false);
            loginGUI.setVisible(true);
        });
        
        loginGUI.addLoginListener((ActionEvent event) -> {
			login();
        });
        loginGUI.addSignUpListener((ActionEvent event) -> {
            loginGUI.setVisible(false);
			signupGUI.setVisible(true);
        });
        
        menuGUI.addTheatreListener((ItemEvent event) -> {
        	for(Theatre t : AccountSystem.getTheatres()) {
        		if(t.getName().compareTo(menuGUI.getTheatre()) == 0)
        			theatre = t;
        	}
        	getMovies(theatre.getID());
        });
        
        menuGUI.addMovieListener((ListSelectionEvent event) -> {
        	for(Movie m : AccountSystem.getMovies(theatre.getID())) {
        		if(m.getName().compareTo(menuGUI.getMovie()) == 0)
        			movie = m;
        	}
        	getSeats(movie.getId());
        });
        
        menuGUI.addInfoListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
			getAccountInfo();
			aGUI.setVisible(true);
        });
        menuGUI.addLoginListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
            loginGUI.setVisible(true);
		});
        menuGUI.addCheckoutListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
			tGUI.setTicketPurchased(menuGUI.getSeat());
			if(user != null)
        		tGUI.showPaymentPanel(false);
        	else
        		tGUI.showPaymentPanel(true);
			tGUI.setVisible(true);
			
        });
        menuGUI.addPurchasedListener((ActionEvent event) -> {
			/* todo */
        });
        
        
        aGUI.addReturnListener((ActionEvent event) -> {
        	aGUI.setVisible(false);
        	menuGUI.setVisible(true);
        });
        
        tGUI.addCheckoutListener((ActionEvent event) -> {
        	tGUI.setVisible(false);
        	menuGUI.setVisible(true);
        	checkoutVerification();
        });
    }

    /**
     * verify login information inputted with AccountSystem
     */
    public void login() {
        String id = AccountSystem.login(loginGUI.getTextFields().get("email").getText(), 
                    loginGUI.getTextFields().get("password").getText());

        user = AccountSystem.getUserInfo(id);
        menuGUI.setName(user.getName());
        if(id == null) {}
            //loginGUI.displayError();
        else {
            loginGUI.setVisible(false);
            menuGUI.setVisible(true);
        }
        
    }

    /**
     * send inputted account info to AccountSystem
     * @return 
     */
    public void signup() {
        // public static boolean signup(String name, String email, String password, String city, int cardNo, int CVV, String expDate, String nameOnCard)
        String name = signupGUI.getTextFields().get("name").getText();
        String email = signupGUI.getTextFields().get("email").getText();
        String password = signupGUI.getTextFields().get("password").getText();
        String city = signupGUI.getTextFields().get("city").getText();
        int cardNo = Integer.parseInt(signupGUI.getTextFields().get("cardNo").getText());
        int CVV = Integer.parseInt(signupGUI.getTextFields().get("cvv").getText());
        String expDate = signupGUI.getTextFields().get("expDate").getText();
        String nameOnCard = signupGUI.getTextFields().get("nameOnCard").getText();

        if(model.signup(name, email, password, city, cardNo, CVV, expDate, nameOnCard)) {
            signupGUI.setVisible(false);
            loginGUI.setVisible(true);
        }
        else {
            //signupGUI.displayError();
        }

    }
    
    public void getAccountInfo() {
    	String info = "You are not signed in!";
    	if(user != null)
    	{
    		info = user.toString();
    	}
    	aGUI.setInfo(info);
    }
    
    public void checkoutVerification() {
    	try {
	    	if(user != null) {
	    		fi.verfiyPayementMethod(Integer.parseInt(user.getCardNo()), user.getCvv(), user.getExpDate(), user.getNameOnCard());
	    		menuGUI.showMessage("Purchase Confirmed!");
	    	}
	    	else {
	    		int cardNo = Integer.parseInt(tGUI.getTextFields().get("cardNo").getText());
	    		int cvv = Integer.parseInt(tGUI.getTextFields().get("cvv").getText());
	    		String expD = tGUI.getTextFields().get("expDate").getText();
	    		String noc = tGUI.getTextFields().get("nameOnCard").getText();
	    		
	    		fi.verfiyPayementMethod(cardNo, cvv, expD, noc);
	    		menuGUI.showMessage("Purchase Confirmed!");
	    	} 
	    	
	    	model.reserveTicket()
	    	
    		
    	} 
    	catch (Exception e) {
    		menuGUI.showMessage("Your card was declined. Transaction failed.");
    	}
    }

    public void getTheatres() {
    	menuGUI.addTheatre("");
        for(Theatre t : AccountSystem.getTheatres()) {
        	menuGUI.addTheatre(t.getName());
        }
    }

    public void getMovies(String theatreId) {
    	DefaultTableModel mTable = new DefaultTableModel() 
    	{
    	    public boolean isCellEditable(int row, int column)
    	    {
    	      return false;
    	    }
    	};
    	mTable.addColumn("Title");
    	mTable.addColumn("Time");
    	mTable.addColumn("Price");
    	for(Movie m : AccountSystem.getMovies(theatreId))
    		mTable.insertRow(0, new Object[] {m.getName(), m.getTime(), m.getPrice()});
    	
       	menuGUI.setMovieTable(mTable);
    }
    
    public void getSeats(String movieId) {
    	DefaultTableModel sTable = new DefaultTableModel()
    	{
    	    public boolean isCellEditable(int row, int column)
    	    {
    	      return false;
    	    }
    	};
    	HashMap<Character, ArrayList<String>> seats = new HashMap<>();
    	for(Seat s : AccountSystem.getSeat(movieId)) {
    		if(seats.get(s.getNumber().charAt(0)) == null)
    			seats.put(s.getNumber().charAt(0), new ArrayList<String>());
    		if(s.getAvailability())
    			seats.get(s.getNumber().charAt(0)).add(s.getNumber());
    		else
    			seats.get(s.getNumber().charAt(0)).add("");
    	}
    	
    	int max = 0;
    	for(HashMap.Entry<Character, ArrayList<String>> a : seats.entrySet()) {
    		if(max < a.getValue().size())
    			max = a.getValue().size();
    	}
    	
    	for(int i = 0; i < max; i++) {
    		sTable.addColumn(i);
    	}
    	
    	char i = 'A';
    	while(seats.get(i) != null) {
    		sTable.addRow(seats.get(i).toArray());
    		i++;
    	}
    	
    	menuGUI.setSeatTable(sTable);
    	
    }
    
    

}
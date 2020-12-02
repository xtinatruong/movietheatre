import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.swing.event.*;

import javax.swing.table.DefaultTableModel;

class GUIController{
    private AccountSystem model;
    private LoginGUI loginGUI;
    private SignUpGUI signupGUI;
    private MenuGUI menuGUI;
    private AccountInfoGUI aGUI;
    private TransactionGUI tGUI;
    private TicketGUI tickGUI;
    private CancellationGUI cancelGUI;
    private FinancialInstitute fi;

    private RegisteredUser user;
    private Theatre theatre; 
    private Movie movie;
    private Seat seat;
    Ticket ticket;

    public GUIController(AccountSystem as, LoginGUI gui, SignUpGUI sgui, MenuGUI mgui, AccountInfoGUI agui, TransactionGUI tgui, TicketGUI tickgui, CancellationGUI cgui) {
    	fi = new FinancialInstitute("CIBC");
    	
        this.model = as;
        this.loginGUI = gui;
        this.signupGUI = sgui;
        this.menuGUI = mgui;
        this.aGUI = agui;
        this.tGUI = tgui;
        this.tickGUI = tickgui;
        this.cancelGUI = cgui;
        
        getTheatres();
        
        menuGUI.setVisible(true);
        loginGUI.setVisible(false);
        signupGUI.setVisible(false);
        aGUI.setVisible(false);
        tGUI.setVisible(false);
        tickGUI.setVisible(false);
        cancelGUI.setVisible(false);
        
        signupGUI.addSignUpListener((ActionEvent event) -> {
			signup();
        });
        signupGUI.addLoginListener((ActionEvent event) -> {
            signupGUI.setVisible(false);
            loginGUI.reset();
            loginGUI.setVisible(true);
        });
        
        loginGUI.addLoginListener((ActionEvent event) -> {
			login();
        });
        loginGUI.addSignUpListener((ActionEvent event) -> {
            loginGUI.setVisible(false);
			signupGUI.setVisible(true);
        });
        loginGUI.addGuestListener((ActionEvent event) -> {
        	loginGUI.setVisible(false);
        	menuGUI.setVisible(true);
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
        
        menuGUI.addSeatListener((ListSelectionEvent event) -> {
        	seat = new Seat();
        	seat.setNumber(menuGUI.getSeat());
        	seat.setMovieId(movie.getId());
        });
        
        menuGUI.addInfoListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
			getAccountInfo();
			aGUI.setVisible(true);
        });
        menuGUI.addLoginListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
			loginGUI.reset();
            loginGUI.setVisible(true);
		});
        menuGUI.addCheckoutListener((ActionEvent event) -> {
        	try {
				menuGUI.setVisible(false);
				
				displayCheckoutInfo(movie.getPrice());
				
				if(user != null)
	        		tGUI.showPaymentPanel(false);
	        	else
	        		tGUI.showPaymentPanel(true);
				tGUI.setVisible(true);
        	}
        	catch (Exception e) {
        		menuGUI.setVisible(true);
        		menuGUI.showMessage("No seat selected.");
        	}
			
        });
        menuGUI.addPurchasedListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
			getTickets();
			tickGUI.setVisible(true);
        });
        	
        
        aGUI.addReturnListener((ActionEvent event) -> {
        	aGUI.setVisible(false);
        	menuGUI.setVisible(true);
        });
        
        // transaction gui action events
        tGUI.addCheckoutListener((ActionEvent event) -> {
        	tGUI.setVisible(false);
        	menuGUI.setVisible(true);
        	checkoutVerification();
        });
        tGUI.addApplyListener((ActionEvent event) -> {
        	Voucher v = model.getVoucher(tGUI.getCoupon());
        	double newPrice = movie.getPrice() - v.getValue();
        	if(newPrice < 0)
        		newPrice = 0;
        	movie.setPrice(newPrice);
        	model.removeVoucher(v.getId());
        	
        	displayCheckoutInfo(movie.getPrice());
        	
        });
        
        // ticket gui action events
        tickGUI.addReturnListener((ActionEvent event) -> {
        	tickGUI.setVisible(false);
        	menuGUI.setVisible(true);
        });
        tickGUI.addCancelListener((ActionEvent event) -> {
        	tickGUI.setVisible(false);
        	
        	if(user != null)
        		cancelGUI.showNonRegisteredPanel(false);
        	cancelGUI.reset();
        	cancelGUI.setVisible(true);
        });
        
        
        // cancel gui action events
        cancelGUI.addSearchListener((ActionEvent event) -> {
        	ticket = model.getTicket(cancelGUI.getTicketId());
        	movie = model.getMovie(ticket.getShowId());
        	if(ticket != null)
        		cancelGUI.setTicketFound("Ticket found!\n" + ticket.toString());
        	else
        		cancelGUI.setTicketFound("Ticket not found!\n");
        	
        });
        cancelGUI.addCancelListener((ActionEvent event) -> {
        	cancelGUI.setVisible(false);
        	
        	cancelTicket();
        	
        	menuGUI.setVisible(true);
        	menuGUI.showMessage("Ticket successfully cancelled");
        });
        cancelGUI.addReturnListener((ActionEvent event) -> {
        	cancelGUI.setVisible(false);
        	menuGUI.setVisible(true);
        });
        
        
        
    }

    /**
     * verify login information inputted with AccountSystem
     */
    public void login() {
    	try {
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
    	catch (Exception e) {
    		menuGUI.showMessage("Incorrect email or password.");
    	}  
    }

    /**
     * send inputted account info to AccountSystem
     * @return 
     */
    public void signup() {
    	try {
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
    	catch (Exception e) {
    		menuGUI.showMessage("Not all fields are filled.");
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
	    		fi.verfiyPayementMethod(user.getCardNo(), user.getCvv(), user.getExpDate(), user.getNameOnCard());
	    		fi.chargeUser(user.getCardNo(), user.getCvv(), user.getExpDate(), user.getNameOnCard(), movie.getPrice());
	    		model.reserveTicket(user.getId(), seat.getMovieId(), seat.getNumber(), movie.getTime(), movie.getName());
	    		
	    		menuGUI.showMessage("Purchase Confirmed!");
	    	}
	    	else {
	    		String cardNo =tGUI.getTextFields().get("cardNo").getText();
	    		int cvv = Integer.parseInt(tGUI.getTextFields().get("cvv").getText());
	    		String expD = tGUI.getTextFields().get("expDate").getText();
	    		String noc = tGUI.getTextFields().get("nameOnCard").getText();
	    		fi.verfiyPayementMethod(cardNo, cvv, expD, noc);
	    		fi.chargeUser(cardNo, cvv, expD, noc, movie.getPrice());
	    		model.reserveTicket("GUEST", seat.getMovieId(), seat.getNumber(), movie.getTime(), movie.getName());
	    		
	    		menuGUI.showMessage("Purchase Confirmed!");
	    	} 
    	} 
    	catch (Exception e) {
    		menuGUI.showMessage("Your card was declined. Transaction failed.");
    	}
    }
    
    public void displayCheckoutInfo(double price) {
    	DecimalFormat df = new DecimalFormat();
    	df.setMaximumFractionDigits(2);
    	
    	String res = "Movie: " + movie.getName();
		res += "\nShowtime: " + movie.getTime();
		res += "\nSeat: " + seat.getNumber();
		res += "\nPrice: $" + df.format(price);
		tGUI.setTicketPurchased(res);
    }
    
    public void cancelTicket() {
    	Movie m = model.getMovie(ticket.getShowId());
    	
    	Date movDate = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[0]) - 1, Integer.parseInt(date[1]));
		Date currDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		long diffInMillies = Math.abs(movDate.getTime() - currDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
    	model.cancelTicket(ticket.getUserId(), ticket.getShowId(), ticket.getSeatNum());
    	Voucher v;
    	if(user != null) {
    		v = model.createVoucher(movie.getPrice());
    		v.printReceipt(user.getEmail(), user.getName());
    	}
    	else {
    		v = model.createVoucher(movie.getPrice()*0.85);
    		v.printReceipt(cancelGUI.getEmail(), cancelGUI.getName());
    	}
    	
    }
    
    public void getTickets() {
    	String ticks= "You are not signed in.";
    	if(user != null) {
    		ticks = "";
    		for(Ticket t : model.getTickets(user.getId())) {
    			ticks += t.toString();
    		}
    	}
    	tickGUI.setTickets(ticks);
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
    	for(Movie m : AccountSystem.getMovies(theatreId)) {
    		String [] date = m.getTime().split("/| |-");
    		
    		Date movDate = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[0]) - 1, Integer.parseInt(date[1]));
    		Date currDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    		long diffInMillies = Math.abs(movDate.getTime() - currDate.getTime());
    	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    	    
    		if(diff >= 20) {
    			if(user == null) 
    				continue;
    			
    		double totalSeats = 0;
    		double unavailSeats = 0;
    		for(Seat s : model.getSeat(m.getId())) {
    			totalSeats++;
    			if(s.getAvailability() == false)
    				unavailSeats++;
    		}
    		
    		if(unavailSeats/totalSeats >= 0.10)
    			continue;
    		}
    			
    		mTable.insertRow(0, new Object[] {m.getName(), m.getTime(), m.getPrice()});
    				
    	}
    	
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
    	for(Seat s : model.getSeat(movieId)) {
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
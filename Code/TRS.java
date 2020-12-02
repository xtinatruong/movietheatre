import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.table.DefaultTableModel;

public class TRS {

	private AccountSystem model;
	
    private LoginGUI loginGUI;
    private SignUpGUI signupGUI;
    private MenuGUI menuGUI;
    private AccountInfoGUI aGUI;
    private TransactionGUI tGUI;
    private TicketGUI tickGUI;
    private CancellationGUI cancelGUI;
	
	private FinancialInstitute fi;
    
    public TRS(AccountSystem model, LoginGUI gui, SignUpGUI sgui, MenuGUI mgui, 
			AccountInfoGUI agui, TransactionGUI tgui, TicketGUI tickgui, 
			CancellationGUI cgui) {
    	this.model = model;
        this.loginGUI = gui;
        this.signupGUI = sgui;
        this.menuGUI = mgui;
        this.aGUI = agui;
        this.tGUI = tgui;
        this.tickGUI = tickgui;
        this.cancelGUI = cgui;
        
        this.fi = new FinancialInstitute("CIBC", model);
    }
    
    /**
     * verify login information inputted with AccountSystem
     */
    public RegisteredUser login() {
    	RegisteredUser user;
    	try {
    		String id = model.login(loginGUI.getTextFields().get("email").getText(), 
                    loginGUI.getTextFields().get("password").getText());

    		user = model.getUserInfo(id);
    		menuGUI.setName(user.getName());
    		if(id == null) {}
            //loginGUI.displayError();
    		else {
    			loginGUI.setVisible(false);
    			menuGUI.setVisible(true);
    		}
    		return user;
    	}
    	catch (Exception e) {
    		menuGUI.showMessage("Incorrect email or password.");
    		return null;
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
	
    public void getAccountInfo(User user) {
    	String info = "You are not signed in!";
    	if(user != null)
    	{
    		info = user.toString();
    	}
    	aGUI.setInfo(info);
    }
    
    public void checkoutVerification(RegisteredUser user, Seat seat, Movie movie) {
    	try {
	    	if(user != null) {
	    		fi.verfiyPayementMethod(user.getCardNo(), user.getCvv(), user.getExpDate(), user.getNameOnCard());
	    		fi.chargeUser(user.getCardNo(), user.getCvv(), user.getExpDate(), user.getNameOnCard(), movie.getPrice());
	    		Ticket t = model.reserveTicket(user.getId(), seat.getMovieId(), seat.getNumber(), movie.getTime(), movie.getName());
	    		
	    		t.printReceipt(user.getEmail(), user.getName());
	    		menuGUI.showMessage("Purchase Confirmed!");
	    	}
	    	else {
	    		String cardNo =tGUI.getTextFields().get("cardNo").getText();
	    		int cvv = Integer.parseInt(tGUI.getTextFields().get("cvv").getText());
	    		String expD = tGUI.getTextFields().get("expDate").getText();
	    		String noc = tGUI.getTextFields().get("nameOnCard").getText();
	    		fi.verfiyPayementMethod(cardNo, cvv, expD, noc);
	    		fi.chargeUser(cardNo, cvv, expD, noc, movie.getPrice());
	    		Ticket t = model.reserveTicket("GUEST", seat.getMovieId(), seat.getNumber(), movie.getTime(), movie.getName());
	    		
	    		t.printReceipt(tGUI.getTextFields().get("name").getText(), tGUI.getTextFields().get("email").getText());
	    		menuGUI.showMessage("Purchase Confirmed!");
	    	} 
	    	
    	} 
    	catch (Exception e) {
    		menuGUI.showMessage("Your card was declined. Transaction failed.");
    	}
    }
    
    public void displayCheckoutInfo(Movie movie, Seat seat) {
    	DecimalFormat df = new DecimalFormat();
    	df.setMaximumFractionDigits(2);
    	
    	String res = "Movie: " + movie.getName();
		res += "\nShowtime: " + movie.getTime();
		res += "\nSeat: " + seat.getNumber();
		res += "\nPrice: $" + df.format(movie.getPrice());
		tGUI.setTicketPurchased(res);
    }
    
    public boolean cancelTicket(Ticket ticket, RegisteredUser user, Movie movie) {
    	Movie m = model.getMovie(ticket.getShowId());
    	String [] date = m.getTime().split("/| |-");
    	Date movDate = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[0]) - 1, Integer.parseInt(date[1]));
		Date currDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		long diffInMillies = Math.abs(movDate.getTime() - currDate.getTime());
	    long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    if(diff < 72)
	    	return false;
	    
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
    	return true;
    	
    }
    
    public void getTickets(RegisteredUser user, Movie movie) {
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
        for(Theatre t : model.getTheatres()) {
        	menuGUI.addTheatre(t.getName());
        }
    }

    public void getMovies(String theatreId, RegisteredUser user) {
    	DefaultTableModel mTable = new DefaultTableModel() 
    	{
    	    public boolean isCellEditable(int row, int column)
    	    {
    	      return false;
    	    }
    	};
    	
    	if(theatreId.equals("")) {
    		menuGUI.setMovieTable(mTable);
    		mTable.getDataVector().removeAllElements();
    		mTable.fireTableDataChanged();
    		return;
    	}
    	
    	mTable.addColumn("Title");
    	mTable.addColumn("Time");
    	mTable.addColumn("Price");
    	for(Movie m : model.getMovies(theatreId)) {
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

    		if(unavailSeats/totalSeats >= 0.1)
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

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

    private TRS trs;

    public GUIController(AccountSystem as, LoginGUI gui, SignUpGUI sgui, MenuGUI mgui, 
    					AccountInfoGUI agui, TransactionGUI tgui, TicketGUI tickgui, 
    					CancellationGUI cgui) {
    	fi = new FinancialInstitute("CIBC", model);
    	
        this.model = as;
        this.loginGUI = gui;
        this.signupGUI = sgui;
        this.menuGUI = mgui;
        this.aGUI = agui;
        this.tGUI = tgui;
        this.tickGUI = tickgui;
        this.cancelGUI = cgui;
        
        trs = new TRS(as, gui, sgui, mgui, agui, tgui, tickgui, cgui);
        
        trs.getTheatres();
        
        menuGUI.setVisible(true);
        loginGUI.setVisible(false);
        signupGUI.setVisible(false);
        aGUI.setVisible(false);
        tGUI.setVisible(false);
        tickGUI.setVisible(false);
        cancelGUI.setVisible(false);
        
        // signup gui action events
        signupGUI.addSignUpListener((ActionEvent event) -> {
        	trs.signup();
        });
        signupGUI.addLoginListener((ActionEvent event) -> {
            signupGUI.setVisible(false);
            loginGUI.reset();
            loginGUI.setVisible(true);
        });
        
        // login gui action events
        loginGUI.addLoginListener((ActionEvent event) -> {
        	user = trs.login();
        });
        loginGUI.addSignUpListener((ActionEvent event) -> {
            loginGUI.setVisible(false);
			signupGUI.setVisible(true);
        });
        loginGUI.addGuestListener((ActionEvent event) -> {
        	loginGUI.setVisible(false);
        	menuGUI.reset();
        	menuGUI.setVisible(true);
        });
        
        // menu gui action events
        menuGUI.addTheatreListener((ItemEvent event) -> {
        	for(Theatre t : model.getTheatres()) {
        		if(t.getName().compareTo(menuGUI.getTheatre()) == 0)
        			theatre = t;
        	}
        	trs.getMovies(theatre.getID(), user);
        });
        
        menuGUI.addMovieListener((ListSelectionEvent event) -> {
        	try {
        		for(Movie m : model.getMovies(theatre.getID())) {
	        		if(m.getName().compareTo(menuGUI.getMovie()) == 0)
	        			movie = m;
	        	}
	        	trs.getSeats(movie.getId());
        	}
        	catch(Exception e) {
        		menuGUI.reset();
        		menuGUI.setVisible(true);
        	}
        	
        });
        
        menuGUI.addSeatListener((ListSelectionEvent event) -> {
        	seat = new Seat();
        	seat.setNumber(menuGUI.getSeat());
        	seat.setMovieId(movie.getId());
        });
        
        menuGUI.addInfoListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
			trs.getAccountInfo(user);
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
				
				trs.displayCheckoutInfo(movie, seat);
				
				if(user != null)
	        		tGUI.showPaymentPanel(false);
	        	else
	        		tGUI.showPaymentPanel(true);
				tGUI.setVisible(true);
        	}
        	catch (Exception e) {
        		menuGUI.reset();
        		menuGUI.setVisible(true);
        		menuGUI.showMessage("No seat selected.");
        	}
			
        });
        menuGUI.addPurchasedListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
			trs.getTickets(user, movie);
			tickGUI.setVisible(true);
        });
        	
        
        // account gui action events
        aGUI.addReturnListener((ActionEvent event) -> {
        	aGUI.setVisible(false);
        	menuGUI.reset();
        	menuGUI.setVisible(true);
        });
        
        // transaction gui action events
        tGUI.addCheckoutListener((ActionEvent event) -> {
        	tGUI.setVisible(false);
        	menuGUI.reset();
        	menuGUI.setVisible(true);
        	trs.checkoutVerification(user, seat, movie);
        });
        tGUI.addApplyListener((ActionEvent event) -> {
        	Voucher v = model.getVoucher(tGUI.getCoupon());
        	double newPrice = movie.getPrice() - v.getValue();
        	if(newPrice < 0)
        		newPrice = 0;
        	movie.setPrice(newPrice);
        	model.removeVoucher(v.getId());
        	
        	trs.displayCheckoutInfo(movie, seat);
        	
        });
        
        // ticket gui action events
        tickGUI.addReturnListener((ActionEvent event) -> {
        	tickGUI.setVisible(false);
        	menuGUI.reset();
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
        	menuGUI.reset();
        	menuGUI.setVisible(true);
        	
        	if(trs.cancelTicket(ticket, user, movie))
        		menuGUI.showMessage("Ticket successfully cancelled");
        	else
        		menuGUI.showMessage("This movie is within 72 hours. Ticket cannot be cancelled.");	
        });
        cancelGUI.addReturnListener((ActionEvent event) -> {
        	cancelGUI.setVisible(false);
        	menuGUI.reset();
        	menuGUI.setVisible(true);
        });
        
        
        
    }


    

    
    

}
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

class GUIController{
    private AccountSystem model;
    private LoginGUI loginGUI;
    private SignUpGUI signupGUI;
    private MenuGUI menuGUI;

    private User user;
    private Theatre theatre; 

    public GUIController(AccountSystem as, LoginGUI gui, SignUpGUI sgui, MenuGUI mgui) {
    	
        this.model = as;
        this.loginGUI = gui;
        this.signupGUI = sgui;
        this.menuGUI = mgui;
        
        getTheatres();
        
        menuGUI.setVisible(true);
        loginGUI.setVisible(false);
        signupGUI.setVisible(false);
        
        
        
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
        menuGUI.addInfoListener((ActionEvent event) -> {
			/* todo */
        });
        menuGUI.addLoginListener((ActionEvent event) -> {
			menuGUI.setVisible(false);
            loginGUI.setVisible(true);
		});
        menuGUI.addVoucherListener((ActionEvent event) -> {
			/* todo */
        });
        menuGUI.addPurchaseListener((ActionEvent event) -> {
			/* todo */
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

    public void getTheatres() {
    	menuGUI.addTheatre("");
        for(Theatre t : AccountSystem.getTheatres()) {
        	menuGUI.addTheatre(t.getName());
        }
    }

    public void getMovies(String theatreId) {
    	DefaultTableModel mTable = new DefaultTableModel();
    	mTable.addColumn("Title");
    	mTable.addColumn("Time");
    	mTable.addColumn("Price");
    	for(Movie m : AccountSystem.getMovies(theatreId))
    		mTable.insertRow(0, new Object[] {m.getName(), m.getTime(), m.getPrice()});
    	
       	menuGUI.setMovieTable(mTable);
    }
    
    

}
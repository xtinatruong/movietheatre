import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

class GUIController{
    private AccountSystem model;
    private LoginGUI loginGUI;
    private SignUpGUI signupGUI;
    private MenuGUI menuGUI;

    private User user;

    public GUIController(AccountSystem as, LoginGUI gui, SignUpGUI sgui, MenuGUI mgui) {
        this.model = as;
        this.loginGUI = gui;
        this.signupGUI = sgui;
        this.menuGUI = mgui;
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
        System.out.println(user.getName());
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

    public String getUserName() {
    	//if(user != null)
    		//return user.getName();
    	
    	return "Guest";
    }
    
    // public void selectTheatre() {
    //     ArrayList<Theatre> theatres = AccountSystem.getTheatres();
    //     int i = 0;
    //     String list = "";
    //     for (Theatre t : theatres) {
    //         list += "Selection: " + i + "\nTheatre Name: " + t.getName() +
    //                 "\nCity: " + t.getCity();
    //         list += "\n*****************************\n";
    //         menuGUI.sendTheatres(list);
    //         i++;
    //     }
    // }

    public ArrayList<Theatre>  getTheatres() {
        return AccountSystem.getTheatres();
    }

    public ArrayList<Movie> getMovies(String theatreId) {
        return AccountSystem.getMovies(theatreId);
    }

    /**
     * defines button functionality and switches GUI pages as needed
     */
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        // login page functionality 
//        if(e.getSource() == loginGUI.getButtons().get("login")) {
//            login();
//        }
//        else if (e.getSource() == loginGUI.getButtons().get("signup")) {
//        	signupGUI = new SignUpGUI();
//        	System.out.println("button pressed");
//        }
//
//        // signup page functionality 
//        else if(e.getSource() == signupGUI.getButtons().get("signup"))
//        {
//        	System.out.println("Please work");
//            signup();
//        }
//        else if (e.getSource() == signupGUI.getButtons().get("login")) {
//            signupGUI.setVisible(false);
//            loginGUI.setVisible(true);
//        }
//
////        else if(e.getSource() == menGUI.getButtons().get("login")) {
////            menuGUI.setVisible(true);
////            loginGUI.setVisible(true);
////        }
////
////        // main page functionality
////        else if(e.getSource() == menuGUI.getButtons().get("selectTheatre")) {
////            int tChoice = menuGUI.getTable().get("theatre").getSelectedRow();
////            int mChoice = menuGUI.getTable().get("movie").getSelectedRow();
////        }
//    }

}
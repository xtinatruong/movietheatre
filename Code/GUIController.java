import java.awt.event.*;
import java.util.HashMap;

class GUIController implements ActionListener{
    private AccountSystem model;
    private AccountGUI accountGUI;
    private SignUpGUI signupGUI;
    private MenuGUI menuGUI;

    public GUIController(AccountSystem as, AccountGUI gui) {
        this.model = as;
        this.accountGUI = gui;
        accountGUI.setVisible(true);
    }

    /**
     * verify login information inputted with AccountSystem
     */
    public void login() {
        HashMap<String,String> dbInfo = model.login(accountGUI.getTextFields().get("email").getText(), 
                    accountGUI.getTextFields().get("password").getText());
        if(dbInfo == null) 
            accountGUI.displayError();
        else {
            accountGUI.setVisible(false);
            menuGUI.setVisible(true);
        }
        
    }

    /**
     * send inputted account info to AccountSystem
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
            accountGUI.setVisible(true);
        }
        else {
            signupGUI.displayError());
        }
    }

    /**
     * defines button functionality and switches GUI pages as needed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // login page functionality 
        if(e.getSource() == accountGUI.getButtons().get("login")) {
            login();
        }
        else if (e.getSource() == accountGUI.getButtons().get("signup")) {
            accountGUI.setVisible(false);
            signupGUI.setVisible(true);
        }

        // signup page functionality 
        else if(e.getSource() == signupGUI.getButtons().get("signup"))
            signup();
        else if (e.getSource() == signupGUI.getButtons().get("login")) {
            signupGUI.setVisible(false);
            accountGUI.setVisible(true);
        }


    }
}
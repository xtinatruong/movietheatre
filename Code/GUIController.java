import java.awt.event.*;
import java.util.HashMap;

class ManageAccount implements ActionListener{
    private AccountSystem model;
    private AccountGUI accountGUI;
    private SignUpGUI signupGUI;
    private MenuGUI menuGUI;

    public ManageAccount(AccountSystem as, AccountGUI gui) {
        this.model = as;
        this.accountGUI = gui;
        accountGUI.setVisible(true);
    }

    public void login() {
        HashMap<String,String> dbInfo = model.login(accountGUI.getTextFields().get("email"), accountGUI.getTextFields().get("password"));
        if(dbInfo == null) 
            accountGUI.displayIncorrectLogin();
        else {
            accountGUI.setVisible(false);
            menuGUI.setVisible(true);
        }
        
    }

    public void signup() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == accountGUI.getButtons().get("login")) {
            login();
        }
        else if (e.getSource() == accountGUI.getButtons().get("signup")) {
            accountGUI.setVisible(false);
            signupGUI.setVisible(true);
        }


    }
}
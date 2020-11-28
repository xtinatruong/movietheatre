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

    public void login() {
        HashMap<String,String> dbInfo = model.login(accountGUI.getTextFields().get("email").getText(), 
                    accountGUI.getTextFields().get("password").getText());
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
        //else if(e.getSource == )


    }
}
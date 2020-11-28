import java.awt.event.*;

class ManageAccount implements ActionListener{
    private AccountSystem model;
    private AccountGUI view;

    private 
    public ManageAccount(AccountSystem as, AccountGUI gui) {
        this.model = as;
        this.view = gui;

        view.addLoginListener((ActionEvent event) -> {
            login();
        });

        model.initializeConnection();
    }

    public void login() {
        model.login(view.getEmail(), view.getPassword());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
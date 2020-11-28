
public class RunMovieTheatre {
    public static void main(String[] args) {
        AccountSystem db = new AccountSystem();
        AccountGUI login = new AccountGUI();
        SignUpGUI signup = new SignUpGUI();
        ManageAccount controller = new ManageAccount(db, login);
        
        login.setMA(controller);
        signup.setMA(controller);
    }
}
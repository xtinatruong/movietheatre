
public class RunMovieTheatre {
    public static void main(String[] args) {
        AccountSystem db = new AccountSystem();
        LoginGUI login = new LoginGUI();
        SignUpGUI signup = new SignUpGUI();
        GUIController controller = new GUIController(db, login);
        
        login.setController(controller);
        signup.setController(controller);
    }
}
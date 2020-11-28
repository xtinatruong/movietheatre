
public class RunMovieTheatre {
    public static void main(String[] args) {
        AccountSystem db = new AccountSystem();
        AccountGUI login = new AccountGUI();
        ManageAccount accountController = new ManageAccount(db, login);
        login.setMA(accountController);
    }
}
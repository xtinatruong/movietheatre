import java.awt.EventQueue;

public class RunMovieTheatre {
    public static void main(String[] args) {
    	
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountSystem db = new AccountSystem();
			        LoginGUI login = new LoginGUI();
					SignUpGUI signup = new SignUpGUI();
					MenuGUI menu = new MenuGUI();
					AccountInfoGUI account = new AccountInfoGUI();
					GUIController controller = new GUIController(db, login, signup, menu, account);
			        
			        signup.setController(controller);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
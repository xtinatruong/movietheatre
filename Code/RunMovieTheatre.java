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
					TransactionGUI trans = new TransactionGUI();
					TicketGUI ticket = new TicketGUI();
					CancellationGUI cancel = new CancellationGUI();
					GUIController controller = new GUIController(db, login, signup, menu, account, trans, ticket, cancel);
			        
			        signup.setController(controller);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AccountSystem implements Database {
    private static Connection conn;

    public AccountSystem() {
        initializeConnection();
    }

    /**
     * Initialize the connection to server
     */
    public static void initializeConnection() {
        try {
            // Register JDBC driver
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Problem");
            e.printStackTrace();
        }
    }

    /**
     * Close the connection to server
     */
    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Problem");
            e.printStackTrace();
        }
    }

    /**
     * Return a hashmap contains the user's id
     */
    public static String login(String email, String password) {
        String sql = "select id from User where email=? and password=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
            	String result = rs.getString("id");
            	pstmt.close();
            	return result;
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

     /**
     * Return true if the account is successfully signed up
     */
    public static boolean signup(String name, String email, String password, String city, int cardNo, int CVV, String expDate, String nameOnCard) {
        try {
            String sql = "insert into User(id, name, email, password, city, cardNo, CVV, expDate, nameOnCard, paymentDeadline) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, city);
            pstmt.setInt(6, cardNo);
            pstmt.setInt(7, CVV);
            pstmt.setString(8, expDate);
            pstmt.setString(9, nameOnCard);
            LocalDate t = LocalDate.now();
            pstmt.setString(10, (new Date(t.getYear() + 1, t.getMonthValue(), t.getDayOfMonth())).toString());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Return true if the user info is successfully updated
     */
    public static boolean updateAccountInfo(String id, String name, String email, String password, String city, int cardNo, int CVV, String expDate, String nameOnCard) {
        String sql = "update User set name=?, email=?, password=?, city=?, cardNo=?, CVV=?, expDate=?, nameOnCard=? where id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, city);
            pstmt.setInt(5, cardNo);
            pstmt.setInt(6, CVV);
            pstmt.setString(7, expDate);
            pstmt.setString(8, nameOnCard);
            pstmt.setString(9, id);
            pstmt.executeQuery();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return true if successfully reserve ticket
     */
    public static boolean reserveTicket(String userId, String showId, String seatNumber, String showtime, String movie) {
        try {
            //Change seat availability
            String sql = "update Seat set availability=FALSE where showId=? and seatNumber=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, showId);
            pstmt.setString(2, seatNumber);
            pstmt.executeUpdate();
            //Add ticket to database
            String sql2 = "insert into Ticket (id, showId, movie, time, seatNumber, userId) values(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, showId);
            pstmt.setString(3, movie);
            pstmt.setString(4, showtime);
            pstmt.setString(5, seatNumber);
            pstmt.setString(6, userId);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return true if successfully cancel ticket
     */
    public static boolean cancelTicket(String userId, String showId, String seatNumber) {
        try {
            //Change seat availability
            String sql = "update Seat set availability=TRUE where showId=? and seatNumber=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, showId);
            pstmt.setString(2, seatNumber);
            pstmt.executeUpdate();
            //Add ticket to database
            String sql2 = "delete from Ticket where showId=? and userId=? and seatNumber=?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, showId);
            pstmt.setString(2, userId);
            pstmt.setString(3, seatNumber);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return true if the paymentDeadline is extended
     */
    public static boolean payAnnualFee(String userId, String newDeadline) {
        try {
            String sql = "update User set paymentDeadline=? where userId=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newDeadline);
            pstmt.setString(2, userId);
            pstmt.executeQuery();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return true if the voucher is valid
     */
    public static boolean isValid(String voucherId) {
        try {
            String sql = "select * from Voucher where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, voucherId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return false;
            }
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * return true if voucher validity is successfully changed
     */
    public static boolean changeValidity(String voucherId) {
        try {
            String sql = "delete from Voucher where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, voucherId);
            pstmt.executeQuery();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * return Voucher if voucher is created for the user
     */
    public static Voucher createVoucher(double value) {
        try {
            String sql = "insert into Voucher (id,value) value(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            String id =  UUID.randomUUID().toString();
            pstmt.setString(1,id);
            pstmt.setDouble(2, value);
            pstmt.executeUpdate();
            pstmt.close();
            
            return new Voucher(id,value);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * return an ArrayList of Theatre objects with the field:
     * {id, name, city}
     */
    public static ArrayList<Theatre> getTheatres() {
        try {
            String sql = "select * from Theatre";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Theatre> theatres= new ArrayList<Theatre>();
            while (rs.next()) {
                Theatre t = new Theatre();
                t.setName(rs.getString("name"));
                t.setID(rs.getString("id"));
                t.setCity(rs.getString("city"));
                theatres.add(t);
            }
            pstmt.close();
            return theatres;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * return all movie at selected theatre
     * return a Arraylist that contains HashMap with the fields:
     * {id, name, theatreId, time}
     */
    public static ArrayList<Movie> getMovies(String theatreId) {
        try {
            String sql = "select * from Movie where theatreId=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, theatreId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Movie> movies = new ArrayList<Movie>();
            while (rs.next()) {
                Movie m = new Movie();
                m.setName(rs.getString("name"));
                m.setId(rs.getString("id"));
                m.setTheatreId(rs.getString("theatreId"));
                m.setTime(rs.getString("time"));
                m.setPrice(rs.getDouble("price"));

                movies.add(m);
            }
            pstmt.close();
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /** 
     * return Movie object from showId
     * @param showId
     * @return 
     */
    public static Movie getMovie (String showId) {
        try {
            String sql = "select * from Movie where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, showId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	String id = rs.getString("id");
            	String name = rs.getString("name");
            	String theatreId = rs.getString("theatreId");
            	String time = rs.getString("time");
            	double price = rs.getDouble("price");
            	
            	Movie m = new Movie(id, name, time, theatreId, price);
            	pstmt.close();
            	return m;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * return all seat of selected show
     * return a Arraylist that contains Seats with the fields:
     * {showId, number, availability}
     */
    public static ArrayList<Seat> getSeat(String showId) {
        try {
            String sql = "select * from Seat where showId=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, showId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Seat> seats = new ArrayList<>();
            while (rs.next()) {
                String number = rs.getString("seatNumber");
                boolean available = rs.getBoolean("availability");
                Seat s = new Seat(showId, number, available);
                seats.add(s);
            }
            pstmt.close();
            return seats;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Ticket> getTickets(String userId) {
    	try {
    		String sql = "select * from Ticket where userId=?";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, userId);
    		ResultSet rs = pstmt.executeQuery();
    		ArrayList<Ticket> tickets = new ArrayList<>();
    		while(rs.next()) {
    			String id = rs.getString("id");
    			String showId = rs.getString("showId");
    			String number = rs.getString("seatNumber");
    			String showtime = rs.getString("time");
    			String movie = rs.getString("movie");
    			Ticket t = new Ticket (id, userId, showId, number, showtime, movie);
    			tickets.add(t);
    		}
    		pstmt.close();
    		return tickets;
    	} catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /** 
     * return Ticket object from ticketId
     * @param showId
     * @return 
     */
    public static Ticket getTicket (String ticketId) {
        try {
            String sql = "select * from Ticket where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ticketId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	String movie = rs.getString("movie");
            	String showId = rs.getString("showId");
            	String time = rs.getString("time");
            	String seat = rs.getString("seatNumber");
            	String uid = rs.getString("userId");
            	
            	Ticket t = new Ticket(ticketId, uid, showId, seat, time, movie);
            	pstmt.close();
            	return t;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * return all user info
     * return a RegisteredUser object with the fields:
     * {showId, number, availability}
     */
    public static RegisteredUser getUserInfo(String userId) {
        try {
            String sql = "select * from User where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String city = rs.getString("city");
                String cardNo = rs.getString("cardNo");
                int cvv = rs.getInt("CVV");
                String expDate = rs.getString("expDate");
                String nameOnCard = rs.getString("nameOnCard");  

                RegisteredUser ru = new RegisteredUser(name,city, userId, email, password, cardNo, cvv, expDate, nameOnCard);
                return ru;
            }
            pstmt.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * return the balance if payment method is verified
     */
    public static double verifyPayment(int cardNo, int CVV, String expDate, String nameOnCard) {
        try {
            String sql = "select * from Card where cardNo=? and CVV=? and expDate=? and nameOnCard=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cardNo);
            pstmt.setInt(2, CVV);
            pstmt.setString(3, expDate);
            pstmt.setString(4, nameOnCard);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return -1;
            }
            return rs.getDouble("balance");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * return true if payment is verified
     */
    public static boolean transaction(int cardNo, int CVV, String expDate, String nameOnCard, double price) {
        try {
            double balance = verifyPayment(cardNo, CVV, expDate, nameOnCard);
            if (balance < 0 || (balance + price) < 0) {
                return false;
            } else {
                balance += price;
                String sql2 = "update Card set balance=? where cardNo=? and CVV=? and expDate=? and nameOnCard=?";
                PreparedStatement pstmt = conn.prepareStatement(sql2);
                pstmt.setDouble(1, balance);
                pstmt.setInt(2, cardNo);
                pstmt.setInt(3, CVV);
                pstmt.setString(4, expDate);
                pstmt.setString(5, nameOnCard);
                pstmt.executeQuery();
                pstmt.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * return all user's voucher id in an arrayList
     */
    public static Voucher getVoucher(String id) {
        try {
            String sql = "select id from Voucher where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            ArrayList <Voucher> vouchers = new ArrayList<Voucher>();
            if (rs.next()) {
                Voucher v = new Voucher(id, rs.getDouble("value"));
                vouchers.add(v);
                pstmt.close();
                return v;
            }
            pstmt.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

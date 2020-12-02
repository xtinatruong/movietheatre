import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class PopulateDatabase implements Database {
    public Connection conn;
    private String user1;
    private String user2;
    private String theatre1;
    private String theatre2;
    private String show1;
    private String show2;
    private String show3;
    private String show4;

    public PopulateDatabase() {
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

    public void populateUser() {
        try {
            String sql = "drop table if exists User";
            PreparedStatement pstmt = conn.prepareStatement(sql); // construct a statement
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql2 = "create table User (id VARCHAR(255), name VARCHAR(255), city VARCHAR(255), email VARCHAR(255), password VARCHAR(255), cardNo INTEGER, CVV INTEGER, expDate VARCHAR(255), nameOnCard VARCHAR(255), paymentDeadline VARCHAR(255), PRIMARY KEY (id))";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql3 = "insert into User(id, name, email, password, city, cardNo, CVV, expDate, nameOnCard, paymentDeadline) values(?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql3);
            user1 = UUID.randomUUID().toString();
            pstmt.setString(1, user1);
            pstmt.setString(2, "user1");
            pstmt.setString(3, "user1@gmail.com");
            pstmt.setString(4, "12345678");
            pstmt.setString(5, "Calgary");
            pstmt.setInt(6, 12345678);
            pstmt.setInt(7, 111);
            pstmt.setString(8, "07/20");
            pstmt.setString(9, "User 1");
            pstmt.setString(10, "02/25"); // wrong year
            pstmt.executeUpdate(); // execute my query (i.e. sql)

            String sql4 = "insert into User(id, name, email, password, city, cardNo, CVV, expDate, nameOnCard, paymentDeadline) values(?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql4);
            user2 = UUID.randomUUID().toString();
            pstmt.setString(1, user2);
            pstmt.setString(2, "user2");
            pstmt.setString(3, "user2@gmail.com");
            pstmt.setString(4, "12345678");
            pstmt.setString(5, "Calgary");
            pstmt.setInt(6, 87654321);
            pstmt.setInt(7, 222);
            pstmt.setString(8, "05/21");
            pstmt.setString(9, "User 2");
            pstmt.setString(10, "09/23");
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
    }

    public void populateTheatre() {
        try {
            String sql = "drop table if exists Theatre";
            PreparedStatement pstmt = conn.prepareStatement(sql); // construct a statement
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql2 = "create table Theatre (id VARCHAR(255), name VARCHAR(255), city VARCHAR(255), PRIMARY KEY (id))";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql3 = "insert into Theatre(id, name, city) values(?,?,?)";
            pstmt = conn.prepareStatement(sql3);
            theatre1 = UUID.randomUUID().toString();
            pstmt.setString(1, theatre1);
            pstmt.setString(2, "Chinook");
            pstmt.setString(3, "Calgary");
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
    }

    public void populateMovie() {
        try {
            String sql = "drop table if exists Movie";
            PreparedStatement pstmt = conn.prepareStatement(sql); // construct a statement
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql2 = "create table Movie (id VARCHAR(255), name VARCHAR(255), time VARCHAR(255), theatreId VARCHAR(255), price INTEGER ,PRIMARY KEY (id))";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql3 = "insert into Movie(id, name, time, theatreId, price) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql3);
            show1 = UUID.randomUUID().toString();
            pstmt.setString(1, show1);
            pstmt.setString(2, "The Wind Rises");
            pstmt.setString(3, "12/10/2020 - 16:00");
            pstmt.setString(4, theatre1);
            pstmt.setInt(5, 20);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql4 = "insert into Movie(id, name, time, theatreId, price) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql4);
            show2 = UUID.randomUUID().toString();
            pstmt.setString(1, show2);
            pstmt.setString(2, "Papillon");
            pstmt.setString(3,"12/30/2020 - 20:00");
            pstmt.setString(4, theatre1);
            pstmt.setInt(5, 25);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql5 = "insert into Movie(id, name, time, theatreId, price) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql5);
            show3 = UUID.randomUUID().toString();
            pstmt.setString(1, show3);
            pstmt.setString(2, "The New Mutants");
            pstmt.setString(3,"12/15/2020 - 14:00");
            pstmt.setString(4, theatre1);
            pstmt.setInt(5, 25);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql6 = "insert into Movie(id, name, time, theatreId, price) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql6);
            show4 = UUID.randomUUID().toString();
            pstmt.setString(1, show4);
            pstmt.setString(2, "Croods 2");
            pstmt.setString(3,"12/29/2020 - 17:00");
            pstmt.setString(4, theatre1);
            pstmt.setInt(5, 20);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
    }

    public void populateSeat() {
        try {
            String sql = "drop table if exists Seat";
            PreparedStatement pstmt = conn.prepareStatement(sql); // construct a statement
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql2 = "create table Seat (showId VARCHAR(255), seatNumber VARCHAR(255), availability BOOLEAN)";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            for (int i = 1; i <= 10; i++) {
                for (char y = 'A'; y <= 'D'; y++) {
                    String sql3 = "insert into Seat(showId, seatNumber, availability) values(?,?,?)";
                    String seatNumber = y + Integer.toString(i);
                    pstmt = conn.prepareStatement(sql3);
                    pstmt.setString(1, show1);
                    pstmt.setString(2, seatNumber);
                    double random = Math.random();
                    if(random < 0.2)
                    	pstmt.setBoolean(3, false);
                    else
                    	pstmt.setBoolean(3, true);
                    pstmt.executeUpdate(); // execute my query (i.e. sql)
                }
                
            }
            for (int i = 1; i <= 5; i++) {
                for (char y = 'A'; y <= 'E'; y++) {
                    String sql3 = "insert into Seat(showId, seatNumber, availability) values(?,?,?)";
                    String seatNumber = y + Integer.toString(i);
                    pstmt = conn.prepareStatement(sql3);
                    pstmt.setString(1, show2);
                    pstmt.setString(2, seatNumber);
                    double random = Math.random();
                    if(random < 0.1)
                    	pstmt.setBoolean(3, false);
                    else
                    	pstmt.setBoolean(3, true);
                    pstmt.executeUpdate(); // execute my query (i.e. sql)
                }
            }
            for (int i = 1; i <= 5; i++) {
                for (char y = 'A'; y <= 'E'; y++) {
                    String sql3 = "insert into Seat(showId, seatNumber, availability) values(?,?,?)";
                    String seatNumber = y + Integer.toString(i);
                    pstmt = conn.prepareStatement(sql3);
                    pstmt.setString(1, show3);
                    pstmt.setString(2, seatNumber);
                    double random = Math.random();
                    if(random < 0.2)
                    	pstmt.setBoolean(3, false);
                    else
                    	pstmt.setBoolean(3, true);
                    pstmt.executeUpdate(); // execute my query (i.e. sql)
                }
            }
            for (int i = 1; i <= 10; i++) {
                for (char y = 'A'; y <= 'D'; y++) {
                    String sql3 = "insert into Seat(showId, seatNumber, availability) values(?,?,?)";
                    String seatNumber = y + Integer.toString(i);
                    pstmt = conn.prepareStatement(sql3);
                    pstmt.setString(1, show4);
                    pstmt.setString(2, seatNumber);
                    double random = Math.random();
                    if(random < 0.1)
                    	pstmt.setBoolean(3, false);
                    else
                    	pstmt.setBoolean(3, true);
                    pstmt.executeUpdate(); // execute my query (i.e. sql)
                }
            }
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
    }

    public void createTicket() {
        try {
            String sql = "drop table if exists Ticket";
            PreparedStatement pstmt = conn.prepareStatement(sql); // construct a statement
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql2 = "create table Ticket (id VARCHAR(255), showId VARCHAR(255), movie VARCHAR(255), time VARCHAR(255), seatNumber VARCHAR(255), userId VARCHAR(255))";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
    }

    public void createVoucher() {
        try {
            String sql = "drop table if exists Voucher";
            PreparedStatement pstmt = conn.prepareStatement(sql); // construct a statement
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql2 = "create table Voucher (id VARCHAR(255), value DOUBLE)";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
    }

    public void createCard() {
        try {
            String sql = "drop table if exists Card";
            PreparedStatement pstmt = conn.prepareStatement(sql); // construct a statement
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql2 = "create table Card (cardNo INTEGER, CVV INTEGER, expDate VARCHAR(255), nameOnCard VARCHAR(255), balance INTEGER)";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)

            String sql3 = "insert into Card(cardNo, CVV, expDate, nameOnCard, balance) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, 12345678);
            pstmt.setInt(2, 111);
            pstmt.setString(3, "07/20");
            pstmt.setString(4, "User 1");
            pstmt.setInt(5, 100);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Table can NOT be created!");
        }
    }

    public static void main(String[] args) throws SQLException {
        PopulateDatabase p=new PopulateDatabase();
        p.populateUser();
        p.populateTheatre();
        p.populateMovie();
        p.populateSeat();
        p.createTicket();
        p.createVoucher();
        p.createCard();
        p.conn.close();
    }
}

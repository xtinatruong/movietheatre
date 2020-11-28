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
            pstmt.setString(8, (new Date(2023, 5, 5)).toString());
            pstmt.setString(9, "User 1");
            pstmt.setString(10, (new Date(2021, 5, 1)).toString());
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
            pstmt.setString(8, (new Date(2023, 6, 6)).toString());
            pstmt.setString(9, "User 2");
            pstmt.setString(10, (new Date(2022, 6, 1)).toString());
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
            pstmt.setString(2, "user1");
            pstmt.setString(3, "user1@gmail.com");
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql4 = "insert into Theatre(id, name, city) values(?,?,?)";
            pstmt = conn.prepareStatement(sql4);
            theatre2 = UUID.randomUUID().toString();
            pstmt.setString(1, theatre2);
            pstmt.setString(2, "user1");
            pstmt.setString(3, "user1@gmail.com");
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
            String sql2 = "create table Movie (id VARCHAR(255), name VARCHAR(255), time VARCHAR(255), theatreId VARCHAR(255), PRIMARY KEY (id))";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql3 = "insert into Movie(id, name, time, theatreId) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql3);
            show1 = UUID.randomUUID().toString();
            pstmt.setString(1, show1);
            pstmt.setString(2, "film1");
            pstmt.setString(3, LocalDateTime.now().toString());
            pstmt.setString(4, theatre1);
            pstmt.executeUpdate(); // execute my query (i.e. sql)
            String sql4 = "insert into Movie(id, name, time, theatreId) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql4);
            show2 = UUID.randomUUID().toString();
            pstmt.setString(1, show2);
            pstmt.setString(2, "film2");
            pstmt.setString(3, LocalDateTime.now().toString());
            pstmt.setString(4, theatre2);
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
            String sql2 = "create table Ticket (showId VARCHAR(255), seatNumber VARCHAR(255), userId VARCHAR(255))";
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
            String sql2 = "create table Voucher (id VARCHAR(255), userId VARCHAR(255))";
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
            String sql2 = "create table Card (cardNo INTEGER, CVV INTEGER, expDate VARCHAR(255), nameOnCard VARCHAR(255))";
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate(); // execute my query (i.e. sql)

            String sql3 = "insert into Card(cardNo, CVV, expDate, nameOnCard) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, 12345678);
            pstmt.setInt(2, 111);
            pstmt.setString(3, (new Date(2023, 5, 5)).toString());
            pstmt.setString(4, "User 1");
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

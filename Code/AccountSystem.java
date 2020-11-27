import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AccountSystem implements Database {
    private static Connection conn;

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
    public static HashMap login(String email, String password) {
        String sql = "select id from User where email=? and password=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            HashMap result = new HashMap();
            result.put("id", rs.getString("id"));
            pstmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashMap();
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
    public static boolean reserveTicket(String userId, String showId, String seatNumber) {
        try {
            //Change seat availability
            String sql = "update Seat set availability=FALSE where showId=? and seatNumber=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, showId);
            pstmt.setString(2, seatNumber);
            pstmt.executeQuery();
            //Add ticket to database
            String sql2 = "insert into Ticket (showId,userId,seatNumber) values(?,?,?)";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, showId);
            pstmt.setString(2, userId);
            pstmt.setString(3, seatNumber);
            pstmt.executeQuery();
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
            pstmt.executeQuery();
            //Add ticket to database
            String sql2 = "delete from Ticket where showId=? and userId=? and seatNumber=?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, showId);
            pstmt.setString(2, userId);
            pstmt.setString(3, seatNumber);
            pstmt.executeQuery();
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
            String sql = "select COUNT(*) as c from Voucher where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, voucherId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.getInt('c') == 0) {
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
     * return a Arraylist that contains HashMap with the field:
     * {id, name, city}
     */
    public static ArrayList getTheatre() {
        try {
            String sql = "select * from Theatre";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList res = new ArrayList();
            while (rs.next()) {
                HashMap row = new HashMap();
                row.put("name", rs.getString("name"));
                row.put("id", rs.getString("id"));
                row.put("city", rs.getString("city"));
                res.add(row);
            }
            pstmt.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    /**
     * return all movie at selected theatre
     * return a Arraylist that contains HashMap with the fields:
     * {id, name, theatreId, time}
     */
    public static ArrayList getMovie(String theatreId) {
        try {
            String sql = "select * from Movie where theatreId=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, theatreId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList res = new ArrayList();
            while (rs.next()) {
                HashMap row = new HashMap();
                row.put("name", rs.getString("name"));
                row.put("id", rs.getString("id"));
                row.put("theatreId", rs.getString("theatreId"));
                row.put("time", rs.getString("time"));
                res.add(row);
            }
            pstmt.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    /**
     * return all seat of selected show
     * return a Arraylist that contains HashMap with the fields:
     * {showId, number, availability}
     */
    public static ArrayList getSeat(String showId) {
        try {
            String sql = "select * from Movie where showId=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, showId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList res = new ArrayList();
            while (rs.next()) {
                HashMap row = new HashMap();
                row.put("showId", rs.getString("showId"));
                row.put("number", rs.getString("number"));
                row.put("availability", rs.getBoolean("availability"));
                res.add(row);
            }
            pstmt.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    /**
     * return all user info
     * return a Hashmap with the fields:
     * {showId, number, availability}
     */
    public static HashMap getUserInfo(String userId) {
        try {
            String sql = "select * from User where id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            HashMap res = new HashMap();
            if (rs.next()) {
                return res;
            }
            res.put("name", rs.getString("name"));
            res.put("email", rs.getString("email"));
            res.put("password", rs.getString("password"));
            res.put("city", rs.getString("city"));
            res.put("cardNo", rs.getInt("cardNo"));
            res.put("CVV", rs.getInt("CVV"));
            res.put("expDate", rs.getString("expDate"));
            res.put("nameOnCard", rs.getString("nameOnCard"));
            pstmt.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap();
        }
    }

    /**
     * return true if payment is verified
     */
    public static boolean verifyPayment(int cardNo, int CVV, String expDate, String nameOnCard) {
        try {
            String sql = "select * from Card where cardNo=? and CVV=? and expDate=? and nameOnCard=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cardNo);
            pstmt.setInt(2, CVV);
            pstmt.setString(3, expDate);
            pstmt.setString(4, nameOnCard);
            pstmt.executeQuery();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * return true if voucher is created for the user
     */
    public static boolean createVoucher(String userId) {
        try {
            String sql = "insert into Voucher (id,userId) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, UUID.randomUUID().toString());
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
     * return all user's voucher id in an arrayList
     */
    public static ArrayList getVoucher(String userId) {
        try {
            String sql = "select id from Voucher where userId=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList res = new ArrayList();
            while (rs.next()) {
                res.add(rs.getString("id"));
            }
            pstmt.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }
}

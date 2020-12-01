import java.time.LocalDateTime;

class Ticket{
	
	private LocalDateTime showtime;
	private String seat;
	
	//I think we should include theatre here because users need to know which theatre
	private String userId;
	private String showId;
	private String seatNum;
	
	public Ticket(String uid, String sid, String num) {
		userId =uid;
		showId = sid;
		seatNum = num;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	
	public String toString() {
		String ticket = "";
		ticket += "\nMovie: " + showId;
		ticket += "\nShowtime: "; // ADD SHOWTIME !!! DB TABLE
		ticket += "\nSeat: " + seatNum;
		ticket += "\n";
		return ticket;
	}
	
}
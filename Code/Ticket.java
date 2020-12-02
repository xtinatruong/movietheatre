import java.time.LocalDateTime;

class Ticket{
	
	//I think we should include theatre here because users need to know which theatre
	private String id;
	private String userId;
	private String showId;
	private String seatNum;
	private String showtime;
	private String movie;
	
	public Ticket(String id, String uid, String sid, String num, String st, String mov) {
		this.id = id;
		userId =uid;
		showId = sid;
		seatNum = num;
		setShowtime(st);
		setMovie(mov);
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
		Movie m = AccountSystem.getMovie(showId);
		String ticket = "";
		ticket += "\nMovie: " + movie;
		ticket += "\nShowtime: " + showtime;
		ticket += "\nSeat: " + seatNum;
		ticket += "\nPrice: $" + m.getPrice();
		ticket += "\nTicket ID: " + id;
		ticket += "\n";
		return ticket;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}
	
}
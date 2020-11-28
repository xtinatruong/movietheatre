import java.time.LocalDateTime;

class Ticket{
	
	private LocalDateTime showtime;
	private String seat;
	
	//I think we should include theatre here because users need to know which theatre
	private Theatre theatre;
	private String show;
	
	
	/**
	 * generate ticket and insert into database
	 */
	public Ticket generateTicket() {
		//verify seat availability
		//confirm transaction
		//mark seat as booked
		//Insert into Ticket(showtime, seat) database
		//return string or json ticket object
		return this;
	}
	
	public LocalDateTime getShowtime() {
		return showtime;
	}
	public void setShowtime(LocalDateTime showtime) {
		this.showtime = showtime;
	}
	
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}

	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	
}
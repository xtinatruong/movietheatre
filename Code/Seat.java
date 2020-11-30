class Seat {
	private String movieId;
	private String number;
	private boolean availability;
	
	public Seat(String movieId, String number, boolean availability) {
		this.movieId = movieId;
		this.number = number;
		this.availability = availability;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
}
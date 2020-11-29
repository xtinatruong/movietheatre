import java.util.HashMap;
import java.util.Vector;

class Theatre{
	private String city;
	private String name;
	private int id;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
	
	/**
	 * Reserves a ticket and mark it as occupied in the seat map
	 * Storing the date purchased
	 * @param ticket
	 */
	public void reserveTicket(Ticket ticket) {
		//insert into database??
		
	}
	
}
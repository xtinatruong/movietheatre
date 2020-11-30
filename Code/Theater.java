import java.util.HashMap;
import java.util.Vector;

class Theatre{
	private String city;
	private String name;
	private String id;
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

	public String getID() {
		return id;
	}
	public void setID(String id) {
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
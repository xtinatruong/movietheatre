import java.util.HashMap;
import java.util.Vector;

class Theatre{
	private String location;
	private String name;
	private HashMap<String, Vector<String>> Map;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, Vector<String>> getMap() {
		return Map;
	}
	public void setMap(HashMap<String, Vector<String>> map) {
		Map = map;
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
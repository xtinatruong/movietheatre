import java.time.LocalTime;

public class Movie {
    private String name;
    private String id;
    private String theatreId;
    private String time;
    private double price;

    public Movie () {
    	name= "";
    	id = "";
    	theatreId = "";
    	time = "";
    	price = -1;
    }
    
    public Movie(String id, String name, String time, String theatreId, double price) {
    	this.id = id;
    	this.name = name;
    	this.time = time;
    	this.theatreId = theatreId;
    	this.price = price;
    }
    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

    
}

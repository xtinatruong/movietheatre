public class User {
    protected String name;
    protected String city;
    protected String id;

    public User(String name, String city, String id) {
        this.name = name;
        this.city = city;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void reserveTicket()
    {
        /*
            Unimplemented
        */
    }

    public void cancelTicket()
    {
        /*
            Unimplemented
        */
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    
    public String toString()
    {
    	String res = "";
    	res += "Name: " + name;
    	res += "\nCity: " + city;
    	return res;
    }
}
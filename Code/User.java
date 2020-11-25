public class User {
    private String name;
    private String city;

    public User(String name, String city)
    {
        this.name = name;
        this.city = city;
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
    public String setCity(String city)
    {
        this.city = city;
    }
}
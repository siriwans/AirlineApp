package DAO;

public class Airline {
    private int Id;
    private String Airline;
    private String Abbreviation;
    private String Country;

    public Airline(int id, String airline, String abbrev, String country)
    {
        Id = id;
        Airline = airline;
        Abbreviation = abbrev;
        Country = country;
    }
}

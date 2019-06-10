package DAO;

public class Airport {
    private String City;
    private String AirportCode;
    private String AirportName;
    private String Country;
    private String CountryAbbrev;

    public Airport(String city, String code, String name, String country, String countAbbrev)
    {
        City = city;
        AirportCode = code;
        AirportName = name;
        Country = country;
        CountryAbbrev = countAbbrev;
    }
}

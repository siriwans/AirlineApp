package DAO;

public class Flight {

    private int Airline;
    private int FlightNo;
    private String SourceAirport;
    private String DestAirport;

    public int getAirline() {
        return Airline;
    }

    public int getFlightNo() {
        return FlightNo;
    }

    public String getSourceAirport() {
        return SourceAirport;
    }

    public String getDestAirport() {
        return DestAirport;
    }

    public Flight(int airline, int flightNo, String source, String dest)
    {
        Airline = airline;
        FlightNo = flightNo;
        SourceAirport = source;
        DestAirport = dest;
    }
}

package DAO;

public class FlightInfo {
    private int FlightNo;
    private int Airline;
    private int PlaneId;
    private String Arrival;
    private String Departure;

    public FlightInfo(int flightNo, int airline, int planeId, String arrive, String dept)
    {
        FlightNo = flightNo;
        Airline = airline;
        PlaneId = planeId;
        Arrival = arrive;
        Departure = dept;
    }
}

package DAO;

public class FlightInfo {
    private int FlightNo;
    private int Airline;
    private int PlaneId;
    private String Arrival;

    public int getFlightNo() {
        return FlightNo;
    }

    public int getAirline() {
        return Airline;
    }

    public int getPlaneId() {
        return PlaneId;
    }

    public String getArrival() {
        return Arrival;
    }

    public String getDeparture() {
        return Departure;
    }

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

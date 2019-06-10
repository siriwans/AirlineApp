package DAO;

public class Booking {
    private int ReservationNo;
    private int Customer;
    private int CardNo;
    private int FlightNo;
    private int SeatNo;
    private String Cancelled;

    public Booking(int reserveNo, int customerId, int cardNo, int flightNo, int seatNo, String cancel)
    {
        ReservationNo = reserveNo;
        Customer = customerId;
        CardNo = cardNo;
        FlightNo = flightNo;
        SeatNo = seatNo;
    }

}

package DAO;

public class Seating {
    private int PlaneId;
    private int SeatNo;
    private String Class;
    private int Customer;
    private String SeatType;
    private double Price;


    public Seating(int planeId, int seatNo, String cls, int customer, String seatType, double price)
    {
        PlaneId = planeId;
        SeatNo = seatNo;
        Customer = customer;
        SeatType = seatType;
        Price = price;
    }
}

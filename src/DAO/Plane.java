package DAO;

public class Plane {
    private int Id;
    private int Capacity;
    private int Count;
    private String isFull;

    public Plane(int id, int cap, int count, String isfull)
    {
        Id = id;
        Capacity = cap;
        Count = count;
        isFull = isfull;
    }
}

package DAO;

public class Customer{
    private int Id;
    private String PassportNo;
    private String Country;
    private String FirstName;
    private String LastName;

    public Customer(int id, String passportNo, String country, String fname, String lname)
    {
        Id = id;
        PassportNo = passportNo;
        Country = country;
        FirstName = fname;
        LastName = lname;
    }
}

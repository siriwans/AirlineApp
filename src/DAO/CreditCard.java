package DAO;

public class CreditCard {
    private int CardNo;
    private int CustomerId;
    private String FirstName;
    private String LastName;
    private int CCV;
    private String EXP;

    public CreditCard(int cardNo, int customerId, String fname, String lname, int ccv, String exp)
    {
        CardNo = cardNo;
        CustomerId = customerId;
        FirstName = fname;
        LastName = lname;
        CCV = ccv;
        EXP = exp;
    }
}

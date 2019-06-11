import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class PreparedStmtMoviesExample
{
    // JDBC Driver Name & Database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String JDBC_DB_URL = "jdbc:mysql://csc365.toshikuboi.net:3306/sec05group03";
    // JDBC Database Credentials
    static final String JDBC_USER = "sec05group03";
    static final String JDBC_PASS = "group03@sec05";

    public static void main(String[] args)
    {
        try
        {
            //checks if the driver class is available
            Class.forName(JDBC_DRIVER);
            //get connection
            Connection connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            //create prepared sql statement

            PreparedStatement prepStatement = connObj.prepareStatement(
                    "SELECT * FROM customers where PassportNo = '12345johndoe';" );

            //plug in a parameter
            //prepStatement.setString(1, "James Cameron");
            //execute the statement
            ResultSet resObj = prepStatement.executeQuery();
            //loop through the result set
            while(resObj.next())
            {
                System.out.println(
                        //you have to know the data type of each attribute
                        "City: " + resObj.getString("City") + ", AirportCode: " + resObj.getString("AirportCode")
                                + ", AirportName: " + resObj.getString("AirportName"));
            }
            connObj.close();
        }
        catch (Exception sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
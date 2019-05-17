import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class PreparedStmtMoviesExample
{
    // JDBC Driver Name & Database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String JDBC_DB_URL = "jdbc:mysql://toshikuboi.net:3306/ssereesa";
    // JDBC Database Credentials
    static final String JDBC_USER = "ssereesa";
    static final String JDBC_PASS = "014479305";

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
                    "SELECT * FROM Movies,StarsIn WHERE Movies.mid=StarsIn.mid AND director=?");
            //plug in a parameter
            prepStatement.setString(1, "James Cameron");
            //execute the statement
            ResultSet resObj = prepStatement.executeQuery();
            //loop through the result set
            while(resObj.next())
            {
                System.out.println(
                        //you have to know the data type of each attribute
                        "title: " + resObj.getString("title") + ", year: " + resObj.getInt("year")
                                + ", actor: " + resObj.getString("sname"));
            }
            connObj.close();
        }
        catch (Exception sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
package com.group03_application;
import javax.sql.rowset.CachedRowSet;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class AirlineDatabase {

    // JDBC Driver Name & Database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String JDBC_DB_URL = "jdbc:mysql://csc365.toshikuboi.net:3306/sec05group03";
    // JDBC Database Credentials
    static final String JDBC_USER = "sec05group03";
    static final String JDBC_PASS = "group03@sec05";
    Connection connObj;

    public AirlineDatabase() {

    }

    // Connect and open the database
    public boolean Open() {

        try
        {
            //checks if the driver class is available
            Class.forName(JDBC_DRIVER);
            //get connection
            connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            return true;
        }
        catch (Exception sqlException)
        {
            sqlException.printStackTrace();
            return false;
        }
    }

    public boolean Close() {
        try {
            connObj.close();
            return true;
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }

    public ResultSet getFlight(String source, String destination) {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "SELECT * FROM flights where SourceAirport = '" + source + "' AND DestAirport = '" + destination + "';" );
            ResultSet results = query.executeQuery();
            return results;
        } catch (Exception sqlException) {
            System.out.println("//TODO No customer by that passport in our system");
        }
        return null;
    }


    public ResultSet SearchFlightsWithCity(String sourceCity, String destCity, String depart, String arrival, String numPassengers) {
        //numpassengers should be 1 by default if 0. Make sure that is implemented

        try {
            ResultSet results = null;
            if(sourceCity.equals("") || destCity.equals("")){
                System.out.println("Please fill in your source city, destination city");
            } else {

                if(arrival.equals("") && !depart.equals("")){
                    PreparedStatement query = connObj.prepareStatement(
                            "SELECT * FROM flights f1, flightInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                    "WHERE aSource.City LIKE '%" + sourceCity +
                                    "%' AND aDest.City LIKE '%" + destCity +
                                    "%' AND aSource.AirportCode = f1.sourceAirport " +
                                    " AND aDest.AirportCode = f1.destAirport " +
                                    " AND f1.FlightNo = f2.FlightNo " +
                                    " AND f2.departure BETWEEN '" + Timestamp.valueOf(depart + " 00:00:00") + "' AND '" + Timestamp.valueOf(depart + "23:59:59") +
                                    "' AND f2.planeId = p.id" +
                                    " AND p.count <= " + Integer.valueOf(numPassengers) +
                                    " AND f1.airline = a.id" +
                                    " AND f1.airline = f2.airline" );
                    results = query.executeQuery();
                }else if (depart.equals("") && !arrival.equals("")){
                    PreparedStatement query = connObj.prepareStatement(
                            "SELECT * FROM flights f1, flightInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                    "WHERE aSource.City LIKE '%" + sourceCity +
                                    "%' AND aDest.City LIKE '%" + destCity +
                                    "%' AND aSource.AirportCode = f1.sourceAirport " +
                                    " AND aDest.AirportCode = f1.destAirport " +
                                    " AND f1.FlightNo = f2.FlightNo " +
                                    " AND f2.Arrival BETWEEN '" + Timestamp.valueOf(arrival + " 00:00:00") + "' AND '" + Timestamp.valueOf(arrival + "23:59:59") +
                                    "' AND f2.planeId = p.id" +
                                    " AND p.count <= " + Integer.valueOf(numPassengers) +
                                    " AND f1.airline = a.id" +
                                    " AND f1.airline = f2.airline" );
                    results = query.executeQuery();
                }else if (depart.equals("") && arrival.equals("")){
                    PreparedStatement query = connObj.prepareStatement(
                            "SELECT * FROM flights f1, flightInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                    "WHERE aSource.City LIKE '%" + sourceCity +
                                    "%' AND aDest.City LIKE '%" + destCity +
                                    "%' AND aSource.AirportCode = f1.sourceAirport " +
                                    " AND aDest.AirportCode = f1.destAirport " +
                                    " AND f1.FlightNo = f2.FlightNo " +
                                    " AND f2.planeId = p.id" +
                                    " AND p.count <= " + Integer.valueOf(numPassengers) +
                                    " AND f1.airline = a.id" +
                                    " AND f1.airline = f2.airline" );
                    results = query.executeQuery();
                }else{
                    //all filled in
                    PreparedStatement query = connObj.prepareStatement(
                            "SELECT * FROM flights f1, flightInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                    "WHERE aSource.City LIKE '%" + sourceCity +
                                    "%' AND aDest.City LIKE '%" + destCity +
                                    "%' AND aSource.AirportCode = f1.sourceAirport " +
                                    " AND aDest.AirportCode = f1.destAirport " +
                                    " AND f1.FlightNo = f2.FlightNo " +
                                    " AND f2.departure BETWEEN '" + Timestamp.valueOf(depart + " 00:00:00") + "' AND '" + Timestamp.valueOf(depart + "23:59:59") +
                                    "' AND f2.Arrival BETWEEN '" + Timestamp.valueOf(arrival + " 00:00:00") + "' AND '" + Timestamp.valueOf(arrival + "23:59:59") +
                                    "' AND f2.planeId = p.id" +
                                    " AND p.count <= " + Integer.valueOf(numPassengers) +
                                    " AND f1.airline = a.id" +
                                    " AND f1.airline = f2.airline" );
                    results = query.executeQuery();
                }
            }
            return results;
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public ResultSet addBooking(Integer customer, Integer creditCard, Integer FlightNo, Integer[] seats){
        for(int i=0; i<seats.length; i++){
            try {
                PreparedStatement query = connObj.prepareStatement("" +
                        "INSERT INTO bookings(customer, cardno, FlightNo, seatno, cancelled)" +
                        "VALUES (" + customer +
                        ", " + creditCard +
                        ", " + FlightNo +
                        ", " + seats[i] +
                        ", 'No');"
                );
                query.executeQuery();
            } catch (Exception sqlException){
                sqlException.printStackTrace();
            }
        }
        //to display the information of the ticket below. Should be able to get all the info
        ResultSet results = null;
        try{
            PreparedStatement query = connObj.prepareStatement("SELECT * " +
                    "FROM flights f1, flightInfo f2, airlines a, airports a1, airports a2, planes p, seatings s " +
                    "WHERE f1.FlightNo = " + FlightNo +
                    "AND f1.FlightNo = f2.FlightNo " +
                    "AND f1.airline = a.id" +
                    "AND f1.sourceAirport = a1.AirportCode" +
                    "AND f1.destAirport = a2.AirportCode" +
                    "AND f2.planeId = p.id" +
                    "AND s.planeID = p.id" +
                    "AND s.seatNo IN (" + seats +" );");
            results = query.executeQuery();
            return results;
        }catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        return null;
        //TODO: Add trigger to db to add to plane count, check capacity, and fill seat customer in
    }


    public ResultSet availableSeats(String FlightNo, String airline) {
        System.out.println("availableSeats: FlightNo = " + FlightNo + "; airline = " + airline);
        try {
            //TODO Write query to get available seats the customer can choose from

            PreparedStatement query = connObj.prepareStatement("Select PlaneId from flightInfo fi where fi.Airline = '" + airline + "' and fi.FlightNo = '" + FlightNo + "';");

            ResultSet results = query.executeQuery();
            if ((Integer)results.getInt("PlaneId") != null)
            {
                int planeid = results.getInt("PlaneId");
                PreparedStatement query1 = connObj.prepareStatement("Select * from seatings s where s.PlaneId = " + planeid + " and s.Customer = NULL;");
                ResultSet results1 = query1.executeQuery();
                return results1;
            }
            else
            {
                System.out.println("No seats available in this plane");
                return null;
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public ResultSet searchForCustomer(String passport) {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "SELECT * FROM customers where PassportNo = '" + passport + "';" );
            ResultSet results = query.executeQuery();
            return results;
        } catch (Exception sqlException) {
            System.out.println("No customer by that passport in our system");
        }
        return null;
    }

    public void InsertCustomer(String fname, String lname, String country, String passwordNo) {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "INSERT INTO customers (PassportNo, Country, FirstName, LastName) " +
                            "VALUES ('" + passwordNo + "','" +  country + "','" +  fname + "','" + lname+ "');"
            );
            query.executeUpdate();
        } catch (Exception sqlException) {
            System.out.println("No customer by that passport in our system");
        }
    }


}


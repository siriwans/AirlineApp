package com.group03_application;
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

    public ResultSet SearchFlights(String source, String destin, String depart, String arrival, String numPassengers) {
        try {
            if(source == null || destin == null || numPassengers == null){
                System.out.println("Please fill in your source airport, destination airport, and number " +
                        "of tickets/passengers");
            }
            if(arrival == null && depart != null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p " +
                                "WHERE f1.flightNo = f2.flightNo AND " +
                                "f1.sourceAirport = '" + source + "' AND f1.destAirport = '" +
                                destin + "' AND f2.departure = " +
                                " AND f2.planeId = p.id AND p.count <= " +
                                Integer.valueOf(numPassengers) + ";");
                ResultSet results = query.executeQuery();
            }else if (depart == null && arrival != null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p " +
                                "WHERE f1.flightNo = f2.flightNo AND " +
                                "f1.sourceAirport = '" + source + "' AND f1.destAirport = '" +
                                destin + "' AND f2.arrival = " +
                                Date.valueOf(arrival) + " AND f2.planeId = p.id AND p.count <= " +
                                Integer.valueOf(numPassengers) + ";");
                ResultSet results = query.executeQuery();
            }else if (depart == null && arrival == null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p " +
                                "WHERE f1.flightNo = f2.flightNo AND " +
                                "f1.sourceAirport = '" + source + "' AND f1.destAirport = '" +
                                destin + "' AND f2.planeId = p.id AND p.count <= " +
                                Integer.valueOf(numPassengers));
                ResultSet results = query.executeQuery();
            }else{
                //all filled in
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p " +
                                "WHERE f1.flightNo = f2.flightNo AND " +
                                "f1.sourceAirport = '" + source + "' AND f1.destAirport = '" +
                                destin + "' AND f2.departure = " + Date.valueOf(depart) + " AND f2.arrival = " +
                                Date.valueOf(arrival) + " AND f2.planeId = p.id AND p.count <= " +
                                Integer.valueOf(numPassengers) + ";");
                ResultSet results = query.executeQuery();
            }
            return results;
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }


    public ResultSet AvailableSeats(String flightNo, String airline) {
        try {
            //TODO Write query to get available seats the customer can choose from
            /*
            PreparedStatement query =
                connObj.prepareStatement("SELECT * FROM flights WHERE sourceAirport = '" + source + "' AND destAirport = '" + destin + "';" );
            ResultSet results = query.executeQuery();

            return results; */
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public ResultSet SearchForCustomer(String passport) {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "SELECT id FROM customers where PassportNo = '" + passport + "';" );
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


package com.group03_application;

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

    public ResultSet SearchFlights(String source, String destin) {
        try {
            PreparedStatement query = connObj.prepareStatement(
                "SELECT * FROM flights WHERE sourceAirport = '" + source + "' AND destAirport = '" + destin + "';" );
            ResultSet results = query.executeQuery();

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
            PreparedStatement query = connObj.prepareStatement("SELECT * FROM flights WHERE sourceAirport = '" + source + "' AND destAirport = '" + destin + "';" );
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
                    "SELECT id FROM customers where PassportNo =  passport" );
            ResultSet results = query.executeQuery();
            return results;
        } catch (Exception sqlException) {
            System.out.println("No customer by that passport in our system");
        }
        return null;
    }
}


package com.group03_application;
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

    public ResultSet SearchFlights(String source, String destin, String depart, String arrival, String numPassengers) {
        try {
            ResultSet results;
            if(source == null || destin == null){
                System.out.println("Please fill in your source airport, destination airport");
            }
            if(arrival == null && depart != null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p " +
                                "WHERE f1.flightNo = f2.flightNo" +
                                " AND f1.sourceAirport = '" + source +
                                "' AND f1.destAirport = '" + destin +
                                "' AND f2.departure BETWEEN " + Timestamp.valueOf(depart + " 00:00:00") + " AND " + Timestamp.valueOf(depart + "23:59:59") +
                                " AND f2.planeId = p.id" +
                                " AND p.count <= " + Integer.valueOf(numPassengers) +
                                " AND f1.airline = a.id" +
                                " AND f1.airline = f2.airline" +
                                " AND f1.sourceAirport = aSource.airportCode" +
                                " AND f2.destAirport = a.Dest.airportCode;" );
                results = query.executeQuery();
            }else if (depart == null && arrival != null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p " +
                                "WHERE f1.flightNo = f2.flightNo " +
                                "AND f1.sourceAirport = '" + source +
                                "' AND f1.destAirport = '" + destin +
                                "' AND f2.arrival BETWEEN " + Timestamp.valueOf(arrival + " 00:00:00") + " AND " + Timestamp.valueOf(arrival + "23:59:59") +
                                " AND f2.planeId = p.id" +
                                " AND p.count <= " + Integer.valueOf(numPassengers) +
                                " AND f1.airline = a.id" +
                                " AND f1.airline = f2.airline" +
                                " AND f1.sourceAirport = aSource.airportCode" +
                                " AND f2.destAirport = a.Dest.airportCode;" );
                results = query.executeQuery();
            }else if (depart == null && arrival == null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p " +
                                "WHERE f1.flightNo = f2.flightNo " +
                                "AND f1.sourceAirport = '" + source +
                                "' AND f1.destAirport = '" + destin +
                                "' AND f2.planeId = p.id " +
                                " AND p.count <= " + Integer.valueOf(numPassengers) +
                                " AND f1.airline = a.id" +
                                " AND f1.airline = f2.airline" +
                                " AND f1.sourceAirport = aSource.airportCode" +
                                " AND f2.destAirport = a.Dest.airportCode;" );
                results = query.executeQuery();
            }else{
                //all filled in
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p, airline a, airport aSource, airport aDest " +
                                "WHERE f1.flightNo = f2.flightNo AND " +
                                "f1.sourceAirport = '" + source +
                                "' AND f1.destAirport = '" + destin +
                                "' AND f2.departure BETWEEN " + Timestamp.valueOf(depart + " 00:00:00") + " AND " + Timestamp.valueOf(depart + "23:59:59") +
                                " AND f2.arrival BETWEEN " + Timestamp.valueOf(arrival + " 00:00:00") + " AND " + Timestamp.valueOf(arrival + "23:59:59") +
                                " AND f2.planeId = p.id" +
                                " AND p.count <= " + Integer.valueOf(numPassengers) +
                                " AND f1.airline = a.id" +
                                " AND f1.airline = f2.airline" +
                                " AND f1.sourceAirport = aSource.airportCode" +
                                " AND f2.destAirport = a.Dest.airportCode;");
                results = query.executeQuery();
            }
            return results;
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public ResultSet SearchFlightsWithCity(String sourceCity, String destCity, String depart, String arrival, String numPassengers) {
        //numpassengers should be 1 by default if 0. Make sure that is implemented
        try {
            ResultSet results;
            if(sourceCity == null || destCity == null){
                System.out.println("Please fill in your source city, destination city");
            }
            if(arrival == null && depart != null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                "WHERE aSource.city = '" + sourceCity +
                                "' AND aDest.city = '" + destCity +
                                "' AND aSource.airportCode = f1.sourceAirport " +
                                " AND aDest.airportCode = f1.destAirport " +
                                " AND f1.flightNo = f2.flightNo " +
                                " AND f2.departure BETWEEN " + Timestamp.valueOf(depart + " 00:00:00") + " AND " + Timestamp.valueOf(depart + "23:59:59") +
                                " AND f2.planeId = p.id" +
                                " AND p.count <= " + Integer.valueOf(numPassengers) +
                                " AND f1.airline = a.id" +
                                " AND f1.airline = f2.airline" );
                results = query.executeQuery();
            }else if (depart == null && arrival != null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                "WHERE aSource.city = '" + sourceCity +
                                "' AND aDest.city = '" + destCity +
                                "' AND aSource.airportCode = f1.sourceAirport " +
                                " AND aDest.airportCode = f1.destAirport " +
                                " AND f1.flightNo = f2.flightNo " +
                                " AND f2.arrival BETWEEN " + Timestamp.valueOf(arrival + " 00:00:00") + " AND " + Timestamp.valueOf(arrival + "23:59:59") +
                                " AND f2.planeId = p.id" +
                                " AND p.count <= " + Integer.valueOf(numPassengers) +
                                " AND f1.airline = a.id" +
                                " AND f1.airline = f2.airline" );
                results = query.executeQuery();
            }else if (depart == null && arrival == null){
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                "WHERE aSource.city = '" + sourceCity +
                                "' AND aDest.city = '" + destCity +
                                "' AND aSource.airportCode = f1.sourceAirport " +
                                " AND aDest.airportCode = f1.destAirport " +
                                " AND f1.flightNo = f2.flightNo " +
                                " AND f2.planeId = p.id" +
                                " AND p.count <= " + Integer.valueOf(numPassengers) +
                                " AND f1.airline = a.id" +
                                " AND f1.airline = f2.airline" );
                results = query.executeQuery();
            }else{
                //all filled in
                PreparedStatement query = connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightsInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                "WHERE aSource.city = '" + sourceCity +
                                "' AND aDest.city = '" + destCity +
                                "' AND aSource.airportCode = f1.sourceAirport " +
                                " AND aDest.airportCode = f1.destAirport " +
                                " AND f1.flightNo = f2.flightNo " +
                                " AND f2.departure BETWEEN " + Timestamp.valueOf(depart + " 00:00:00") + " AND " + Timestamp.valueOf(depart + "23:59:59") +
                                " AND f2.arrival BETWEEN " + Timestamp.valueOf(arrival + " 00:00:00") + " AND " + Timestamp.valueOf(arrival + "23:59:59") +
                                " AND f2.planeId = p.id" +
                                " AND p.count <= " + Integer.valueOf(numPassengers) +
                                " AND f1.airline = a.id" +
                                " AND f1.airline = f2.airline" );
                results = query.executeQuery();
            }
            return results;
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public void addBooking(Integer customer, Integer creditCard, Integer flightNo, Integer[] seats){
        for(int i=0; i<seats.length; i++){
            try {
                PreparedStatement query = connObj.prepareStatement("" +
                        "INSERT INTO bookings(customer, cardno, flightno, seatno, cancelled)" +
                        "VALUES (" + customer +
                        ", " + creditCard +
                        ", " + flightNo +
                        ", " + seats[i] +
                        ", 'No');"
                );
            } catch (Exception sqlException){
                sqlException.printStackTrace();
            }
        }
    }


    public ResultSet availableSeats(String flightNo, String airline) {
        System.out.println("availableSeats: flightNo = " + flightNo + "; airline = " + airline);
        try {
            //TODO Write query to get available seats the customer can choose from

            PreparedStatement query = connObj.prepareStatement("Select PlaneId from flightInfo fi where fi.Airline = '" + airline + "' and fi.FlightNo = '" + flightNo + "';");

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


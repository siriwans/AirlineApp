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

    //returns NULL if no card in system, return resultSet if card in system
    public ResultSet checkCreditCard(int cardnum, String fname, String lname)
    {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "SELECT * FROM creditCards where CardNo = " + cardnum + " and Firstname = '" +
                            fname + "' and Lastname = '" + lname+ "';" );
            ResultSet results = query.executeQuery();
            if (results.next() && (Integer)results.getInt("CardNo") != null)
            {
                results.beforeFirst();
                System.out.println("YES");
                return results;
            }
        } catch (Exception sqlException) {
            System.out.println("No creditCard in our system please try again");
        }
        System.out.println("NO");
        return null;
    }

    public ResultSet bookingTransaction()
    {
        return null;
    }

    public ResultSet getFlight(String source, String destination) {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "SELECT * FROM flights where SourceAirport = '" + source + "' AND DestAirport = '" + destination + "';");
            ResultSet results = query.executeQuery();
            return results;
        } catch (Exception sqlException) {
            System.out.println("//TODO No customer by that passport in our system");
        }
        return null;
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

    public ResultSet addBooking(Integer customer, Integer creditCard, Integer flightNo, Integer[] seats){
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
                    "WHERE f1.flightNo = " + flightNo +
                    "AND f1.flightNo = f2.flightNo " +
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

    /*public void InsertBooking(int customer, int cardNo, int flightNo, int airline, int seatNo)
    {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "INSERT INTO bookings (Customer, CardNo, FlightNo, Airline, SeatNo, Cancelled) " +
                            "VALUES ('" + customer + "','" +  cardNo + "','" +  flightNo + "','" + airline +  "','"
                            + seatNo + "', 'No' );"
            );
            query.executeUpdate();
        } catch (Exception sqlException) {
        }
    }*/

    //PRINTS ALL SEATS AVAILABLE GIVEN FLIGHTNO AND AIRLINE
    public ResultSet availableSeats(int flightNo, int airline) {
        System.out.println("availableSeats: flightNo = " + flightNo + "; airline = " + airline);
        try {
            //TODO Write query to get available seats the customer can choose from

            PreparedStatement query = connObj.prepareStatement("Select * from flightInfo fi join seatings s on s.PlaneId = fi.PlaneId" +
                    " and fi.Airline = '" + airline + "' and fi.FlightNo = '" + flightNo + "';");
            ResultSet results = query.executeQuery();
            while (results.next())
            {
                System.out.println("SeatNo: " + results.getInt("SeatNo") + ", " +
                        "Class: " + results.getString("Class") + ", " +
                        "SeatType: " + results.getString("SeatType") + ", " +
                        "Price: " + results.getDouble("Price"));
            }
            results.beforeFirst();
            return results;
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


    //PRINTS THE CUSTOMER ID FOR CUSTOMER SIGN IN
    public ResultSet searchForCustomer(String passport) {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "SELECT * FROM customers where PassportNo = '" + passport + "';" );
            ResultSet results = query.executeQuery();

            results.next();
            System.out.println("Id: " + results.getInt("Id") + ", " +
                            "FirstName: " + results.getString("FirstName") + ", " +
                            "LastName: " + results.getString("LastName"));
            return results;

        } catch (Exception sqlException) {
            System.out.println("No customer by that passport in our system");
        }
        return null;
    }


    //INSERTS CUSTOMER INTO THE DATABASES WITH SIGN UP ->>> WORKS FOR SURE
    public void InsertCustomer(String fname, String lname, String country, String passwordNo) {
        try {
            PreparedStatement query = connObj.prepareStatement(
                    "INSERT INTO customers (PassportNo, Country, FirstName, LastName) " +
                            "VALUES ('" + passwordNo + "','" +  country + "','" +  fname + "','" + lname+ "');"
            );
            query.executeUpdate();
        } catch (Exception sqlException) {
        }
    }


}


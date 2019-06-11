package com.group03_application;
import DAO.*;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AirlineApp {

    public static AirlineDatabase airlineDB;
    public static FlightDAO flightDAO;
    public static FlightInfoDAO flightInfoDAO;

    public static void main(String[] args)
    {
        // Open database
        airlineDB = new AirlineDatabase();
        airlineDB.Open();

        WelcomePage welcomePage = new WelcomePage();

        flightDAO = new FlightDAO();


        // Close

    }

    public static void printResults(ResultSet rs) {

        try {
            rs.beforeFirst();   // reset cursor to beginning

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void ChanelleTest() {
        ResultSet rs = null;
        // rs = airlineDB.SearchFlights("Akron/Canton OH", "Alma", "", "", "1");

        String sourceCity = "Akron/Canton OH";
        String destCity = "Alma";
        String depart = "2019-01-01";
        String arrival = "2019-05-01";
        try {
            PreparedStatement query = airlineDB.connObj.prepareStatement(
                        "SELECT * FROM flights f1, flightInfo f2, planes p, airlines a, airports aSource, airports aDest " +
                                "WHERE aSource.City LIKE '%" + sourceCity +
                                "%' AND aDest.City LIKE '%" + destCity +
                                "%' AND aSource.AirportCode = f1.sourceAirport " +
                                " AND aDest.AirportCode = f1.destAirport " +
                                " AND f1.FlightNo = f2.FlightNo " +
                                " AND f2.departure BETWEEN '" + Timestamp.valueOf(depart + " 00:00:00") + "' AND '" + Timestamp.valueOf(depart + " 23:59:59") + "'" +
                                " AND f2.Arrival BETWEEN '" + Timestamp.valueOf(arrival + " 00:00:00") + "' AND '" + Timestamp.valueOf(arrival + " 23:59:59") + "'" +
                                " AND f2.planeId = p.id" +
                                " AND p.count <= " + Integer.valueOf("1") +
                                " AND f1.airline = a.id" +
                            " AND f1.airline = f2.airline"
                    );
            rs = query.executeQuery();

            printResults(rs);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

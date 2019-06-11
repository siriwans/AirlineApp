package com.group03_application;
import DAO.*;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;

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

        String flightNo = "726";
        String airline = "5";

        try {
            PreparedStatement query = airlineDB.connObj.prepareStatement(
                    "SELECT * FROM seatings s " +
                            "JOIN flightInfo fin on s.PlaneId = fin.PlaneId" +
                            " WHERE fin.FlightNo = " + flightNo +
                            " AND fin.airline = " + airline +
                            " AND s.Customer is Null;"
                    );
            rs = query.executeQuery();

            printResults(rs);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

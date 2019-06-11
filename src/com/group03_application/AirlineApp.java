package com.group03_application;
import DAO.*;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class AirlineApp {

    public static AirlineDatabase airlineDB;
    public static FlightDAO flightDAO;
    public static FlightInfoDAO flightInfoDAO;

    public static void main(String[] args)
    {
        // Open database
        airlineDB = new AirlineDatabase();
        airlineDB.Open();

        ResultSet rs = airlineDB.SearchFlights("Akron/Canton OH", "Alma", "", "", "1");


        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("querying SELECT * FROM XXX");
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (Exception e) {

        }
        //airlineDB.SearchFlights("", "", "", "", "1");

        WelcomePage welcomePage = new WelcomePage();

        flightDAO = new FlightDAO();


        // Close

    }
}

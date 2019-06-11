package com.group03_application;
import DAO.*;

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
}

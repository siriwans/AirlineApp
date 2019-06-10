package com.group03_application;
import DAO.*;

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

package com.group03_application;

public class AirlineApp {

    public static AirlineDatabase airlineDB;

    public static void main(String[] args)
    {
        // Open database
        airlineDB = new AirlineDatabase();
        airlineDB.Open();
        WelcomePage welcomePage = new WelcomePage();

        // Close

    }
}

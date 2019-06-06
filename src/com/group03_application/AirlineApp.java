package com.group03_application;

import javax.swing.*;

public class AirlineApp {

    private static AirlineDatabase airlineDB;

    public static void main(String[] args)
    {
        airlineDB = new AirlineDatabase();
        boolean yes = airlineDB.OpenDatabase();
        System.out.print(yes);

        WelcomePage welcomePage = new WelcomePage(airlineDB);
    }
}

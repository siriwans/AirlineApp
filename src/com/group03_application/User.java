package com.group03_application;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    public static int Id = -1;
    public static String firstName;
    public static String lastName;

    public static ArrayList<Integer> listSeatNum = new ArrayList<Integer>(); // lost of seat numbers (in case of multiple passengers
    public static int flightNo = -1; // of the flight they selected
    public static int airline = -1; // of the airline of the flight they selected
    public static ArrayList<Integer> listReservationNum = new ArrayList<Integer>(); //each booking
    public static ArrayList<Double> singleBookingPrice = new ArrayList<Double>();
    public static ArrayList<Integer> reservationList = new ArrayList<Integer>();
    public static int cardNumber = -1;
    public static double totalPrice = 0;

    public static int numPassengers = -1;


    public static void confirmPurchase()
    {
        listSeatNum = new ArrayList<Integer>();
        flightNo = -1;
        airline = -1;
        listReservationNum = new ArrayList<Integer>();
        singleBookingPrice = new ArrayList<Double>();
        cardNumber = -1;
        totalPrice = 0;
    }
}

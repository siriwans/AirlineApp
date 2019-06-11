package com.group03_application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SeatComponent {
    private JLabel txtPassenger;
    private JComboBox cboSeatType;
    private JPanel pnlMain;
    private JTextField txtSeatType;
    private JTextField txtClass;
    private JTextField txtPrice;

    public SeatComponent(int flightNo, int airline, int passengerID) {
        // Label cbo
        txtPassenger.setText("Passenger " + passengerID + ": ");

        // Populate cbo
        ResultSet seatTypes = AirlineApp.airlineDB.availableSeats(Integer.toString(flightNo), Integer.toString(airline));
        System.out.println("creating seat component");
        try {
            while (seatTypes.next()) {
                cboSeatType.addItem(seatTypes.getString(1));
            }
        } catch (Exception sqlException){
            sqlException.printStackTrace();
        }
    }

    public JPanel getMainPanel() {
        return pnlMain;
    }
}

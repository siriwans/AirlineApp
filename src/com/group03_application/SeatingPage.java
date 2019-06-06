package com.group03_application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatingPage {
    private JTextField txtFlightNo;
    private JScrollPane scrSeats;
    private JPanel pnlMain;


    public SeatingPage(int flightNo, String airline, int numPassengers) {
        for (int i = 0; i < numPassengers; i++) {
            SeatComponent seat = new SeatComponent(flightNo, airline);
            scrSeats.add(seat.getMainPanel());
        }

    }

    public JPanel getMainPanel() {
        return pnlMain;
    }
}

package com.group03_application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatingPage {
    private JTextField txtFlightNo;
    private JScrollPane scrSeats;
    private JPanel pnlMain;
    private JTextField txtAirline;
    private JLabel lblAirline;


    public SeatingPage(int flightNo, int airline, int numPassengers) {
        // Labeling
        txtFlightNo.setText(Integer.toString(flightNo));
        txtAirline.setText(Integer.toString(airline));

        JPanel gridResults = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;

        for (int i = 0; i < numPassengers; i++) {
            c.gridy = i;
            int passengerID = i+1;
            SeatComponent seat = new SeatComponent(flightNo, airline, passengerID);
            gridResults.add(seat.getMainPanel(), c);
        }
        scrSeats.setViewportView(gridResults);

    }

    public JPanel getMainPanel() {
        return pnlMain;
    }
}

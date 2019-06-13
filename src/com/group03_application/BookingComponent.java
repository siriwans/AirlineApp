package com.group03_application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingComponent {
    private JLabel lblReservationNo;
    private JTextField hereIsOtherInformationTextField;
    private JLabel txtReservationNo;
    private JPanel pnlMain;
    private JButton btnCancel;

    public BookingComponent(int i) {

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AirlineApp.airlineDB
            }
        });
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }



}

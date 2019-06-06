package com.group03_application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatComponent {
    private JLabel txtPassenger;
    private JComboBox cboSeatType;
    private JPanel pnlMain;

    public SeatComponent(int flightNo, String airline) {
        cboSeatType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getMainPanel() {
        return pnlMain;
    }
}

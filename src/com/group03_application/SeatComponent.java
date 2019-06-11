package com.group03_application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SeatComponent {
    private JLabel txtPassenger;
    private JComboBox cboSeatType;
    private JPanel pnlMain;
    private JTextField txtSeatNo;
    private JTextField txtClass;
    private JTextField txtSeatType;
    private JButton btnSelect;
    private JTextField txtPrice;
    private JLabel lblSeatNo;
    private JLabel lblPrice;
    private JLabel lblSeatType;
    private JLabel lblClass;

    public SeatComponent(int flightNo, int airline, int passengerID) {
        // Label cbo
        txtPassenger.setText("Passenger " + passengerID + ": ");

        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If seat previously selected, delete old one
                if (!txtSeatNo.getText().equals("")) {
                    AirlineApp.airlineDB.unassignSeat(txtSeatNo.getText(), Integer.toString(flightNo), Integer.toString(airline));
                } else {
                    if (txtClass.getText().equals("") || txtSeatType.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter a class type: First, Business, Economy\n " +
                                "and enter a seat type: Window, Aisle");
                    } else {
                        ResultSet rs = AirlineApp.airlineDB.availableSeats(Integer.toString(flightNo), Integer.toString(airline),
                                txtClass.getText(), txtSeatType.getText());

                        try {
                            if (rs.next()) {
                                String seatNo = Integer.toString(rs.getInt("SeatNo"));

                                // update gui
                                txtSeatNo.setText(seatNo);
                                txtPrice.setText(Integer.toString(rs.getInt("Price")));

                                // update database
                                AirlineApp.airlineDB.assignSeat(seatNo, Integer.toString(flightNo), Integer.toString(flightNo));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return pnlMain;
    }
}

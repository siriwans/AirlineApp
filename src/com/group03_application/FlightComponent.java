package com.group03_application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightComponent {
    private JLabel lblDeparture;
    private JTextField txtDeparture;
    private JLabel lblArrival;
    private JTextField txtArrival;
    private JButton btnSelect;
    private JTextField txtSourceAirport;
    private JLabel lblSourceAirport;
    private JLabel lblDestination;
    private JTextField txtDestination;
    private JPanel pnlMain;
    private JTextField txtFlightNo;
    private int numPassengers;

    public FlightComponent(int flightNo, int airline, int numPassengers, String source, String destination, String departure, String arrival) {
        this.txtSourceAirport.setText(source);
        this.txtDestination.setText(destination);
        this.txtDeparture.setText(departure);
        this.txtArrival.setText(arrival);
        this.txtFlightNo.setText(Integer.toString(flightNo));
        this.numPassengers = numPassengers;


        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("select flight");
                SeatingPage seating = new SeatingPage(flightNo, airline, numPassengers);
                int response = JOptionPane.showConfirmDialog(null, seating.getMainPanel(),
                        "Select seats", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (response == JOptionPane.OK_OPTION) {
                    User.flightNo = flightNo;
                    User.airline = airline;
                }

                // open transaction page
                if (response == JOptionPane.OK_OPTION) {
                    JFrame frame = new JFrame();
                    frame.setTitle("Confirm Booking");
                    frame.setMinimumSize(new Dimension(500, 300));
                    frame.setContentPane(new FlightTransactionComponent().getPnlMain());
                    frame.setVisible(true);
                    //FlightTransactionComponent transaction =  new FlightTransactionComponent();
                    //int response2 = JOptionPane.showConfirmDialog(null, transaction.getPnlMain(),
                    //        "Confirm Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    // confirm booking
                    //if (response2 == JOptionPane.OK_OPTION) {


                    //}
                }
            }
        });

    }

    public JPanel getMainPanel() {
        return pnlMain;
    }
}

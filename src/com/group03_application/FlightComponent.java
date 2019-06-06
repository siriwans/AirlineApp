package com.group03_application;

import javax.swing.*;
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

    public FlightComponent(int flightNo, String airline, int numPassengers, String source, String destination, String departure, String arrival) {
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
                JFrame frame = new JFrame();
                SeatingPage seating = new SeatingPage(flightNo, airline, numPassengers);
                frame.setContentPane(seating.getMainPanel());
                frame.setVisible(true);
                //JOptionPane.showMessageDialog(seating.getMainPanel(), null);
            }


        });
    }

    public JPanel getMainPanel() {
        return pnlMain;
    }
}

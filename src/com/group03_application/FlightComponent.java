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
    private JLabel lblSelectSeating;
    private JLabel lblClass;
    private JComboBox cboClass;

    public FlightComponent(String source, String destination, String departure, String arrival) {
        this.txtSourceAirport.setText(source);
        this.txtDestination.setText(destination);
        this.txtDeparture.setText(departure);
        this.txtArrival.setText(arrival);


        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getMainPanel() {
        return pnlMain;
    }
}

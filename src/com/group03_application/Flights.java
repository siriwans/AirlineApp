package com.group03_application;

import javax.swing.*;

public class Flights {
    private JPanel pnlFlightInfo;
    private JLabel lblPlaneID;
    private JLabel lblPlaneCapacity;
    private JTextField txtPlaneCapacity;
    private JTextField txtPlaneID;
    private JPanel pnlSelectSeating;
    private JLabel lblSelectSeating;
    private JLabel lblClass;
    private JComboBox cboClass;
    private JPanel pnlFlightMain;

    public Flights() {
        JFrame frame = new JFrame("Flight");
        frame.setContentPane(pnlFlightMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public JPanel GetMainPanel() {
        return pnlFlightMain;
    }
}

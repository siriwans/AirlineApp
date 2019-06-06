package com.group03_application;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.image.BufferedImage;

public class WelcomePage {

    private AirlineDatabase airlineDB;

    private JPanel pnlFlights;
    private JTextField txtSource;
    private JTextField txtDestin;
    private JButton btnSearch;
    private JTable tblFlights;
    private JPanel pnlMain;
    private JButton btnFlights;
    private JButton btnUser;
    private JPanel pnlFlightsSearch;
    private JLabel lblSource;
    private JLabel lblDestin;
    private JPanel pnlNavigation;
    private JPanel pnlBody;
    private JPanel pnlUser;
    private JLabel lblDepartture;
    private JTextField txtDepartture;
    private JLabel lblArrival;
    private JTextField txtArrival;
    private JPanel pnlHeader;
    private JPanel pnlFooter;
    private JPanel pnlUserCard;
    private JPanel pnlLogin;
    private JLabel lblLogin;
    private JLabel lblPassportID;
    private JTextField txtPassportID;
    private JPanel pnlLoginBody;
    private JButton btnSignUp;
    private JPanel pnlSignUp;
    private JPanel pnlSignUpBody;
    private JLabel lblFirstName;
    private JTextField txtFirstName;
    private JLabel lblLastName;
    private JTextField txtLastName;
    private JLabel lblCountry;
    private JTextField txtCountry;
    private JLabel lblCardInfo;
    private JTable tblCard;
    private JButton btnLogin;
    private JLabel lblSignUpHeader;
    private JPanel pnlFlightsResults;
    private JPanel pnlFlightsZoom;


    public WelcomePage(AirlineDatabase airlineDB) {
        JFrame frame = new JFrame("WelcomePage");
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(txtSource.getText());
                System.out.println(txtDestin.getText());
                airlineDB.testQuery(txtSource.getText(), txtDestin.getText());
            }
        });
        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(pnlBody.getLayout());
                cl.show(pnlBody, "cardUser");
            }
        });
        btnFlights.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(pnlBody.getLayout());
                cl.show(pnlBody, "cardFlights");


                JOptionPane.showMessageDialog(null, (new Flights()).GetMainPanel());
            }
        });

        System.out.println("hello");
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(pnlUserCard.getLayout());
                cl.show(pnlUserCard, "cardSignUp");
            }
        });
    }


    private void createUIComponents() {

        // Column Names
        Vector<String> columnNames = new Vector<String>();
        columnNames.addElement("FlightNo");
        columnNames.addElement("Airline");
        columnNames.addElement("Source");
        columnNames.addElement("Destination");

        Vector<String> d1 = new Vector<String>();
        d1.addElement("1");
        d1.addElement("ASC");
        d1.addElement("San Antonio");
        d1.addElement("San Francisco");

        Vector<Vector> data = new Vector<Vector>();
        data.add(d1);

        // Initializing the JTable
        tblFlights = new JTable(data, columnNames);
        tblFlights.setBounds(30, 40, 200, 300);
    }

}

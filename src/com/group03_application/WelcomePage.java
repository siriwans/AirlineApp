package com.group03_application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class WelcomePage {

    AirlineDatabase airlineDB;

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
    private JScrollPane scrResults;
    private JList<JPanel> listResults;


    public WelcomePage(AirlineDatabase airlineDB) {
        this.airlineDB = airlineDB;

        JFrame frame = new JFrame("WelcomePage");
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Searching...");
                RequeryFlightsTable();
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

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(pnlUserCard.getLayout());
                cl.show(pnlUserCard, "cardSignUp");
            }
        });
    }


    private void RequeryFlightsTable() {

        ResultSet data = airlineDB.SearchFlights(txtSource.getText(), txtDestin.getText());

        JPanel gridResults = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;

        try {
            while (data.next()) {
                System.out.println("Hello");
                FlightListComponent flightObj = new FlightListComponent(
                        data.getString("sourceAirport"),
                        data.getString("destAirport"),
                        "10:00pm",
                        "3:00am");
                        //TODO data.getString("departure"),
                        //TODO data.getString("arrival" ));
                gridResults.add(flightObj.getMainPanel(), c);
            }
        }
        catch (Exception sqlException){
            sqlException.printStackTrace();
        }

        scrResults.setViewportView(gridResults);


        // Initializing the JTable
        //tblFlights = new JTable(data, columnNames);
        //scrResults.setViewportView(listResults);
        //tblFlights.setBounds(30, 40, 200, 400);
    }

}

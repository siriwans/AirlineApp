package com.group03_application;
import DAO.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

public class WelcomePage {

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
    private JButton btnLogin;
    private JLabel lblSignUpHeader;
    private JPanel pnlFlightsResults;
    private JPanel pnlFlightsZoom;
    private JScrollPane scrResults;
    private JButton btnSignUpCancel;
    private JButton btnSignUpConfirm;
    private JPanel pnlUserInfo;
    private JLabel lblUserHeader;
    private JPanel pnlReservations;
    private JScrollPane scrReservationsActive;
    private JScrollPane scrReservationsInactive;
    private JTextField txtPassport;
    private JButton btnLogout;
    private JTextField txtPassengerNo;
    private JList<JPanel> listResults;


    public WelcomePage() {

        JFrame frame = new JFrame("WelcomePage");
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Default: show user info
        ((CardLayout)(pnlBody.getLayout())).show(pnlBody, "cardUser");


        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtSource.getText().equals("") | txtDestin.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select a source and destination");
                    return;
                }
                //RequeryFlightsResult(AirlineApp.flightDAO.get(txtSource.getText(), txtDestin.getText()));
                RequeryFlightsResult();

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

            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(pnlUserCard.getLayout());
                cl.show(pnlUserCard, "cardSignUp");
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // User must input login info
                if (txtPassportID.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter your passport.");
                    return;
                }
                // Verify login info
                else {
                    try{
                        ResultSet data = AirlineApp.airlineDB.searchForCustomer(txtPassportID.getText());
                        data.next();
                        User.Id = data.getInt("Id");
                        User.firstName = data.getString("FirstName");
                        User.lastName = data.getString("LastName");
                    }
                    catch (Exception sqlException){
                        sqlException.printStackTrace();
                    }

                    //TODO need to show customer login info
                    ((CardLayout)(pnlUserCard.getLayout())).show(pnlUserCard, "cardUserInfo");
                    RequeryActiveReservations();
                }

            }
        });
        btnSignUpCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(pnlUserCard.getLayout())).show(pnlUserCard, "cardLogin");
            }
        });
        btnSignUpConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AirlineApp.airlineDB.InsertCustomer(txtFirstName.getText(), txtLastName.getText(), txtCountry.getText(), txtPassport.getText());
                JOptionPane.showMessageDialog(frame, "Customer created. Login with your passport id: ###");
                ((CardLayout)(pnlUserCard.getLayout())).show(pnlUserCard, "cardLogin");
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)(pnlUserCard.getLayout())).show(pnlUserCard, "cardLogin");
            }
        });
    }


    private void RequeryFlightsResult() {

        JPanel gridResults = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        ResultSet results = AirlineApp.airlineDB.SearchFlightsWithCity(txtSource.getText(),
                txtDestin.getText(), txtDepartture.getText(), txtArrival.getText(), txtPassengerNo.getText());

        try {
            while(results.next()) {
                FlightComponent flightComponent = new FlightComponent(
                        results.getInt("f1.flightNo"),
                        results.getInt("f1.airline"),
                        Integer.parseInt(txtPassengerNo.getText()),
                        results.getString("aSource.airportCode"),
                        results.getString("aDest.airportCode"),
                        results.getString("f2.arrival"),
                        results.getString("f2.departure"));

                gridResults.add(flightComponent.getMainPanel(), c);
            }
        } catch (Exception sqlException){
            sqlException.printStackTrace();
        }

        scrResults.setViewportView(gridResults);
    }

    private void RequeryActiveReservations() {

        JPanel gridResults = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        // TODO get list of booked flights
        ResultSet results = AirlineApp.airlineDB.SearchFlightsWithCity(txtSource.getText(),
                txtDestin.getText(), txtDepartture.getText(), txtArrival.getText(), txtPassengerNo.getText());

        try {
            for (int i = 0; i < 3; i++) {
                BookingComponent bookingComponent = new BookingComponent(i);

                gridResults.add(bookingComponent.getPnlMain(), c);
            }
        } catch (Exception sqlException){
            sqlException.printStackTrace();
        }

        scrReservationsActive.setViewportView(gridResults);
    }


}

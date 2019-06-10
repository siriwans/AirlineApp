package com.group03_application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class WelcomePage {

    private int currentCustomer = -1;

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

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Searching...");
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

                if (txtPassportID.getText().equals("")) {
                    System.out.println("empty string");
                    JOptionPane.showMessageDialog(frame, "Please enter your passport.");
                } else {
                    ResultSet data = AirlineApp.airlineDB.searchForCustomer(txtPassportID.getText());
                    try{
                        currentCustomer = (int)data.getInt("Id");}
                    catch (Exception sqlException){
                        sqlException.printStackTrace();}
                    //TODO need to show customer login info
                    ((CardLayout)(pnlUserCard.getLayout())).show(pnlUserCard, "cardUserInfo");
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
                currentCustomer = -1;
                ((CardLayout)(pnlUserCard.getLayout())).show(pnlUserCard, "cardLogin");
            }
        });
    }


    private void RequeryFlightsResult() {

        ResultSet data = AirlineApp.airlineDB.SearchFlights(txtSource.getText(), txtDestin.getText(), txtDepartture.getText(),
                    txtArrival.getText(), txtPassengerNo.getText());

        JPanel gridResults = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;

        try {
            while (data.next()) {
                FlightComponent flightObj = new FlightComponent(
                        data.getInt("flightNo"),
                        data.getInt("Airline"),
                        Integer.parseInt(txtPassengerNo.getText()),
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

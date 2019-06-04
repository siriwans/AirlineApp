package com.group03_application;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AirlineApp {

    private static AirlineDatabase airlineDB;

    private JPanel pnlMain;
    private JPanel pnlMainCenter;
    private JTextField txtSource;
    private JLabel lblHeader;
    private JLabel lblSource;
    private JTextField txtDestin;
    private JLabel lblDestin;
    private JButton btnSearch;
    private JTable tblFlights;
    private JPanel pnlInput;


    public AirlineApp() {

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(txtSource.getText());
                System.out.println(txtDestin.getText());
                airlineDB.testQuery(txtSource.getText(), txtDestin.getText());
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("AirlineApp");
        frame.setContentPane(new AirlineApp().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        airlineDB = new AirlineDatabase();
        boolean yes = airlineDB.OpenDatabase();
        System.out.print(yes);
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

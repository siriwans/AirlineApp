package com.group03_application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightTransactionComponent {

    private JPanel pnlMain;
    private JLabel lblTransactionInfo;
    private JLabel lblFlightInfo;
    private JLabel lblAirline;
    private JLabel lblFlight;
    private JLabel lblCardNo;
    private JTextField txtCardNumber;
    private JTextField txtFlight;
    private JTextField txtAirline;
    private JButton btnConfirm;
    private JLabel lblTotalAmount;
    private JTextField txtTotalAmount;

    public FlightTransactionComponent() {
        // update gui
        txtAirline.setText(Integer.toString(User.airline));
        txtFlight.setText(Integer.toString(User.flightNo));
        txtTotalAmount.setText(Double.toString(User.totalPrice));

        // Confirm booking
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO check if card number for this customer exists
                if (txtCardNumber.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "This card number doesn't exist");
                    return;
                }

                // Update database to confirm booking

            }
        });
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }


}

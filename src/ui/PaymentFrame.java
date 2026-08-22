package ui;

import javax.swing.*;
import java.awt.*;

public class PaymentFrame {

    JFrame frame;
    public PaymentFrame(double amount) {

        frame = new JFrame("Payment");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(10,1));

        JLabel total = new JLabel("Total Amount : ₹" + amount);

        JTextField cardField = new JTextField();
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField cvvField = new JTextField();
        JButton payBtn = new JButton("Pay ₹" + amount);

        payBtn.addActionListener(e -> {
            if(cardField.getText().isEmpty()
                    || monthField.getText().isEmpty()
                    || yearField.getText().isEmpty()
                    || cvvField.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Fill all details");
            }
            else{
                try {
                    Long.parseLong(cardField.getText());
                    Integer.parseInt(monthField.getText());
                    Integer.parseInt(yearField.getText());
                    Integer.parseInt(cvvField.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Only numeric values are allowed!");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Payment Successful!");

                //new BillFrame();
                frame.dispose();
                //new BillFrame();
            }
        });

        frame.add(total);
        frame.add(new JLabel("Enter Card Number"));
        frame.add(cardField);
        frame.add(new JLabel("Enter Expiry Month"));

        frame.add(monthField);

        frame.add(new JLabel("Enter Expiry Year"));
        frame.add(yearField);
        frame.add(new JLabel("Enter CVV"));

        frame.add(cvvField);
        frame.add(payBtn);
        frame.setVisible(true);
    }
}
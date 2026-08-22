package ui;

import javax.swing.*;
import java.awt.*;

public class BillFrame {

    JFrame frame;

    public BillFrame(double amount) {

        frame = new JFrame("Bill Receipt");
        frame.setSize(800, 600);      // Normal size
        frame.setLocationRelativeTo(null); // Center of screen
        frame.setResizable(false);    // Optional
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JTextArea billArea = new JTextArea();

        billArea.setEditable(false);

        billArea.setFont(new Font("Monospaced", Font.PLAIN, 20));

        billArea.setMargin(new Insets(20, 30, 20, 30));

        StringBuilder bill = new StringBuilder();

        bill.append("====================================================\n\n");

        bill.append("                 ONLINE SHOPPING\n\n");

        bill.append("====================================================\n\n");

        bill.append("Purchased Products:\n\n");

        bill.append(String.format(
                        "%-10s %-25s %-15s\n",
                        "ID",
                        "PRODUCT",
                        "PRICE"
                )
        );

        bill.append("-------------------------------------------------------------\n");

        for(String row[] : ProductFrame.cartItems){
            bill.append(
                    String.format(
                            "%-10s %-25s ₹%-15s\n",
                            row[0],
                            row[1],
                            row[3]
                    )
            );
        }

        bill.append("\n-------------------------------------------------------------\n\n");

        bill.append(
                "TOTAL PAYMENT : ₹"
                        + amount
        );

        bill.append("\n\n");

        bill.append(
                "THANK YOU FOR SHOPPING!"
        );

        billArea.setText(
                bill.toString()
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        billArea
                );

        frame.add(
                scrollPane,
                BorderLayout.CENTER
        );

        frame.setVisible(true);

    }

}
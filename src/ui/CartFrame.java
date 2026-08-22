package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static ui.ProductFrame.cartItems;

public class CartFrame {

    JFrame frame;

    // PaymentFrame ke liye accessible hoga
    public static double totalPrice = 0;

    public CartFrame(){

        frame = new JFrame("Cart");

        String[] columns = {
                "ID",
                "Name",
                "Category",
                "Price"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns,0);

        // Reset every time cart opens
        totalPrice = 0;

        for(String[] item : cartItems){

            model.addRow(item);

            totalPrice += Integer.parseInt(item[3]);
        }

        JTable table = new JTable(model);

        table.setRowHeight(35);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for(int i=0; i<table.getColumnCount(); i++){

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        JLabel totalLabel =
                new JLabel(
                        "Total Price : ₹" + totalPrice
                );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        frame.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        frame.add(
                totalLabel,
                BorderLayout.SOUTH
        );

        frame.setSize(700,500);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}

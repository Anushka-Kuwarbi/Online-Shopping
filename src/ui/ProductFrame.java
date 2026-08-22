package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//import static sun.tools.jconsole.inspector.XDataViewer.dispose;

public class ProductFrame {

    public static double totalPrice;
    JFrame frame;
    JTable table;
    JTextField searchField;
    JButton searchBtn, cartBtn, refreshBtn;
    public static ArrayList<String[]> cartItems = new ArrayList<>();

    public ProductFrame() {
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            frame.dispose();
            new UserDashboard();
        });

        frame = new JFrame("Products");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                frame.dispose();
                new UserDashboard();
            }
        });
        frame.setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("PRODUCTS", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 35));
        frame.add(heading, BorderLayout.NORTH);

        // Search Panel
        JPanel topPanel = new JPanel();
        searchField = new JTextField(20);
        searchBtn = new JButton("Search");
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        frame.add(topPanel, BorderLayout.SOUTH);

        // Table

        String[] columns = {"ID", "Name", "Category", "Price", "Stock"};
        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/online_shopping",
                    "root",
                    "root@234"
            );

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM products");

            while(rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                });
            }

            con.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }

        table = new JTable(model);

        table = new JTable(model);
        // Row height increase
        table.setRowHeight(30);

// Font size increase
        table.setFont(new Font("Arial", Font.PLAIN, 16));

// Header font and height
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setPreferredSize(new Dimension(100, 35));

// Center align content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

// Center align headers
        DefaultTableCellRenderer headerRenderer =
                (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        // Buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnBack);      // Back button
        cartBtn = new JButton("Add To Cart");
        //refreshBtn = new JButton("Refresh");

        buttonPanel.add(cartBtn);
        //buttonPanel.add(refreshBtn);
        cartBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row != -1){

                String id = table.getValueAt(row,0).toString();
                String name = table.getValueAt(row,1).toString();
                String category = table.getValueAt(row,2).toString();
                String price = table.getValueAt(row,3).toString();

                cartItems.add(new String[]{
                        id, name, category, price
                        }
                );
                JOptionPane.showMessageDialog(frame, "Added To Cart");
            }
        });
        frame.add(buttonPanel, BorderLayout.PAGE_END);
        frame.setVisible(true);
    }
}
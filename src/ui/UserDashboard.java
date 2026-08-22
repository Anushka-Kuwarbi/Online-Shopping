package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserDashboard extends JFrame {

    JPanel sidePanel, topPanel, centerPanel;
    JButton dashboardBtn,
            productBtn,
            cartBtn,
            paymentBtn,
            billBtn,
            logoutBtn;

    JLabel title;
    public UserDashboard() {
        setTitle("User Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        // TOP PANEL
        topPanel = new JPanel();
        topPanel.setBackground(new Color(0,180,140));
        title = new JLabel("ONLINE SHOPPING");
        title.setForeground(Color.WHITE);

        title.setFont(new Font("Arial", Font.BOLD, 35));
        topPanel.add(title);

        add(topPanel, BorderLayout.NORTH);

        // SIDE PANEL
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(25,35,50));
        sidePanel.setPreferredSize(new Dimension(250,getHeight()));
        sidePanel.setLayout(new GridLayout(10,1,10,10));

        Font btnFont = new Font("Arial", Font.BOLD, 20);

        dashboardBtn = new JButton("Dashboard");
        productBtn = new JButton("View Products");
        cartBtn = new JButton("Cart");
        paymentBtn = new JButton("Payment");
        billBtn = new JButton("Bill");
        logoutBtn = new JButton("Logout");

        JButton[] buttons = {
                dashboardBtn,
                productBtn,
                cartBtn,
                paymentBtn,
                billBtn,
                logoutBtn
        };

        for(JButton btn : buttons){
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            btn.setBackground(new Color(45,62,80));
            btn.setForeground(Color.WHITE);
            sidePanel.add(btn);
        }

        add(sidePanel, BorderLayout.WEST);

        // CENTER PANEL
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2,3,20,20));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 2));
        centerPanel.add(createOfferCard(
                "MAKEUP SALE",
                "src/images/makeup.png"));

        centerPanel.add(createOfferCard(

                "WATCHES DISCOUNT",
                        "src/images/watch.png"
                )
        );

        centerPanel.add(createOfferCard(
                        "FASHION SALE",
                        "src/images/fashion.png"
                )
        );

        add(centerPanel, BorderLayout.CENTER);
        // BUTTON ACTIONS
        productBtn.addActionListener(e -> {
            new ProductFrame();
        });

        cartBtn.addActionListener((ActionEvent e) -> {
            new CartFrame();
        });

        paymentBtn.addActionListener(e -> {
            double totalAmount = CartFrame.totalPrice;

            if(totalAmount <= 0){
                JOptionPane.showMessageDialog(null, "Cart is Empty!");
            }

            else{
                new PaymentFrame(totalAmount);
            }
        });
        billBtn.addActionListener(e -> {
            if(CartFrame.totalPrice <= 0){
                JOptionPane.showMessageDialog(null, "Cart is Empty!");
                return;
            }

            String email = JOptionPane.showInputDialog(
                            null,
                            "Enter Customer Email:"
                    );
            if(email == null){
                return;
            }

            if(email.trim().isEmpty()){
                JOptionPane.showMessageDialog(
                        null,
                        "Email Required"
                );
                return;
            }

            new BillFrame(CartFrame.totalPrice);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        setVisible(true);
    }
    private JPanel createOfferCard(String title, String imagePath){

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createLineBorder(
                        Color.LIGHT_GRAY,
                        2));

        JLabel heading = new JLabel(title);

        heading.setFont(new Font("Arial", Font.BOLD, 22));

        heading.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon img = new ImageIcon(imagePath);

        Image scaled = img.getImage().getScaledInstance(
                350,
                230,
                Image.SCALE_SMOOTH
        );

        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        card.add(heading, BorderLayout.NORTH);
        card.add(imageLabel, BorderLayout.CENTER);
        return card;
    }
}

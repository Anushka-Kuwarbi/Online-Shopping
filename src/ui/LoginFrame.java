package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {

    JPanel leftPanel, rightPanel;
    JLabel title, emailLabel, passwordLabel, welcomeLabel;
    JTextField emailField;
    JPasswordField passwordField;
    JButton loginButton;
    //JRadioButton userBtn, adminBtn;
    ButtonGroup group;

    public LoginFrame() {
        // FRAME SETTINGS
        setTitle("Online Shopping System");
        // Full Screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));
        // ================= LEFT PANEL =================
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(173,216,230));
        leftPanel.setLayout(null);

        // IMAGE CODE START
        ImageIcon img = new ImageIcon(getClass().getResource("/images/shopping.png"));
        Image scaledImage = img.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);
        iconLabel.setBounds(180,40,150,150);
        leftPanel.add(iconLabel);

        // IMAGE CODE END
        // Welcome Text
        welcomeLabel = new JLabel(
                "<html><center>WELCOME<br>TO<br>ONLINE<br>SHOPPING</center></html>"
        );
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 45));
        welcomeLabel.setBounds(120,150,400,400);
        welcomeLabel.setForeground(Color.WHITE);

        // Shopping Icon
        JLabel icon = new JLabel("🛒");
        icon.setFont(new Font("Arial", Font.PLAIN, 100));
        icon.setBounds(180,40,200,100);
        leftPanel.add(icon);
        leftPanel.add(welcomeLabel);

        // ================= RIGHT PANEL =================
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0,102,204));
        rightPanel.setLayout(null);

        // LOGIN TITLE
        title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(300,80,200,50);

        // EMAIL LABEL
        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(150,180,100,30);

        // EMAIL FIELD
        emailField = new JTextField();
        emailField.setBounds(150,220,400,40);
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));

        // PASSWORD LABEL
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(150,300,150,30);

        // PASSWORD FIELD
        passwordField = new JPasswordField();
        passwordField.setBounds(150,340,400,40);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));

        // RADIO BUTTONS
//        userBtn = new JRadioButton("User");
//        adminBtn = new JRadioButton("Admin");
//
//        userBtn.setBounds(150,420,100,30);
//        adminBtn.setBounds(300,420,100,30);
//
//        userBtn.setBackground(new Color(0,102,204));
//        adminBtn.setBackground(new Color(0,102,204));
//
//        userBtn.setForeground(Color.WHITE);
//        adminBtn.setForeground(Color.WHITE);
//
//        userBtn.setFont(new Font("Arial", Font.BOLD, 18));
//        adminBtn.setFont(new Font("Arial", Font.BOLD, 18));
//
//        group = new ButtonGroup();
//        group.add(userBtn);
//        group.add(adminBtn);

        // LOGIN BUTTON
        loginButton = new JButton("Login");
        //loginButton.setBounds(180,520,300,50);
//        loginButton.setBounds(180,430,300,50);
        loginButton.setBounds(220,430,220,40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 22));
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(0,102,204));

        loginButton.addActionListener(e -> {

            String email = emailField.getText();
            String password = String.valueOf(passwordField.getPassword());

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/online_shopping",
                        "root",
                        "root@234"
                );

                String query =
                        "SELECT * FROM users WHERE email=? AND password=?";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                Component frame = null;
                if(rs.next()) {

                    JOptionPane.showMessageDialog(frame,
                            "Login Successful");

                    new UserDashboard();
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Invalid Email or Password");

                }

                con.close();

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        // ADD COMPONENTS
        rightPanel.add(title);
        rightPanel.add(emailLabel);
        rightPanel.add(emailField);
        rightPanel.add(passwordLabel);
        rightPanel.add(passwordField);
//        rightPanel.add(userBtn);
//        rightPanel.add(adminBtn);
        rightPanel.add(loginButton);

        // ADD PANELS TO FRAME
        add(leftPanel);
        add(rightPanel);
        setVisible(true);
    }
}
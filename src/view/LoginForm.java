package view;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JButton accediButton;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(new JLabel("Username: "));
        usernameField = new JTextField();
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Password: "));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        accediButton = new JButton("Accedi");
        loginPanel.add(accediButton);

        add(loginPanel, BorderLayout.CENTER);
    }

    public JButton getAccediButton() {
        return accediButton;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}

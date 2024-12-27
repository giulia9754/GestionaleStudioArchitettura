package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameAmministratore extends JFrame {
    private CardLayout cardLayout; // Gestisce i pannelli
    private JPanel mainPanel; // Pannello principale con CardLayout

    public MainFrameAmministratore() {
        // Impostazioni del Frame
        setTitle("BENVENUTO AMMINISTRATORE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menu laterale
        JPanel menuPanel = createMenuPanel();

        // Pannello principale (CardLayout)
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Pannelli
        JPanel homePanel = createHomePanelAmministratore();
        JPanel employeesPanel = createViewPanel("Gestione Dipendenti");
        JPanel projectsPanel = createViewPanel("Gestione Progetti");
        JPanel newProjectPanel = createViewPanel("Nuovo Progetto");
        JPanel invoicesPanel = createViewPanel("Gestione Fatture");

        // Aggiunta dei pannelli al CardLayout
        mainPanel.add(homePanel, "Home");
        mainPanel.add(employeesPanel, "Dipendenti");
        mainPanel.add(projectsPanel, "Gestione Progetti");
        mainPanel.add(newProjectPanel, "Nuovo Progetto");
        mainPanel.add(invoicesPanel, "Fatture");

        // Aggiunta dei componenti al frame
        add(menuPanel, BorderLayout.WEST); // Menu laterale
        add(mainPanel, BorderLayout.CENTER); // Contenuto principale

        // Mostra il pannello iniziale
        cardLayout.show(mainPanel, "Home");

        // Aggiungiamo il pulsante di logout con funzionalità
        menuPanel.add(Box.createVerticalStrut(20));
        JButton logoutButton = new JButton("Esci");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainFrameAmministratore.this, "Ritorno alla schermata di login...");
                dispose(); // Chiude la finestra corrente
                new LoginForm().setVisible(true); // Mostra la finestra di login
            }
        });
        menuPanel.add(logoutButton);
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(200, 800)); // Larghezza del menu
        menuPanel.setBackground(Color.LIGHT_GRAY);

        // Pulsanti del menu
        JButton homeButton = new JButton("Home");
        JButton employeesButton = new JButton("Dipendenti");
        JButton manageProjectsButton = new JButton("Gestione Progetti");
        JButton newProjectButton = new JButton("Nuovo Progetto");
        JButton invoicesButton = new JButton("Fatture");

        // Listener per cambiare pannello
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        employeesButton.addActionListener(e -> cardLayout.show(mainPanel, "Dipendenti"));
        manageProjectsButton.addActionListener(e -> cardLayout.show(mainPanel, "Gestione Progetti"));
        newProjectButton.addActionListener(e -> cardLayout.show(mainPanel, "Nuovo Progetto"));
        invoicesButton.addActionListener(e -> cardLayout.show(mainPanel, "Fatture"));

        // Aggiunta pulsanti al menu
        menuPanel.add(Box.createVerticalStrut(20)); // Spaziatura
        menuPanel.add(homeButton);
        menuPanel.add(Box.createVerticalStrut(20)); 
        menuPanel.add(employeesButton);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(manageProjectsButton);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(newProjectButton);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(invoicesButton);

        return menuPanel;
    }

    private JPanel createHomePanelAmministratore() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Benvenuto, Amministratore!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(welcomeLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createViewPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
}

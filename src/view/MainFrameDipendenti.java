package view;

import model.Progetti;
import model.ProgettiDAO;
import model.Dipendenti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainFrameDipendenti extends JFrame {
	private ProgettiDAO progettiDAO;
	private Progetti progetti;
    private CardLayout cardLayout; // Gestisce i pannelli
    private JPanel mainPanel; // Pannello principale con CardLayout
    private JButton homeButton;
    private JButton myPersonalButton;
    private JButton progettiAssegnatiButton;
    private JButton archivioButton;
    private JButton logoutButton;
    private JButton searchButton;
    private JButton modificaButton;
    private JButton visualizzaButton;
    private File filePath;
    
    public MainFrameDipendenti(Dipendenti dipendenti, ProgettiDAO progettiDAO) throws SQLException {
        // Impostazioni del Frame
        setTitle("BENVENUTO " + dipendenti.getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menu laterale
        JPanel menuPanel = createMenuPanel();

        // Pannello principale (CardLayout)
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(255, 255, 255));

        // Pannelli
        JPanel homePanel = createHomePanelDipendente();
        JPanel myPersonalPanel = createProfilePanel(dipendenti);
        JPanel progettiAssegnatiPanel = createProgettiAssegnatiPanel(progettiDAO);
        JPanel archivioPanel = createArchivioPanel(progettiDAO);

        // Aggiunta dei pannelli al CardLayout
        mainPanel.add(homePanel, "Home");
        mainPanel.add(myPersonalPanel, "Profilo");
        mainPanel.add(progettiAssegnatiPanel, "Progetti Assegnati");
        mainPanel.add(archivioPanel, "Archivio");

        // Aggiunta dei componenti al frame
        add(menuPanel, BorderLayout.WEST); // Menu laterale
        add(mainPanel, BorderLayout.CENTER); // Contenuto principale

        // Mostra il pannello iniziale
        cardLayout.show(mainPanel, "Home");
    }

    // Creazione del pannello Menu laterale
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(200, 800));
        menuPanel.setBackground(new Color(0, 128, 128));

        // Etichetta del menu
        JLabel menuLabel = new JLabel("MENU", SwingConstants.CENTER);
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Allineamento centrale
        menuPanel.add(menuLabel);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(menuLabel);

        // Pulsanti del menu
         homeButton = new JButton("Home");
         myPersonalButton = new JButton("Profilo");
         progettiAssegnatiButton = new JButton("Progetti Assegnati");
         archivioButton = new JButton("Archivio");
        
        // Listener per cambiare pannello
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        myPersonalButton.addActionListener(e -> cardLayout.show(mainPanel, "Profilo"));     
        progettiAssegnatiButton.addActionListener(e -> cardLayout.show(mainPanel, "Progetti Assegnati"));
        archivioButton.addActionListener(e -> cardLayout.show(mainPanel, "Archivio"));
        
        // Aggiunta dei pulsanti al menu
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(homeButton);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(myPersonalButton);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(progettiAssegnatiButton);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(archivioButton);


     // Logout
        logoutButton = new JButton("Esci");
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Sei sicuro di voler uscire?", 
                "Conferma logout", 
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Ritorno alla schermata di login...");
                setVisible(false); // Chiude la finestra corrente
                new LoginForm().setVisible(true); // Mostra la finestra di login
            }
        });
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(logoutButton);
        return menuPanel;
    }

    // Creazione del pannello Home
    private JPanel createHomePanelDipendente() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Benvenuto, Dipendente!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(welcomeLabel, BorderLayout.CENTER);
        return panel;
    }

    // Creazione del pannello Profilo Dipendente
    private JPanel createProfilePanel(Dipendenti dipendente) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10)); // Layout a griglia
        // Campi di testo con dati del dipendente
        panel.add(new JLabel("ID: "));
        JTextField id = new JTextField(String.valueOf(dipendente.getId_dipendente()));
        id.setEditable(false);
        panel.add(id);
        panel.add(new JLabel("Nome: "));
        JTextField nome = new JTextField(dipendente.getNome());
        nome.setEditable(false);
        panel.add(nome);
        panel.add(new JLabel("Cognome: "));
        JTextField cognome = new JTextField(dipendente.getCognome());
        cognome.setEditable(false);
        panel.add(cognome);
        panel.add(new JLabel("Data di Nascita: "));
        JTextField dataNascita = new JTextField(dipendente.getDataNascita());
        dataNascita.setEditable(false);
        panel.add(dataNascita);
        panel.add(new JLabel("Partita IVA: "));
        JTextField partitaIVA = new JTextField(dipendente.getPartitaIVA());
        partitaIVA.setEditable(false);
        panel.add(partitaIVA);
        return panel;
    }

    // Creazione del pannello Progetti con tabella
    private JPanel createProgettiAssegnatiPanel(ProgettiDAO progettiDAO) throws SQLException {
        JPanel panel = new JPanel();
        //JPanel searchPanel = new JPanel();
        // Barra di ricerca
        JTextField searchField = new JTextField(50);
        searchButton = new JButton("Cerca");
        modificaButton = new JButton("Modifica");
        //panel.add(searchPanel);
        panel.add(new JLabel("Cerca progetto: "));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(modificaButton);
        // Tabella dei progetti
        String[] columnNames = {"ID", "Nome", "Tipologia", "Data Inizio", "Data Scadenza", "Stato"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        List<Progetti> progettiList = progettiDAO.getAllProgetti();
        for (Progetti progetti : progettiList) {
        	tableModel.addRow(new Object[]{
        	progetti.getIdProgetto(),
        	progetti.getNomeProgetto(),
        	progetti.getTipologia(),
        	progetti.getDataInizio(),
        	progetti.getDataScadenza(),
        	progetti.getStato(),
        });
        }	
        JTable table = new JTable(tableModel);
        table.setEnabled(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(
        		new Dimension(800,600)
        		);
        panel.add(tableScrollPane);        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//leggo input 
            	String testo = searchField.getText().toLowerCase();        	
            	//filtri i dati
                DefaultTableModel filteredModel = new DefaultTableModel(columnNames, 0);
                for (int i=0; i<tableModel.getRowCount();i++) {
                	if (
                			tableModel.getValueAt(i, 0).toString().toLowerCase().contains(testo)||
                			tableModel.getValueAt(i, 1).toString().toLowerCase().contains(testo)||
                			tableModel.getValueAt(i, 2).toString().toLowerCase().contains(testo)||
                			tableModel.getValueAt(i, 3).toString().toLowerCase().contains(testo)||
                			tableModel.getValueAt(i, 4).toString().toLowerCase().contains(testo)||
                			tableModel.getValueAt(i, 5).toString().toLowerCase().contains(testo)

                		) {
                		filteredModel.addRow(new Object[] {
                    			tableModel.getValueAt(i,0),	
                    			tableModel.getValueAt(i,1),	
                    			tableModel.getValueAt(i,2),	
                    			tableModel.getValueAt(i,3),	
                    			tableModel.getValueAt(i,4),	
                    			tableModel.getValueAt(i,5)
                		});
                	}
                }
            	//aggiorno la lista
                table.setModel(filteredModel);
            }
        });
        modificaButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String selectedId = table.getValueAt(selectedRow, 0).toString();
                Progetti selectedProgetto = null;
                try {
                    selectedProgetto = progettiDAO.getProgettoById(Integer.parseInt(selectedId));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Errore durante il recupero del progetto: " + ex.getMessage());
                    return;
                }
                // Avvolgi selectedProgetto in un contenitore
                final Progetti[] selectedProgettoWrapper = {selectedProgetto};
                if (selectedProgettoWrapper[0] != null) {
                    JFrame modificaFrame = new JFrame("Modifica Progetto");
                    modificaFrame.setSize(400, 400);
                    modificaFrame.setLayout(new GridLayout(6, 2));
                    JTextField statoField = new JTextField(selectedProgettoWrapper[0].getStato());
                    modificaFrame.add(new JLabel("Stato:"));
                    modificaFrame.add(statoField);
                    JButton confermaButton = new JButton("Conferma");
                    JButton annullaButton = new JButton("Annulla");
                    modificaFrame.add(confermaButton);
                    modificaFrame.add(annullaButton);
                    confermaButton.addActionListener(confirmEvent -> {
                        int confirm = JOptionPane.showConfirmDialog(
                                modificaFrame,
                                "Confermi di voler apportare queste modifiche?",
                                "Conferma Modifiche",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (confirm == JOptionPane.YES_OPTION) {
                            selectedProgettoWrapper[0].setStato(statoField.getText());
                            try {
                                progettiDAO.updateProgetto(selectedProgettoWrapper[0]);
                                JOptionPane.showMessageDialog(modificaFrame, "Modifiche salvate con successo!");
                                modificaFrame.dispose();
                                // Aggiorna la tabella
                                table.setValueAt(selectedProgettoWrapper[0].getStato(), selectedRow, 5);
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(modificaFrame, "Errore durante il salvataggio: " + ex.getMessage());
                            }
                        }
                    });
                    
                    annullaButton.addActionListener(cancelEvent -> modificaFrame.dispose());
                    modificaFrame.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleziona un progetto per modificarlo.");
            }
        });
        return panel;
    }
       
    
    //Creazione del pannello Archivia con Tabella
    private JPanel createArchivioPanel(ProgettiDAO progettiDAO) throws SQLException  {
    	 JPanel panel = new JPanel();
         //JPanel searchPanel = new JPanel();
         // Barra di ricerca
         JTextField searchField = new JTextField(50);
         JButton searchButton = new JButton("Cerca");
         JButton visualizzaButton = new JButton("Visualizza");
         //panel.add(searchPanel);
         panel.add(new JLabel("Cerca progetto: "));
         panel.add(searchField);
         panel.add(searchButton);
         panel.add(visualizzaButton);
         // Tabella dei progetti
         String[] columnNames = {"ID", "Nome", "Tipologia", "Data Inizio", "Data Scadenza", "Stato"};
         DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
         List<Progetti> progettiList = progettiDAO.getProgettiArchiviati();
         for (Progetti progetti : progettiList) {
         	tableModel.addRow(new Object[]{
         	progetti.getIdProgetto(),
         	progetti.getNomeProgetto(),
         	progetti.getTipologia(),
         	progetti.getDataInizio(),
         	progetti.getDataScadenza(),
         	progetti.getStato(),
         });
         }	
         JTable table = new JTable(tableModel);
         JScrollPane tableScrollPane = new JScrollPane(table);
         tableScrollPane.setPreferredSize(
         		new Dimension(800,600)
         		);
         panel.add(tableScrollPane);
         searchButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
             	//leggo input 
             	String testo = searchField.getText().toLowerCase();        	
             	//filtri i dati
                 DefaultTableModel filteredModel = new DefaultTableModel(columnNames, 0);
                 for (int i=0; i<tableModel.getRowCount();i++) {
                 	if (
                 			tableModel.getValueAt(i, 0).toString().toLowerCase().contains(testo)||
                 			tableModel.getValueAt(i, 1).toString().toLowerCase().contains(testo)||
                 			tableModel.getValueAt(i, 2).toString().toLowerCase().contains(testo)||
                 			tableModel.getValueAt(i, 3).toString().toLowerCase().contains(testo)||
                 			tableModel.getValueAt(i, 4).toString().toLowerCase().contains(testo)||
                 			tableModel.getValueAt(i, 5).toString().toLowerCase().contains(testo)

                 		) {
                 		filteredModel.addRow(new Object[] {
                     			tableModel.getValueAt(i,0),	
                     			tableModel.getValueAt(i,1),	
                     			tableModel.getValueAt(i,2),	
                     			tableModel.getValueAt(i,3),	
                     			tableModel.getValueAt(i,4),	
                     			tableModel.getValueAt(i,5)
                 		});
                 	}
                 }
             	//aggiorno la lista
                 table.setModel(filteredModel);
             }
         });
         visualizzaButton.addActionListener(e -> {
             int selectedRow = table.getSelectedRow();
             if (selectedRow != -1) {
                 String selectedId = table.getValueAt(selectedRow, 0).toString();
                 Progetti selectedProgetto = null;
                 try {
                     selectedProgetto = progettiDAO.getProgettoById(Integer.parseInt(selectedId));
                 } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(this, "Errore durante il recupero del progetto: " + ex.getMessage());
                     return;
                 }

                 if (selectedProgetto != null) {
                     String filePath = selectedProgetto.getFilePath(); // Supponiamo che il file path sia un campo della classe Progetti
                     openFile(filePath);
                 } else {
                     JOptionPane.showMessageDialog(this, "Seleziona un progetto per visualizzarlo.");
                 }
             } else {
                 JOptionPane.showMessageDialog(this, "Seleziona un progetto per visualizzarlo.");
             }
         });

         return panel;
     }

     // Metodo per aprire il file dal sistema
     private void openFile(String filePath) {
         File file = new File(filePath);
         if (file.exists()) {
             Desktop desktop = Desktop.getDesktop();
             try {
                 desktop.open(file);
             } catch (IOException ex) {
                 JOptionPane.showMessageDialog(this, "Errore nell'apertura del file: " + ex.getMessage());
             }
         } else {
             JOptionPane.showMessageDialog(this, "Il file non esiste o è stato rimosso.");
         }
     }
    
}
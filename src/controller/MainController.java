package controller;

import model.UtenteForm;
import model.Dipendenti;
import model.ProgettiDAO;
import model.Progetti;

import view.LoginForm;
import view.MainFrameAmministratore;
import view.MainFrameDipendenti;

import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
	private Dipendenti dipendenti;
	private Progetti progetti;
	private ProgettiDAO progettiDAO;
    private LoginForm loginForm;
    private MainFrameAmministratore mainFrameAmministratore;
    private MainFrameDipendenti mainFrameDipendenti;
    private UtenteForm utenteForm;

    // Costruttore
    public MainController() throws SQLException {
        utenteForm = new UtenteForm();
        loginForm = new LoginForm();
        mainFrameAmministratore = new MainFrameAmministratore();
        
        // Gestisce il click sul pulsante "Accedi"
        loginForm.getAccediButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    autenticaUtente();  // Controlla le credenziali
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore durante l'autenticazione: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginForm.setVisible(true);
    } 
    
    
    

    private void autenticaUtente() throws SQLException {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        Dipendenti dipendenti = utenteForm.autenticazione(username, password);// Metodo di autenticazione del database
        progettiDAO = new ProgettiDAO();
        System.out.println(dipendenti);
        if (username != null && password != null) {
            JOptionPane.showMessageDialog(loginForm, "Accesso riuscito!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            loginForm.dispose(); // Chiude la finestra di login  
            // Apri la finestra basata sul ruolo
            switch (dipendenti.getRuolo()) {
                case "amministratore":
                    // Apri MainFrame amministratore
                    mainFrameAmministratore.setVisible(true);
                    break;
                case "dipendente":
                	// Apri MainFrame dipendente
                	mainFrameDipendenti = new MainFrameDipendenti(dipendenti, progettiDAO);
                	mainFrameDipendenti.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(loginForm, "Ruolo non riconosciuto", "Errore", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(loginForm, "Nome utente o password non validi.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        
        
        
        
        
        
        
        
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

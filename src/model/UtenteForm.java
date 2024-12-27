package model;

import model.Dipendenti;

import java.sql.*;
import java.util.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UtenteForm extends JFrame{
	
		// Metodo per effettuare l'autenticazione
		public Dipendenti autenticazione(String username, String password) throws SQLException {
		    Dipendenti dipendenti = null;
		    String query = "SELECT * FROM dipendenti WHERE username = ? AND password = ?";
		    try (
		        Connection conn = DatabaseConnection.getConnection();
		        PreparedStatement pstmt = conn.prepareStatement(query)
		    ) {
		        pstmt.setString(1, username);
		        pstmt.setString(2, password);
		        ResultSet rs = pstmt.executeQuery();
		        if (rs.next()) {
		            int id = rs.getInt("id_dipendente");
		            String ruolo = rs.getString("ruolo");
		            String nome = rs.getString("nome");
		            String cognome = rs.getString("cognome");
		            String dataDiNascita = rs.getString("data_nascita");
		            String partitaIva = rs.getString("partita_iva");

		            dipendenti = new Dipendenti(id, username, password, ruolo, nome, cognome, dataDiNascita, partitaIva); // Creazione utente con ruoli
		        }
		    } catch (SQLException e) {
		        throw new SQLException("Errore durante l'autenticazione", e);
		    }
		    return dipendenti;
		}

		
		
	}

	

	
	


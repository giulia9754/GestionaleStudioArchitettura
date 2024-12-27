package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;//di formattare la data da noi preferita formattazione delle date
import java.time.temporal.ChronoUnit;//per fare calcoli sulle date
import java.time.Period; // rappresenta un intervanllo in anni, mesi e giorni



public class ProgettiDAO {
    //Metodo per visualizzare la lista dei progetti    
    public List<Progetti> getAllProgetti()throws SQLException{
    	//Lista per memorizzare i progetti
    	List<Progetti> progetti = new ArrayList<>();
    	//query SQL per selezionare tutti i progetti
   	    String query = "SELECT * FROM progetti";
   	try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
   			){
   		while(rs.next()) {
   			progetti.add(new Progetti
   					(
   					rs.getInt("id_progetto"),
   					rs.getString("nome_progetto"),
   					rs.getString("tipologia"),
   					rs.getString("data_inizio"),
   					rs.getString("data_scadenza"),
   					rs.getString("stato"),
   					rs.getInt("id_dipendente")
   					));
   		 }
   	 }  		
   	return progetti;
   }
   
    public List<Progetti> searchProgetti(String testo) throws SQLException{
    	//Lista per memorizzare i progetti
    	List<Progetti> progetti = new ArrayList<>();
    	//query SQL per selezionare tutti i progetti 
   	    String query = "SELECT * FROM progetti WHERE nome_progetto like '%" + testo + "%'";
   	try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
   			){
   		while(rs.next()) {
   			progetti.add(new Progetti
   					(
   					rs.getInt("id_progetto"),
   					rs.getString("nome_progetto"),
   					rs.getString("tipologia"),
   					rs.getString("data_inizio"),
   					rs.getString("data_scadenza"),
   					rs.getString("stato"),
   					rs.getInt("id_dipendente")
   					));
   		 }
   	 }  		
   	return progetti;
   }
    
   // Metodo per aggiungere un nuovo progetto al database
    public void addProgetti(Progetti progetti)throws SQLException{
    	String query = "INSERT INTO progetti(nome_progetto,tipologia,data_inizio,data_scadenza,stato) VALUES(?,?,?,?,?)";    	
    	//esecuzione della query
    	try(
    			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
    			PreparedStatement pstmt = conn.prepareStatement(query);
    			){
    		//imposto i valori dei parametri
    		pstmt.setString(1, progetti.getNomeProgetto());
    		pstmt.setString(2, progetti.getTipologia());
    		pstmt.setString(3, progetti.getDataInizio());
    		pstmt.setString(4, progetti.getDataScadenza());
    		pstmt.setString(5, progetti.getStato());
    		
    		pstmt.executeUpdate();    		
    	}
    	
    }
    
    // Metodo per modificare un progetto     
    public void editProgetti(Progetti progetto)throws SQLException{
    	String query = "UPDATE progetti SET nome_progetto =? , tipologia =? , data_inizio = ? , data_scadenza = ? , stato = ?";		
    	//esecuzione della query
    	try(
    			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
    			PreparedStatement pstmt = conn.prepareStatement(query);
    			){
    		pstmt.setString(1, progetto.getNomeProgetto());
    		pstmt.setString(2, progetto.getTipologia());
    		pstmt.setString(3, progetto.getDataInizio());
    		pstmt.setString(4, progetto.getDataScadenza());
    		pstmt.setString(5, progetto.getStato());
    		
    		pstmt.executeUpdate();
    	}
    }
    
    //Metodo per modificare un progetto versione2
	public void updateProgetto(Progetti selectedProgetto) throws SQLException{
    	String query = "UPDATE progetti SET nome_progetto =? , tipologia =? , data_inizio = ? , data_scadenza = ? , stato = ?";		
    	//esecuzione della query
    	try(
    			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
    			PreparedStatement pstmt = conn.prepareStatement(query);
    			){
    		pstmt.setString(1, selectedProgetto.getNomeProgetto());
    		pstmt.setString(2, selectedProgetto.getTipologia());
    		pstmt.setString(3, selectedProgetto.getDataInizio());
    		pstmt.setString(4, selectedProgetto.getDataScadenza());
    		pstmt.setString(5, selectedProgetto.getStato());    		
    		pstmt.executeUpdate();
    	}
	}

    
 // Metodo per eliminare un progetto 	    
	public void deleteProgetti(int id) throws SQLException {
	    String query = "DELETE FROM progetti WHERE id = ?";	    
	    try (
	    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
	        PreparedStatement pstmt = conn.prepareStatement(query);
	    ) {        
	        pstmt.setInt(1, id);
	        pstmt.executeUpdate();
	    }
	}
	
    // Filtro di ricerca per ID
    public Progetti getProgettoById(int idProgetto) throws SQLException {
        Progetti progetti = null; // Variabile per memorizzare il progetto trovato
        String query = "SELECT * FROM progetti WHERE id_progetto = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idProgetto); // Imposta il parametro ID nella query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // Se viene trovato un progetto con l'ID specificato
                    progetti = new Progetti(
                        rs.getInt("id_progetto"),
                        rs.getString("nome_progetto"),
                        rs.getString("tipologia"),
                        rs.getString("data_inizio"),
                        rs.getString("data_scadenza"),
                        rs.getString("stato"),
                        rs.getInt("id_dipendente")
                    );
                }
            }
        }

        return progetti; // Restituisce il progetto trovato o null se non esiste
    }
    
    //metodo per ricerca progetti archiviati
    public List<Progetti> getProgettiArchiviati() throws SQLException{
    	List<Progetti> progetti = new ArrayList<>();
    	String stato = "Archiviato";
    	String query = "SELECT * FROM progetti WHERE stato = ? ";
    	try {
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, stato);
    		ResultSet rs = pstmt.executeQuery();
    		while(rs.next()) {    			
    			progetti.add(new Progetti(
    					rs.getInt("id_progetto"),
                        rs.getString("nome_progetto"),
                        rs.getString("tipologia"),
                        rs.getString("data_inizio"),
                        rs.getString("data_scadenza"),
                        rs.getString("stato")
    					));
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}    
    	return progetti;
    }
        
    //metodo per calcolare la differenza tra le due date
    public class DateDifferenceCalculator {
        // Metodo per calcolare la differenza tra due date
        public static int calcolaDifferenzaGiorni(String dataInizio, String dataScadenza) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adatta il formato alla tua stringa
            LocalDate startDate = LocalDate.parse(dataInizio, formatter);
            LocalDate endDate = LocalDate.parse(dataScadenza, formatter);
            // Calcolo della differenza in giorni
            return (int)ChronoUnit.DAYS.between(startDate, endDate);
        }

        // Metodo per calcolare la differenza in anni, mesi e giorni
        public static String calcolaDifferenzaCompleta(String dataInizio, String dataScadenza) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(dataInizio, formatter);
            LocalDate endDate = LocalDate.parse(dataScadenza, formatter);

            // Calcolo della differenza
            int anni = (int)ChronoUnit.YEARS.between(startDate, endDate);
            startDate = startDate.plusYears(anni);

            int mesi = (int)ChronoUnit.MONTHS.between(startDate, endDate);
            startDate = startDate.plusMonths(mesi);

            int giorni = (int) ChronoUnit.DAYS.between(startDate, endDate);

            return anni + " anni, " + mesi + " mesi, " + giorni + " giorni";
        }
    }
    
    
	//metodo per caricare un file
    public void uploadFile(String filePath) {
        String query = "INSERT INTO file_storage (file_name, file_data) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
             PreparedStatement pstmt = conn.prepareStatement(query);
             FileInputStream fis = new FileInputStream(new File(filePath))) {
            // Imposta il nome del file e i dati binari
            File file = new File(filePath);
            pstmt.setString(1, file.getName());
            pstmt.setBinaryStream(2, fis, (int) file.length());
            // Esegui l'operazione
            int rowsInserted = pstmt.executeUpdate();
            System.out.println("File caricato con successo. Righe inserite: " + rowsInserted);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
	
    //metodo per scaricare un documento
    public void downloadFile(int fileId, String outputFilePath) {
        String query = "SELECT file_name, file_data FROM file_storage WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Imposta il parametro per la query
            pstmt.setInt(1, fileId);
            // Esegui la query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String fileName = rs.getString("file_name");
                    byte[] fileData = rs.getBytes("file_data");
                    // Salva il file in locale
                    try (FileOutputStream fos = new FileOutputStream(outputFilePath + fileName)) {
                        fos.write(fileData);
                        System.out.println("File scaricato con successo: " + outputFilePath + fileName);
                    }
                } else {
                    System.out.println("Nessun file trovato con l'ID specificato.");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //metodo per eliminare un file caricato
    public void deleteFile(int fileId) {
        String query = "DELETE FROM file_storage WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studio_architettura","root","1234@l8q");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Imposta il parametro per la query
            pstmt.setInt(1, fileId);
            // Esegue la query di eliminazione
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("File eliminato con successo. Righe interessate: " + rowsDeleted);
            } else {
                System.out.println("Nessun file trovato con l'ID specificato.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	
	
	
	
	
	

}


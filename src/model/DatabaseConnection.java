package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	//Connessione utente Amministratore
	//URL
	private static final String URL = "jdbc:mysql://localhost:3306/studio_architettura";
	//USER
	private static final String USER = "root";
	//PASSWORD
	private static final String PASSWORD = "1234@l8q";
	
	//Creazione del metodo per ottenere una connessione al database
	public static Connection getConnection() throws SQLException{ 
		//rotorna la connessione
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	//Connessione utente reparto front Office
	private static final String URL_FRONT = "jdbc:mysql://localhost:3306/studio_architettura";
	private static final String USER_FRONT = "frontUser";
	private static final String PASSWORD_FRONT = "frontPassword";

	public static Connection getConnectionFront() throws SQLException{
		return DriverManager.getConnection(URL_FRONT, USER_FRONT, PASSWORD_FRONT);
	}
	
}
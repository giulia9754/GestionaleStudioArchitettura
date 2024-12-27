package main;

import java.sql.SQLException;

import controller.MainController;
//classe principale per avviare l'applicazione
public class Main {

	public static void main(String[] args) throws SQLException{
		new MainController();//si richiama la classe controller principale che gestisce l'avvio dell'applicativo

	}

}

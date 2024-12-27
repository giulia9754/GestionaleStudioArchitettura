package model;

import java.sql.*;
import java.util.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Utente extends JFrame{
	//Attributi
	private int id;
	private String username;
	private String password;
	private String ruolo;
		
	//Costruttore
	public Utente(int id, String nomeUtente, String password, String ruolo) {
		this.id = id;
		this.username = nomeUtente;
		this.password = password;
		this.ruolo = ruolo;
	}
	
	//Metodi Getter e Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
	
	
}

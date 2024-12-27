package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period; // rappresenta un intervanllo in anni, mesi e giorni


public class Progetti {
	//attributi
	private Progetti Progetto;
	private int idProgetto;
    private String nomeProgetto;
    private String tipologia;
    private String dataInizio;
    private String dataScadenza;
    private String stato;
    private int idDipendente;

    //Costruttore
    public Progetti(int idProgetto, String nomeProgetto, String tipologia, String dataInizio, String dataScadenza, String stato, int idDipendente){
        this.idProgetto = idProgetto;
        this.nomeProgetto = nomeProgetto;
        this.tipologia = tipologia;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        this.stato = stato;
        this.idDipendente = idDipendente;
    }
    
    //Costruttore
    public Progetti(int idProgetto, String nomeProgetto, String tipologia, String dataInizio, String dataScadenza, String stato){
        this.idProgetto = idProgetto;
        this.nomeProgetto = nomeProgetto;
        this.tipologia = tipologia;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        this.stato = stato;
    }

    //Costruttore
    public Progetti(Progetti progetto){
        this.idProgetto = idProgetto;
        this.nomeProgetto = nomeProgetto;
        this.tipologia = tipologia;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        this.stato = stato;
    }


    //Metodi Getter
    public int getIdProgetto() {
        return idProgetto;
    }
    
    public String getNomeProgetto() {
        return nomeProgetto;
    }
    
    public String getTipologia() {
        return tipologia;
    }
    
    public String getDataInizio() {
        return dataInizio;
    }
    
    public String getDataScadenza() {
        return dataScadenza;
    }
    
    public String getStato() {
        return stato;
    }
    
    public int getIdDipendente() {
    	return idDipendente;
    }
    
    public String getFilePath() {
    	return null;
    }

    //Metodo Setter
    
    public void setIdProgetto(int idProgetto) {
        this.idProgetto = idProgetto;
    }

    public void setNomeProgetto(String nomeProgetto) {
        this.nomeProgetto = nomeProgetto;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
    
    public void setIdDipendente(int idDipendente) {
    	this.idDipendente = idDipendente;
    }
    
    
    // Metodo toString() per rappresentazione leggibile
    @Override
    public String toString() {
        return "Progetto{" +
                "id Progetto=" + idProgetto +
                ", nome Progetto='" + nomeProgetto + '\'' +
                ", tipologia='" + tipologia + '\'' +
                ", data Inizio=" + dataInizio +
                ", data Scadenza=" + dataScadenza +
                ", stato='" + stato + '\'' +
                ", id Dipendente='" + idDipendente + '\'' +
                '}';
    }
    

}


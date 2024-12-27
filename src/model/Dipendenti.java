package model;

public class Dipendenti {
	private int id_dipendente;
	private String username;
	private String password;
	private String ruolo;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String partitaIVA;
	private int id_progetto;
	
	//Costruttore
		public Dipendenti(int id_dipendente, String username, String password, String ruolo, String nome, String cognome, String dataNascita, String partitaIVA) {
			this.id_dipendente = id_dipendente;
			this.username = username;
			this.password = password;
			this.ruolo = ruolo;
			this.nome = nome;
			this.cognome = cognome;
			this.dataNascita = dataNascita;
			this.partitaIVA = partitaIVA;
			this.id_progetto = id_progetto;
		}
		
		public Dipendenti(int id_dipendenti, String username, String password, String ruolo) {
			this.id_dipendente = id_dipendente;
			this.username = username;
			this.password = password;
			this.ruolo = ruolo;
		}
		
		//metodo GET e SET
		public int getId_dipendente() { return id_dipendente; }
		public String getUsername() { return username; }
		public String getPassword() { return password; }
		public String getRuolo() { return ruolo; }
		public String getNome() { return nome; }
		public String getCognome() { return cognome ;}
		public String getDataNascita() { return dataNascita ;}
		public String getPartitaIVA() { return partitaIVA ;}
		public int getId_progetto() { return id_progetto;}
		
		//SET
		public void setId_dipendente(int id_dipendente) {
			this.id_dipendente = id_dipendente;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public void setCognome(String cognome) {
			this.cognome = cognome;
		}
		public void setDataNascita(String dataNascita) {
			this.dataNascita = dataNascita;
		}
		public void setPartitaIVA(String partitaIVA) {
			this.partitaIVA = partitaIVA;
		}
		public void setId_progetto(int id_progetto) {
			this.id_progetto = id_progetto;
		}


}

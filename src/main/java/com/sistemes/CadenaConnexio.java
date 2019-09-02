package com.sistemes;

public class CadenaConnexio {

	String servidor;
	String port;
	String database;
	String usuari;
	String password;

	public CadenaConnexio() {
		super();
		clear();
	}
	
	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void clear() {
		this.servidor = "";
		this.port = "";
		this.database = "";
		this.usuari = "";
		this.password = "";
	}

}

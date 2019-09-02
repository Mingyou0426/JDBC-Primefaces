package com.sistemes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ClassConnexio {
	CadenaConnexio cadenaConnexio = null;
	Connection connexio;
	
	public ClassConnexio(CadenaConnexio cadenaConnexio) {
		this.cadenaConnexio = cadenaConnexio;

	   	Properties props = new Properties();
  	   	props.setProperty("user", this.cadenaConnexio.getUsuari());
	   	props.setProperty("password",this.cadenaConnexio.getPassword());
		// props.setProperty("encoding", "UNICODE_FSS");
//	   	props.setProperty("TRANSACTION_READ_COMMITTED","isc_tpb_read_committed,isc_no_rec_version, isc_tpb_write,isc_tpb_nowait");

	    try {
	    	Class.forName("org.firebirdsql.jdbc.FBDriver");
	    	this.connexio = DriverManager.getConnection("jdbc:firebirdsql"+":"
	                                                     +"//"+this.cadenaConnexio.getServidor()+":"
  	     			                                     +this.cadenaConnexio.getPort()+"/"
	                                                     +this.cadenaConnexio.getDatabase(),props);
			/* this.connexio = DriverManager.getConnection(
					"jdbc:firebirdsql://localhost:3050/c:/DATABASE.IB",
					"SYSDBA",
					"covertheworld"); */

    		this.connexio.setAutoCommit(false);
	    // } catch (SQLException | ClassNotFoundException  e1) {
		} catch (Exception  e1) {
	 		e1.printStackTrace();
	 	}
		
	}

	public Connection getConnection() {
		return connexio;
	}


}

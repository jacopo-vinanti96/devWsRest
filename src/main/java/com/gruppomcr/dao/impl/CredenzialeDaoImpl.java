package com.gruppomcr.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gruppomcr.dao.CredenzialeDao;
import com.gruppomcr.dao.model.Credenziale;

public class CredenzialeDaoImpl implements CredenzialeDao {

	private CredenzialeDaoImpl() {
		
	}
	
	private static CredenzialeDaoImpl instance = null;
	
	public static CredenzialeDaoImpl getInstance() {
    	if (instance == null) {
    		instance = new CredenzialeDaoImpl();
    	}
    	return instance;
    }
	
	private String DB_URL = "jdbc:mysql://localhost:3306/laboratorio";
    private String USER = "root";
    private String PASS = "root";
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    @Override
    public boolean validazione(Credenziale credenziale) {
    	String queryLettura = "SELECT COUNT(id) FROM credenziali WHERE (username='"+credenziale.getUsername()+"' AND password='"+credenziale.getPassword()+"')";
    	boolean result = false;
    	
    	try {
    		Class.forName(JDBC_DRIVER);
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	try (    
    			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS );
    			Statement statement = connection.createStatement();
    	) {
    		try(ResultSet resultSet = statement.executeQuery(queryLettura);){
    			//.next() ritorna false se non c'è la riga da leggere e true se esiste, quindi è un ulteriore controllo
    			if (resultSet.next()) {
    				//Oppure utilizzando un Alias anzichè selezionare la prima colonna
    				if (resultSet.getInt(1) == 1) {
    					result = true;
    				}    				
    			}
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    	
    }

}

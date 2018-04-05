package it.au.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.au.bean.Persona;

public class PersonaDao {

	private String connectionString = "jdbc:mysql://localhost:3306/db1?user=root&password=root";
	Connection connection = null;
	
	public PersonaDao() {}
	
	public void chiudi() {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		try
		{
		    Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
		    // gestione dell'eccezione
		}
		
		
		 
		try {
		    connection = DriverManager.getConnection(connectionString);
		    System.out.println("connessione "+!(connection.isClosed()));
		    /*
		    PreparedStatement prepared = connection.prepareStatement("insert into persone (cognome, nome, eta) values (?,?,?)");
		    prepared.setString(1, "Marroni");
		    prepared.setString(2, "Enrico");
		    prepared.setInt(3, 55);
		 
		    prepared.executeUpdate();
		 
		    Statement stm = connection.createStatement();
		    ResultSet rs = stm.executeQuery("select * from persone");
		    while (rs.next()) {
		        System.out.println(rs.getString("cognome") + " " + rs.getString("nome") + " di anni " + rs.getInt("eta"));
		    }
		    */
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		} /*finally {
		    try {
		        if (connection != null)
		            connection.close();
		        System.out.println(connection.isClosed());
		    } catch (SQLException e) {
		        // gestione errore in chiusura
		    }
		}*/
		
		return connection;
	}
	
	public void addPersona(Persona p) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("insert into anagrafica (nome,cognome, mail) values (?,?,?)");
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCognome());
			ps.setString(3, p.getMail());
			ps.executeUpdate();
			chiudi();
			
		}catch(Exception e) {}
	}
	
	public Persona getPersona(int id) {
		
		try {
			PreparedStatement ps = getConnection().prepareStatement("select * from Anagrafica where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Persona p = null;
			if(rs.next()) {
				p = new Persona();
				p.setNome(rs.getString(1));
				p.setCognome(rs.getString(2));
				p.setMail(rs.getString(3));
			}
			chiudi();
			return p;
		}catch(Exception e) {}
		
		return null;
	}
	
	
}

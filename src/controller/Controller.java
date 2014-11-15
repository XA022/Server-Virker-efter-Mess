package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ModelDAO;

public class Controller extends ModelDAO {
	
	private PreparedStatement saveNotes = null;
	
//Jeg har lavet en test 
	
	public Controller() {
		super();
		
		//saving notes
		try {
			saveNotes = conn.prepareStatement("INSERT INTO notes VALUES(?,?);");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	
// Metoder for Notes
	
	public void saveNotes(String text, String created) {
			
		try {
			saveNotes.setString(4, text);
			saveNotes.setString(5, created);
			saveNotes.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

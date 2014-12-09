package model.QueryBuild;

import java.sql.Connection;

public class ConnectionController {
	private static ConnectionController instance;
	private Connection connection;

	

	public static ConnectionController getInstance() {
		if(instance==null) {
			instance = new ConnectionController();
		}
		return instance;
	}



	public Connection getConnection() {
		return connection;
	}



	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	
	
}

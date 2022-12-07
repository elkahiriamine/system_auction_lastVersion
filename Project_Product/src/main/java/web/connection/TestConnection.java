package web.connection;

import java.sql.Connection;

public class TestConnection {

	public TestConnection() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
        Connection c = ConnectionWithOracle.getConnection();
        
	}

}

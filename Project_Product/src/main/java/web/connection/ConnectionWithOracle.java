package web.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionWithOracle {

	static private Connection connection = null;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "java", "oracle");
			System.out.println("Creation connection to data base");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public static Connection getConnection() {
		return connection;
	}
}

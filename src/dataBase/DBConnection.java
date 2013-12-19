package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn;
	/*
	private static String url = "jdbc:mysql://localhost:3306/db_spyhole";
	private static String user = "root";// Username of database
	private static String pass = "";// Password of database
	*/
	private static String url = "jdbc:mysql://spyhole.no-ip.biz:1901/db_spyhole";	
	private static String user = "pi";	// Username of database
	private static String pass = "beuthspyhole";		// Password of database
	
	public static Connection connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException cnfe) {
			System.err.println("1");
			System.err.println("Error: " + cnfe.getMessage());
		} catch (InstantiationException ie) {
			System.err.println("2");
			System.err.println("Error: " + ie.getMessage());
		} catch (IllegalAccessException iae) {
			System.err.println("3");
			System.err.println("Error: " + iae.getMessage());
		}
		conn = DriverManager.getConnection(url, user, pass);
		return conn;
	}

	public static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		if (conn != null && !conn.isClosed())
			return conn;
		connect();
		return conn;
	}
}
import java.sql.*;

public class ConnectiontoServer {

	String url = "mysql server database";  //cleared to prevent public access to my sql database.
	String username = "username";
	String password = "password";
	Connection conn = null;
	
	
	public Connection Connect() {
		try{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connection is successful.");
			return conn;
		}
		catch (SQLException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
	}
			}
	
	
}

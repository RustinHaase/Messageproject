import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class MessagingClass {

	String message = "[blank message]" ;
	String username = "[blank username]";
	int lastmessage = 0;
	Connection connection = null;
	

	public MessagingClass(String username2){
		username = username2; 
		connect();
		setlastmessagenumber();
		
	}
	
	void printusernamelocal(){
		System.out.println("Your username is:");
		System.out.println(username);
	}
	
	void addusernametomessage(){
		message = new String(username + ": " + message);
	}
	
	void updatemessage(String message2){
		message = message2;
		addusernametomessage();
	}
	
	void printmessagelocal(){
		System.out.println("Message is: " + "\n" + message);
	}
	
	void connect(){
		ConnectiontoServer con = new ConnectiontoServer();
		connection = con.Connect();
	}
	
	void printlastmessagenumber() {
		System.out.println("Last message number is:" + lastmessage);
	}
	
	////////SQL Section --------------------------------------------------------------
	
	void sendmessage(){
		String query = "insert into Messages(Message) values (?) ";
	
		java.sql.PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, message);
			ps.executeUpdate();
			setlastmessagenumber();
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + query);
		}
		
	}
	
	
	
	
	void getLastmessage(){
		String query = "Select * from Messages order by PriKey desc Limit 1";
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()){
			String Message = rs.getString("Message");
			System.out.println(Message);
			}
			
			setlastmessagenumber();
			
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + query);
	}	
	}
	
	void setlastmessagenumber(){  //During initialization the function will get a value to find the last number.  When
		//implemented in a actual messaging program, this should be run either periodically or incorporated into other functions. 
		String query = "Select * from Messages order by PriKey desc Limit 1";
		java.sql.ResultSet rs = null;
		java.sql.PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()){
				this.lastmessage = rs.getInt("PriKey");
			}
		} catch (SQLException sqle) {
			System.out.println("SQLExcpetion: " + query);
		}
		
	}
	
	void closeconnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("failed to close connection.");
			e.printStackTrace();
		}
	}
}

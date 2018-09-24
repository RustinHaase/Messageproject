import java.io.IOException;
import java.sql.*; 
import java.sql.Connection;
import java.util.Scanner;  

public class TheMainReal {

	public static void main(String[] args) throws IOException  {
		
			
		Scanner sc = new Scanner(System.in);  //used to read all data
		
		
		
		System.out.println("Enter your username");  //needed to begin program
		String username = sc.next();
		username = username + sc.nextLine();
		sc.reset();
		
		MessagingClass mc = new MessagingClass(username); //initializes the message Class which must remain persistent until program termination.

		
		
		String command = null;
		int exit = 0;

		System.out.println("Enter your command:");
		
		while(exit != 1){
			
			command = sc.next();
			
			if(command.equalsIgnoreCase("REFRESH") || command.equalsIgnoreCase("R")){
				mc.getLastmessage();
			} 
			
			else if(command.equalsIgnoreCase("WRITE") || command.equalsIgnoreCase("W")){
				sc.reset();
				String message = sc.next();
				message = message + sc.nextLine();
				mc.updatemessage(message);
				mc.sendmessage();
			}
			
			else if(command.equalsIgnoreCase("EXIT") || command.equalsIgnoreCase("E") || command.equalsIgnoreCase("QUIT") || command.equalsIgnoreCase("Q")){
				exit = 1;
			} 
			
			else if(command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h")){
				System.out.println("HELP:  \nREFRESH:  refreshes the page \nWRITE:  Write a message to send"
						+ "\nEXIT: exits the program." + "\nRECONNECT: reconnect to database" + "\nMESS: Get the last message number of the program.");
			}

			else if(command.equalsIgnoreCase("reconnect") || command.equalsIgnoreCase("Re")){
				mc.connect();
			}
			
			else if(command.equalsIgnoreCase("Mess") || command.equalsIgnoreCase("M") ){
				mc.printlastmessagenumber();
			}
			
			sc.reset();
			
			
		}
		mc.closeconnection();
		sc.close();
	}

}

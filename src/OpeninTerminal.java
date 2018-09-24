/////////////////////////  Thank you to Brandon Barajas for providing this code from:  
//                   https://stackoverflow.com/questions/7704405/how-do-i-make-my-java-application-open-a-console-terminal-window


import java.io.*;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;
import java.io.IOException;

public class OpeninTerminal {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException{
        Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = OpeninTerminal.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
        }else{
            TheMainReal.main(new String[0]);
            System.out.println("Program has ended, please type 'exit' to close the console");
        }
    }
}
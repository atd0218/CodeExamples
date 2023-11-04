package FileWriterJava;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args)
    {
        

        try{
            
            FileWriter writer = new FileWriter("C:\\Users\\pastu\\OneDrive\\Documents\\MyFirstProgram\\src\\FileWriterJava\\poem.txt");
            writer.write("Roses are red violets are blue can I smack your booty and eat you out too?");
            writer.append("Poem by yours truly");
            writer.close();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            System.out.println("Goodbye!");
        }  
    
    }
}

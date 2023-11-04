package FIleReaderJava;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args)
    {
        //FileReader = read the contents of a file as a stream of characters. One by one
        //             read() returns an int value which contiains the byte value
        //             when read() returns -1, there is no more data to be read

        try
        {
            FileReader reader = new FileReader("C:\\Users\\pastu\\OneDrive\\Documents\\MyFirstProgram\\src\\FIleReaderJava\\art.txt");

            // create this variable as a placeholder to loop over easily. When read() = -1 then file has finished. 
            int data = reader.read();

            while(data != -1)
            {
                System.out.print((char)data);

                data = reader.read();
            }

            reader.close();

            // do
            // {
            //     System.out.print((char)data);
            //     data = reader.read();
            //     reader.close();
            // }while(data != -1);

            
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

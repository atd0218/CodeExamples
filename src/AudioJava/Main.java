package AudioJava;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.*; // not compatible with mp3 only .wav

public class Main {
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        // we put this hear so that it will keep the program open and waiting so the file can play. 
        Scanner scanner = new Scanner(System.in);

        // you can work with any type of file you want but you always must include the file you are working with this way
        File file = new File("Level_Up.wav");

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        
        String response = "";
        

        while(!response.equals("Q"))
        {
                System.out.println("P = play, S = stop, R = reset, Q = quit");
                System.out.println("Enter your choice: ");

                response = scanner.next();

                //force uppercase as we cant guarantee the user will enter uppercase
                response = response.toUpperCase();

                switch(response)
                {
                    case ("P"): clip.start();
                    break;
                    case ("S"): clip.stop();
                    break;
                    case ("R"): clip.setMicrosecondPosition(0);
                    break;
                    case("Q"): clip.close();
                    break;
                    default: System.out.println("Not a valid response");
                }
        }
        System.out.println("Thanks for playing some tunes");
    }
}

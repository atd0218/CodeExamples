/**
 * @author Ashton Daniels
 * Date: 02/07/2024
 * JDK Version: 8
 * Description: Create a Hashset and fill it with 10 strings. 
 * Prompt the user to enter a name to search for and let them know if that was found. 
 * Messed around with Optional class and simulated some errors to test, but removed
 * them once done to leave program as it should be minus a few variable declarations. 
 */
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;

public class myMain {
    static public void main(String[] args) {
	Scanner s = new Scanner(System.in);
        Optional<Boolean> found = Optional.of(false);
        HashSet<String> learners = new HashSet<>();
        learners.add("Jessie");
        learners.add("Blake");
        learners.add("Adrian");
        learners.add("Andrew");
        learners.add("Emeke");
        learners.add("Kowshik");
        learners.add("Don");
        learners.add("Ashton");
        learners.add("Zach");

        //TODO: Get a name from user, call your functions here to initialize the HashSet and then lookup the value from the user
        System.out.println("Enter the name of a fellow learner to see if they are still enrolled: ");
        Optional<String> name = Optional.ofNullable(s.nextLine());
        
        if(learners.contains(name.get())){
            found = Optional.of(true);
            System.out.println("This person is still enrolled! \n");
        }
        else {
            System.out.println("This individual is no longer enrolled. \n");
        }
        System.out.println("All of the Learners in the set are: ");
        System.out.println(learners);
    }


}





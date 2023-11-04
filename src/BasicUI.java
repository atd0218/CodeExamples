//import
import javax.swing.JOptionPane;

public class BasicUI {
    
    public static void main(String[] args){

        String name = JOptionPane.showInputDialog("Enter your name");
        JOptionPane.showMessageDialog(null, "Hello "+name);

        //showInputDialog returns string so we have to parse it to an integer. 
        int age = Integer.parseInt(JOptionPane.showInputDialog("What is your age"));
        JOptionPane.showMessageDialog(null, "You are "+age);

        //showInputDialog returns string so we have to parse it to an Double. 
        double height = Double.parseDouble(JOptionPane.showInputDialog("What is your height"));
        JOptionPane.showMessageDialog(null, "You are "+height);


    }
}

public class stringMethods {
    
    public static void main(String[] args) {

        //Since string is a reference data type it has access to useful methods

        String name = "Bro";

        boolean result = name.equalsIgnoreCase("bro");
        // int result = name.length();
        // char result = name.charAt(0);
        // int result = name.indexOf("o");
        // boolean result = name.isEmpty();
        // String result = name.toUpperCase();
        // String result = name.toLowerCase();
        // String result = name.trim(); - removes emtpy space before and after string
        // String result = name.replace('o', 'a');
        

        System.out.println(result);

    }
}

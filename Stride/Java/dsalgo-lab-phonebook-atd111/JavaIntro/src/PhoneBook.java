/**
 * @author Ashton Daniels
 * Date: 02/08/2024
 * JDK Version: 8
 * Description: Taking the older implementation and instead using the concepts of Hashing
 * and the java HashMap Class and utilizing that instead. This allowed me to store Contacts
 * directly in the HashMap and remove the need for an extra class to store Contacts. 
 * 
 * You can perform add, del, and find operations on this HashMap storing Contacts. 
 * 
 * Additionally, I made use of Optional Class in certain areas to test its capabilites such as when
 * needing to remove somthing from the HashMap so I did not have to perform a check myself. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Optional;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    //private List<Contact> contacts = new ArrayList<>();

    /*
     * Create a hashmap with key of number, which will be used as the 
     * value to Hash against in the calculation and determine where to store
     * the contact information. 
     */
    HashMap<Integer, String> contacts = new HashMap<>();

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    /**
     * 
     * @return custom Query object containing the type, name, and phone number if add or just type and number.
     * 
     * takes in values from the user and stores them in Query objects to be used in the program. 
     */
    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }
    //customer println method. 
    private void writeResponse(String response) {
        System.out.println(response);
    }


    /**
     * 
     * @param query - query object that contains the user instructions to carry out. 
     * 
     * add - first checks if the Key exists in the HashMap and if it does overrides that entry. 
     * Otherwise it will add the entry as a new entry. 
     * 
     * del - uses optional to simply try and remove the object and not throw an error if
     * the object did not exist. 
     * 
     * find - .get built in method will automatically return null if nothing is found so I do
     * not need a conditional or Optional variable here. 
     */
    private void processQuery(Query query) {
        if (query.type.equals("add")) {
            boolean wasFound = false;
            //containsKey O(1) average - O(n) worst case
            if(contacts.containsKey(query.number)) {
                contacts.replace(query.number, query.name);
                wasFound = true;
            }
            if (!wasFound) {
                contacts.put(query.number, query.name);
            }
        } else if (query.type.equals("del")) {
            Optional<String> checkDel = Optional.ofNullable(contacts.remove(query.number));
            //can write optional code using isPresent() to display the value stored that was removed if wanted. 
        } else {
            String response = contacts.get(query.number);
            writeResponse(response);
        }
    }

    public void processQueries() {
        Long startTime = System.nanoTime();
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
        System.out.println(System.nanoTime()-startTime);
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
    
}

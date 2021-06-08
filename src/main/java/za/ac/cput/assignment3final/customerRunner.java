/**
 * @author Kurtney Clyde Jantjies (218138105)
 * @Description Topic: Text Files, Serialization and Exception Handling 
 * @DueDate: 9 June 2021
 */

package za.ac.cput.assignment3final;

import java.util.*;

public class customerRunner {

    
    public static void main(String[] args) {
        FileHandler file = new FileHandler();
        file.openFile();
        file.readFromFile();
        System.out.println("Lists\n");
        file.printList();
    
    }
}

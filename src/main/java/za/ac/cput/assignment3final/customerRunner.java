/**
 * @author Kurtney Clyde Jantjies (218138105)
 * @Description Topic: Text Files, Serialization and Exception Handling 
 * @DueDate: 9 June 2021
 */
package za.ac.cput.assignment3final;

public class customerRunner {

    
    public static void main(String[] args) {
        //Creating Object file for Weading and Writting
        FileHandler file = new FileHandler();
        
        //File Handling: Read stakeHolder.ser file
        file.openFile();
        file.readFromFile();
        
        //File Handling: Write to customerOuFile.txt
        file.openCustomerOutFile();
        file.writeCustomerOutFile();
        
        //File Handling: Write to supplierOutFile.txt
        file.openSupplierOutFile();
        file.writeSupplierOutFile();
    }
}

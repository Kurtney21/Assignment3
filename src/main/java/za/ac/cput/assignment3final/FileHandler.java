/**
 * @author Kurtney Clyde Jantjies (218138105)
 * @Description Topic: Text Files, Serialization and Exception Handling 
 * @DueDate: 9 June 2021
 */
package za.ac.cput.assignment3final;

import java.io.*;
import java.util.*;

public class FileHandler {
    private ObjectInputStream input;
    private BufferedWriter soutput;
    private BufferedWriter coutput;
    private final String topS   =  "======================= SUPPLIER ========================";
    private final String topC   =  "======================= CUSTOMER ===========================";
    private final String bottomC = "============================================================\n";
    private final String bottomS = "=========================================================\n";
    private final ArrayList<Supplier> supplierArrayList = new ArrayList();
    private final ArrayList<Customer> customerArrayList = new ArrayList();
    private Calculation calc;

    public ArrayList<Supplier> getSupplierArrayList() {
        return supplierArrayList;
    }

    public ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }
    
    //Deserialization || Opening File 
    public void openFile(){
        try{
            System.out.println("** stakeholder.ser Open for reading!!! **");
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
        }
        catch(IOException e){
            System.out.println("Error opening Ser File" + e);  
        }
    }
    
    //Deserialization || Close Files
    public void closeFile(){
        try{
            input.close();
            System.out.println("******************\nFile closed for reading!!!");
        }
        catch(IOException e){
            System.out.println(" ");    
        }
    }
    
    //Deserialization || Reading From File
    void readFromFile() {
        try{ 
            Stakeholder s;
            while(true){
                s = (Stakeholder) input.readObject();
                if(s instanceof Customer){
                    customerArrayList.add((Customer) s );
                }
                if(s instanceof Supplier){
                    supplierArrayList.add((Supplier) s);
                }
            }         
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("Error occured reading line.");
        }finally{
            closeFile();
        }
    }
    
    void openCustomerOutFile(){
        try{
            coutput = new BufferedWriter(new FileWriter("customerOutFile.txt"));
        }
        catch(IOException e){
            e.getMessage();
        }
    }
    
    void closeCustomerOutFile(){
        try{
            coutput.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    void writeCustomerOutFile(){
        calc = new Calculation();
        calc.sortCustomerListById(customerArrayList);
        calc.addAge(customerArrayList);
        calc.reformatDOB(customerArrayList);
        
        try{
            //Printing List contents to console
            coutput.write(topC);
            coutput.write("\nID\tName\t\tSurname\t\tDate of Birth\t Age\n");
            coutput.write(bottomC);

            for(int i = 0; i<customerArrayList.size();i++){
                coutput.write(String.format("%-5s\t%-10s\t%-10s\t%-15s\t %-10s\n",
                customerArrayList.get(i).getStHolderId(),
                customerArrayList.get(i).getFirstName(),
                customerArrayList.get(i).getSurName(),
                customerArrayList.get(i).getDateOfBirth(),
                calc.getAge().get(i).toString()
                ));
            }
            coutput.write("\nNumber of customers who can rent: "+calc.canRent(customerArrayList)+"\n");
            coutput.write("Number of customers who cannot rent: "+calc.cannotRent(customerArrayList));
        }
        catch(IOException e){
            e.getMessage();
        }
        finally{
            closeCustomerOutFile();
        }
    }
    
    void openSupplierOutFile(){
        try{
            soutput = new BufferedWriter(new FileWriter("supplierOutFile.txt"));
        }
        catch(IOException e){
            e.getMessage();
        }
    }
    
    void closeSupplierOutFile(){
        try{
            soutput.close();
        }
        catch(IOException e){
            e.getMessage();
        }
    }
    
    void writeSupplierOutFile(){
        try{
            calc.sortSupplierListByName(supplierArrayList);
            soutput.write(topS);
            soutput.write(String.format("\n%-5s%-20s%-15s%-20s\n","ID","Name","Prod Type","Description"));
            soutput.write(bottomS);
            
            for(int i = 0; i < supplierArrayList.size();i++){
                soutput.write(String.format("%-5s%-20s%-15s%-20s\n",
                        supplierArrayList.get(i).getStHolderId(),
                        supplierArrayList.get(i).getName(),
                        supplierArrayList.get(i).getProductType(),
                        supplierArrayList.get(i).getProductDescription()
                ));
            }
        }
        catch(IOException e){
            e.getMessage();
        }
        finally{
            closeSupplierOutFile();
        }
    }
}

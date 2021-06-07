package za.ac.cput.assignment3final;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadFile {
    private ObjectInputStream input;
    private BufferedWriter soutput;
    private BufferedWriter coutput;
    private final String topS   = "========================Supplier============================\n";
    private final String topC   = "========================Customer============================\n";
    private String bottom;
    private ArrayList<Supplier> supplierArrayList = new ArrayList();
    private ArrayList<Customer> customerArrayList = new ArrayList();

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
        }catch(IOException e){
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
                    System.out.println("Customers:"+customerArrayList.size());
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
    
    void printList(){
        customerArrayList.forEach((n) -> {
            System.out.println(n);
        });
        supplierArrayList.forEach((n) -> {
            System.out.println(n);
        });
        System.out.println(customerArrayList.size());
    }
    
}

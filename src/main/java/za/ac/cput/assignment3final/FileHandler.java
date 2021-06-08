/**
 * @author Kurtney Clyde Jantjies (218138105)
 * @Description Topic: Text Files, Serialization and Exception Handling 
 * @DueDate: 9 June 2021
 */
package za.ac.cput.assignment3final;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileHandler {
    private ObjectInputStream input;
    private BufferedWriter soutput;
    private BufferedWriter coutput;
    private final String topS   = "========================Supplier============================";
    private final String topC   = "========================Customer============================";
    private final String bottom = "============================================================";
    private final ArrayList<Supplier> supplierArrayList = new ArrayList();
    private final ArrayList<Customer> customerArrayList = new ArrayList();
    private final ArrayList<Integer> age;

    public FileHandler() {
        this.age = new ArrayList();
        
    }
    
    void addAge(){
        for(int i = 0; i < customerArrayList.size();i++ ){
            age.add(new Calculation().Age(customerArrayList.get(i).getDateOfBirth()));
        }
    }

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
    
    //Debuging + Trial and Error
    void printList(){
        //Printing Supplier List to Console
        System.out.println(topS);
        System.out.println("ID\tName\t\t\tProd Type\tDescription");
        System.out.println(bottom);
        supplierArrayList.forEach((n) -> {
            System.out.println(n);
        });
    }
    
    void openCustomerOutFile(){
        try{
            coutput = new BufferedWriter(new FileWriter("customerOutFile.txt"));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
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
    /*
    void writeCustomerOutFile(){
        Calculation calc = new Calculation();
        calc.sortCustomerListById(customerArrayList);
        calc.sortSupplierListByName(supplierArrayList);
        addAge();
        calc.reformatDOB(customerArrayList);
        
        try{
        //Printing List contents to console
        coutput.write(topS);
        coutput.write("ID\tName\t\tSurname\t\tDate of Birth\t Age");
        coutput.write(bottom);
        
        for(int i = 0; i < customerArrayList.size();i++){
                    String.format("%-5s\t%-10s\t%-15s\t%-15s\t%-5s",
                    coutput.write(customerArrayList.get(i).getStHolderId().toString()+" "),
                    customerArrayList.get(i).getFirstName(),
                    customerArrayList.get(i).getSurName(),
                    customerArrayList.get(i).getDateOfBirth(),
                    age.get(i).toString());
        }
        calc.canRent(customerArrayList);
        }
        catch(Exception e){
            System.out.println();
        }
    }*/
    
    
    
}

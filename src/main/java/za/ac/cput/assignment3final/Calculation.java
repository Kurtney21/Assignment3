/**
 * @author Kurtney Clyde Jantjies (218138105)
 * @Description Topic: Text Files, Serialization and Exception Handling 
 * @DueDate: 9 June 2021
 */
package za.ac.cput.assignment3final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;


public class  Calculation{
    private int canRent;
    private int notRent;
    private int newAge;
    
    //Sorting Customer List by id
    public void sortCustomerListById(ArrayList<Customer> array){
        array.sort((c1,c2) -> c1.getStHolderId().compareTo(c2.getStHolderId()));
    }
    
    //Sort Supplier list by Name
    public void sortSupplierListByName(ArrayList<Supplier> array){
        array.sort((s1,s2) -> s1.getName().compareTo(s2.getName()));
    }
    
    //DOB Reformatting
    public void reformatDOB(ArrayList<Customer> dob){
        dob.forEach((n) -> {
        String oldDOB = n.getDateOfBirth();
        SimpleDateFormat oldsdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                SimpleDateFormat newFormat = new SimpleDateFormat("dd MMM yyyy");
                Date date = oldsdf.parse(oldDOB);
                oldDOB = newFormat.format(date);
                n.setDateOfBirth(oldDOB);
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }
    
    void canRent(ArrayList<Customer> rent){
        rent.forEach((each)->{
            if(each.getCanRent()){
                canRent++;
            }else{
                notRent++;
            }
        });
        System.out.println("Number of customers who rent: "+canRent);
        System.out.println("Number of customers who cannot rent:"+ notRent);
    }
    
    public int Age(String age){
        try{
            LocalDate DoB = LocalDate.parse(age);
            LocalDate present = LocalDate.now();
            newAge = Period.between(DoB, present).getYears(); 
        }
        catch(DateTimeParseException e){
            System.out.println(e.getMessage());
        }
        return newAge;
    }
}

    
    
 


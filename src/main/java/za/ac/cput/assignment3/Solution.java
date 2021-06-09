package za.ac.cput.assignment3;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.Period;
import java.util.Formatter;

// student name :  Mahad Hassan (stud num : 219122822)
// date : 6/8/2021

public class Solution {
  private ObjectInputStream input;
  private FileInputStream fi;
  private ArrayList<Customer> cList = new ArrayList<>();
  private ArrayList<Supplier> sList = new ArrayList<>();
  
  
  
 public void openFile(){
    
        try{
        input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
        
            System.out.println("***File opening for eading****");
        
        
        
        }catch(IOException ioe){
            System.out.println("errer! file not opening!");
        
        
        }
    } // opening method
 
    public void closeFile(){
 try{
     input.close();
 
 } catch(IOException ioe){
 
     System.out.println("error not clossing "+ioe.getMessage());
 }
 
 
 } // close method
  public void readingFile() {
      
      try{
        fi = new FileInputStream("stakeholder.ser");
        input = new ObjectInputStream(fi);
        
        
        while(true) {
            Object obj = input.readObject();

            if(obj instanceof Customer) {
                cList.add((Customer)obj);
            }
            else if(obj instanceof Supplier) {
                sList.add((Supplier)obj);
            }
        } // while loop
      } // try
 
      catch(IOException ex) {
          
      } 
      catch (ClassNotFoundException ex) {
          System.out.println(" class not found " + ex.getMessage());
      }  
      
  } // reading from file method
  
  
// sorting customer object by in ascending order of stakeholderId.
   public void sortCustomer() {
      Collections.sort(cList, (o1, o2) -> {
          return o1.getStHolderId().compareTo(o2.getStHolderId()); 
          
      });
  }
   
  //  sorting supplier  object by in alphabetical order
     public void sortSupplier() {
      Collections.sort(sList, (o1, o2) -> {
          return o1.getName().compareTo(o2.getName()); 
          
      });
  }
  

  
  public void displayCnSObjects() {
      sortCustomer();
      sortSupplier();
      
      for(int i = 0; i < cList.size(); i++){
          System.out.println(cList.get(i));
      }
      System.out.println();
      for (int i = 0; i < sList.size(); i++) {
          System.out.println(sList.get(i));
          
      } // store supplier objects
      
  } // displays customer and supplier objects in seperte and soring them
   
  public void getAllCustomerAge() {
      for(int i = 0; i < cList.size(); i++){
          String cNames = cList.get(i).getSurName();
          String DOB = cList.get(i).getDateOfBirth();
         
          
       

          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          Date date;
          
          try {
              date = df.parse(DOB);
              
              Calendar cal = Calendar.getInstance();
              cal.setTime(date);
              

              int cYOB = cal.get(Calendar.YEAR);
              
              Calendar currentCal = Calendar.getInstance();
              int currentYear = currentCal.get(Calendar.YEAR);
              
              int age = currentYear - cYOB;
              Formatter  fmt = new Formatter();
               fmt.format("%td %tb %tY", cal, cal, cal);
                
            //  System.out.println(fmt); // this prints the reformatted of date of birth of all customers
              System.out.printf("%s is %d years old\n", cNames, age);
             
           
              
              
          } catch (ParseException ex) {
              Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           
      }
  } // this is method is determining each customers age and formatted date of birth
          
  public void customerOutFile(){
  
  try{
  FileWriter fw = new FileWriter("src//customerOutFile.txt");
   BufferedWriter br = new BufferedWriter(fw);
   
     sortCustomer();
     int cannotRent = 0;
     int canRent =0;
     br.write("================== CUSTOMERS =======================\n");
    // br.write("ID\t  Name\t Surname\t Date of birth\t Age ");
     br.write(String.format("%-10s %-20s %-15s %-5s %-5s\n", "ID", "Name", "Surname", "Date of birth ","Age"));
      
      for(int i = 0; i < cList.size(); i++){
          System.out.println(cList.get(i));
            br.write(String.format("%-10s %-20s %-15s %-5s %-5s\n", 
                    cList.get(i).getStHolderId(),
                    cList.get(i).getFirstName(),
                    cList.get(i).getSurName(),
                    dateformater(cList.get(i).getDateOfBirth()),
                    getCustomerAge(cList.get(i).getDateOfBirth())
            ));
            
          if(cList.get(i).getCanRent())
              canRent++;
              else
              cannotRent++;
      }
      
      br.append("Number of customers who can rent: 	"+canRent+"   \n" +
       "Number of customers who cannot rent: 	"+cannotRent+"   ");
      br.close();
      
  }catch(IOException ioe){
      System.out.println("error");
  
  }
  
  } // write costomerfile method
  
  
    public int getCustomerAge(String DOB) {
      
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          Date date;
          int age =0;
          try {
              date = df.parse(DOB);
              
              Calendar cal = Calendar.getInstance();
              cal.setTime(date);
              

              int cYOB = cal.get(Calendar.YEAR);
              
              Calendar currentCal = Calendar.getInstance();
              int currentYear = currentCal.get(Calendar.YEAR);
              
               age = currentYear - cYOB;
               
               

          } catch (ParseException ex) {
              Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
          }
         
          return age;
           
      }
 //date of birth formatter
    
  public Formatter dateformater(String DOB){
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          Date date;
           Formatter  fmt = new Formatter();
           try {
              date = df.parse(DOB);
              
              Calendar cal = Calendar.getInstance();
              cal.setTime(date);
             
              fmt.format("%td %tb %tY", cal, cal, cal);
              
               } catch (ParseException ex) {
              Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
          }
           return fmt;
  } // date birth formatter
  
        public void supplierOutFile(){
        sortSupplier();
       try{
  FileWriter fw = new FileWriter("src//supplierOutFile.txt");
   BufferedWriter br = new BufferedWriter(fw);
      
   br.write("================== SUPPLIERS  =======================\n");
   br.write(String.format("%-10s %-20s %-15s %-10s\n", "ID", "Name", "Product Type", "Description"));
//           br.newLine();
           for (int i = 0; i < sList.size(); i++) {
               
               System.out.println(sList.get(i));
               
                br.write(String.format("%-10s %-20s %-15s %-10s\n",sList.get(i).getStHolderId(),
                        sList.get(i).getName(),
                        sList.get(i).getProductType(),
                        sList.get(i).getProductDescription()));
                
//           br.newLine();
           } // for loop
       
           br.close();
   
       }catch(IOException ioe){
       
           System.out.println("can not write in the file " + ioe.getMessage());
       }
  } // wrting in supplir 
  
  
  
 

  
}

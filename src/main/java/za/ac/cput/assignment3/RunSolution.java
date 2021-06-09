package za.ac.cput.assignment3;

/**
 *
 * @author Mahad
 */
public class RunSolution {
    public static void main(String[] args) {
        Solution files = new Solution();
        files.openFile();                  // this method opens the file
       files.readingFile();               // this method reading from file
     // files.displayCnSObjects();           // this method customer and supplier objects in a  seperate arraylist
       // files.getAllCustomerAge();       // this method displays ages of all customer class
      files.customerOutFile();            // this is calls customerOutFile.txt
       // files.supplierOutFile();           // this is calls supplierOutFile.txt
     
    }
}

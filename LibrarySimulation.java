package librarysimulation;
import java.util.*;

/**
 *
 * @author Tiberiu
 */
public class LibrarySimulation {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    System.out.println(Arrays.toString(runSimulation(generateBookStock(),100)));
    }
    
    public static LibraryBook [] generateBookStock(){
    
    String [] authorsList =
        { "Lewis and Loftus",  "Mitrani", "Goodrich",
          "Lippman", "Gross", "Baase", "Maclane", "Dahlquist",
          "Stimson", "Knuth", "Hahn", "Cormen and Leiserson",
          "Menzes", "Garey and Johnson"};
    String [] titlesList =
          { "Java Software Solutions", "Simulation",
            "Data Structures", "C++ Primer", "Graph Theory",
            "Computer Algorithms", "Algebra", "Numerical Methods",
            "Cryptography","Semi-Numerical Algorithms",
            "Essential MATLAB", "Introduction to Algorithms",
            "Handbook of Applied Cryptography",
            "Computers and Intractability"};
    int [] pagesList = {832, 185, 695, 614, 586, 685, 590, 573, 475,
                    685, 301, 1175, 820, 338};
    int n = authorsList.length;
    LibraryBook [] bookStock = new LibraryBook[n];
    for(int i = 0; i < n; i++){
           bookStock[i] = new LibraryBook(authorsList[i],
                                           titlesList[i], pagesList[i]);
}
    // set library classification for half of the LibraryBooks
    for(int i = 0; i < n; i=i+2){
        bookStock[i].setLibraryClass("QA" + (99 - i));
    }
    // set approx. two thirds of LIbraryBooks in test data as
       // lending books
       for(int i = 0; i < 2*n/3; i++)
           bookStock[i].setAsForLending();
       // set approx. one third of LibraryBooks in test data as
       // reference-only
       for(int i = 2*n/3; i < n; i++)
           bookStock[i].setAsReferenceOnly();
       return bookStock;
    }
    
     /**
* @param bookStock              the stock of LibraryBooks in the library
* @param numberOFevents         the size of the events table to be
* @return                       generated table of events generated during 
*                               the simulation
*/
     public static String [] runSimulation(LibraryBook [] bookStock, int numberOFevents){
        Random rnd = new Random();
        // String array used for displaying simulation
        String [] output = new String [numberOFevents];
        int n = LibraryBook.getBooks();
        int j=70; // variable used to classifi books
        for (int index =0; index<numberOFevents; index++){
            /* used to randomly chose between 
            return book and reservation placed event
             */
            int c = rnd.nextInt(2);
            //get random book from bookStock
            LibraryBook book = bookStock[rnd.nextInt(n)];
            
            //book is classified event
            if (book.getLibraryClass() == null) 
            {
                //decrements variable for classification number to give unique classif
                j-=4;
                book.setLibraryClass("QA" + (j));
                output[index] = ((index+1)+" " +LibraryBook.getBooksLoan()+
                        " ----"+ " BOOK IS CLASSIFIED\n");
            }
            
            //book is reference only event
            else if (book.getLibraryClass() != null && 
                "REFERENCE_ONLY".equals(book.getBookStatus()))
            {
                output[index] = ((index+1)+" " +LibraryBook.getBooksLoan()+" " 
                        + book.getLibraryClass() + " REFERENCE ONLY BOOK\n");
            }
            
            // Book is loaned out event 
            else if (book.getLibraryClass() != null && 
                "AVAILABLE_FOR_LENDING".equals(book.getBookStatus()))
            {
                book.borrowBook();
                output[index] = ((index+1)+" "+(LibraryBook.getBooksLoan()-1)+" " 
                        + book.getLibraryClass() + " BOOK IS LOANED OUT\n");
            }
            
            // book is returned event
            else if (c==0 && "ON_LOAN".equals(book.getBookStatus()))
            {
                //variable created for displaying correct loaned books value 
                //based on book reservations
                int plus=0;
                book.returnBook();
                if ("AVAILABLE_FOR_LENDING".equals(book.getBookStatus())){
                    plus = 1;
                }
                output[index] = ((index+1)+" " +(LibraryBook.getBooksLoan()+plus)+" " 
                        + book.getLibraryClass() + " BOOK IS RETURNED\n");
            }
            
            /*nested if statement to determine event type
            *reservation placed event or book cannot be reserved event
            */
            else if (c==1 && "ON_LOAN".equals(book.getBookStatus())) 
                // nested if to check if able to reserver or not after wanting to reserve
            {
                // book is reserved event
                if (book.reserveBook() == true){
                
                output[index] = ((index+1)+" " +LibraryBook.getBooksLoan()+" "
                        + book.getLibraryClass() + " RESERVATION PLACED FOR ON-LOAN BOOK\n");
                }
                //book cannot be reserved event
                else if (book.reserveBook() == false){
                    output[index] = ((index+1)+" " +LibraryBook.getBooksLoan()+" " 
                        + book.getLibraryClass() + " BOOK IS ON LOAN BUT CANNOT BE RESERVED\n");
                }
            }
        } 
        
        return output;
    }
}

package librarysimulation;
//import java.util.Scanner;
/**
 *          Programming 1 : Coursework 1 
 *          Student number: 100125468
 */
public class LibraryBook {
    private String author;
    private String title;
    private int pages;
    private int timesBorrowed;
    private int nrPendingReservation;
    private String libraryClass;
    private enum bookStatus {REFERENCE_ONLY, ON_LOAN, AVAILABLE_FOR_LENDING};
    private bookStatus status;
    private static int totalBooks;
    private static int booksLoan;
    
    /**
    * Constructor with arguments for a LibraryBook’s author(s),
    * title and number of pages
    * @param bookAuthor
                    the names of the author(s) of this
                    LibraryBook
                    the title of this LibraryBook
                    the number of pages of this
                    LibraryBook
    *
    * @param bookTitle
    * @param bookPages */
    
    public LibraryBook(String bookAuthor,String bookTitle, int bookPages){
        this.author = bookAuthor;
        this.title = bookTitle;
        this.pages = bookPages;
        timesBorrowed = 0;
        nrPendingReservation = 0;
        libraryClass = null;
        status = null;
        totalBooks++;
        
    }
    public static int getBooks(){
        return totalBooks;
    }
    public String getAuthor(){
        return author;
    } 
    public String getTitle(){
        return title;
    }
    public int getPages(){
        return pages;
    }
    public String getLibraryClass(){
        return libraryClass;
    }
    public int getTimesBorrowed(){
        return timesBorrowed;
    }
    public String getBookStatus(){
        return status.name();
    }
    // returns nr of books on loan
    public static int getBooksLoan(){
        return booksLoan;
    }
     
    /* reset the library's classification
       if the new classification has at least 3 chars
    */
    public boolean setLibraryClass(String bookClass){
        
        if (bookClass.length() > 3){
            libraryClass = (bookClass);    
            return true;   
        }
        else
        return false;
    }
    // sets book as reference only
    public final void setAsReferenceOnly(){
        if (status != bookStatus.AVAILABLE_FOR_LENDING || 
                status != bookStatus.REFERENCE_ONLY){
            status = bookStatus.REFERENCE_ONLY;
            
        }
    }
    // sets book as available for lending
    public void setAsForLending(){
        if(status!= bookStatus.AVAILABLE_FOR_LENDING ||
                status != bookStatus.REFERENCE_ONLY){
            status = bookStatus.AVAILABLE_FOR_LENDING;
        }
    }
    // Method for checking book availability
    public boolean availableForLending(){
        switch (status){
            case AVAILABLE_FOR_LENDING:
                return true;
            default:
                return false;
        }
    }
    /**
    * If possible, reserves this LibraryBook.
    * This is only possible if this LibraryBook is currently on loan
    * and less than 3 reservations have been placed since this went
    * on loan.
    * @return      true,  if a new reservation has been made for this.
                   false, otherwise*/
    public boolean reserveBook(){
        if(status == bookStatus.ON_LOAN && nrPendingReservation < 3){
            nrPendingReservation += 1;
            return true;
        }
        else return false;
            
    }
    //Borrow book method
    public void borrowBook(){
        if(this.status == bookStatus.AVAILABLE_FOR_LENDING){
        this.timesBorrowed ++;
        booksLoan++;
        nrPendingReservation = 0;
        status = bookStatus.ON_LOAN;
        }
    }
    // return book method, action based on nrPendingReservation of a book
    public void returnBook(){
        if (this.status == bookStatus.ON_LOAN && nrPendingReservation > 0 ){
                status = bookStatus.ON_LOAN;
                nrPendingReservation --;
            }
        else if (this.status == bookStatus.ON_LOAN && nrPendingReservation == 0){
            booksLoan--;
            this.status = bookStatus.AVAILABLE_FOR_LENDING;  
            }
        }  
    // Returns data about book as string
    @Override
    public String toString(){
        String courseText;
        courseText = "Title: "+getTitle()+"\n"
                + "Author: "+getAuthor()+"\n"
                + "Pages: "+ getPages()+"\n"
                + "Classification: "+getLibraryClass()+"\n";
       
        return courseText;
    }
    
    
}

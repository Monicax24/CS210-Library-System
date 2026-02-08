import java.util.*;

public class Member {
    private String name;
    private String ID;
    private HashSet<String> borrowedBooks;

    public Member(String name, String ID) {
        this.name = name;
        this.ID = ID;
        this.borrowedBooks = new HashSet<>();
    }

    public String getName()
    {
        return name;
    }
    public String getMemberID()
    {
        return ID;
    }
    public HashSet<String> getBorrowedBooks()
    {
        return borrowedBooks;
    }
    
    public void borrowBook(Book book) {
        if (borrowedBooks.contains(book.getTitle())) { //checks for book in borrowedBooks
            System.out.println(ID + " has already borrowed this book: " + book.getTitle() + "\nCould not issue the book.");
        } else {
            borrowedBooks.add(book.getTitle()); //user borrows book if not already borrowed
            book.borrowBook();
            System.out.println("Book issued: " + book.getTitle() + " to " + getName() + " (Member ID: " + getMemberID() + ")"); //outputs book thats issued
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book.getTitle())) { //checks for book in borrowedBooks
            book.returnBook();
            borrowedBooks.remove(book.getTitle());  //books is returned and removed from borrowedBooks
            System.out.println("Book returned: " + book.getTitle());
        } else {
            System.out.println(ID + " has not borrowed this book: " + book.getTitle() + " by " + book.getAuthor() + ".");

        }
    }

    public void listBorrowedBooks() { //lists borrowed books
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have no borrowed books.");
        } else {
            System.out.println("Borrowed books: "); //outputs all borrowed books
            for (String booktitle : borrowedBooks) {
                System.out.println(booktitle);
            }
        }
    }
}

import java.util.*;

public class LibraryManagement {
    private HashMap<String, Book> bookList;
    private HashMap<String, Member> memberList;
    
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        LibraryManagement library = new LibraryManagement();
        int input = 0;

        while (input != 7) { //continuous menu loop until user inputs 7
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. List Borrowed Books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            input = scan.nextInt();
            scan.nextLine();

            if (input == 1) { //add book
                System.out.print("Enter book title: ");
                String title = scan.nextLine();
                System.out.print("Enter book author: ");
                String author = scan.nextLine();
                System.out.print("Enter book ISBN: ");
                String ISBN = scan.nextLine();
                System.out.print("Enter number of copies: ");
                int bookCopies = scan.nextInt();
                scan.nextLine();
                library.addBook(title, author, ISBN, bookCopies);
            }
            else if (input == 2) { //add member
                System.out.print("Enter member name: ");
                String name = scan.nextLine();
                library.addMember(name);
            }
            else if (input == 3) { //search book
                System.out.print("Search book by: \n 1. Title\n 2. Author\n 3. ISBN\nChoose an option: ");
                int opt = scan.nextInt();
                scan.nextLine();
                if (opt == 1) {
                    String type = "title";
                    System.out.print("Enter the book title: ");
                    String value = scan.nextLine();
                    library.searchBook(type, value);
                } else if (opt == 2) {
                    String type = "author";
                    System.out.print("Enter the book author: ");
                    String value = scan.nextLine();
                    library.searchBook(type, value);
                } else if (opt == 3) {
                    String type = "ISBN";
                    System.out.print("Enter the book ISBN: ");
                    String value = scan.nextLine();
                    library.searchBook(type, value);
                }
            }
            else if (input == 4) { //issue book
                System.out.print("Enter member ID: ");
                String ID = scan.nextLine();
                System.out.print("Enter book title to borrow: ");
                String title = scan.nextLine();
                library.issueBook(ID, title);
            }
            else if (input == 5) { //return book
                System.out.print("Enter member ID: ");
                String ID = scan.nextLine();
                System.out.print("Enter book title to return: ");
                String title = scan.nextLine();
                library.returnBook(ID, title);
            }
            else if (input == 6) { //list borrowed books
                System.out.print("Enter member ID: ");
                String ID = scan.nextLine();
                library.listBorrowedBooks(ID);
            }
            else if (input != 7) { //exit
                System.out.println("Please choose a number between 1-6.");
            }
        }
    System.out.println("Exiting...");
    scan.close();
    }

    public LibraryManagement() {
        bookList = new HashMap<>();
        memberList = new HashMap<>();
    }

    private void addBook(String title, String author, String ISBN, int copies) {
        if (bookList.containsKey(title)) {
            Book existingBook = bookList.get(title);

            if (existingBook.getAuthor().equalsIgnoreCase(author) && existingBook.getISBN().equals(ISBN)) { //checks if book already exists
                existingBook.addCopies(copies);
                System.out.println("Book already exists. Updated the number of copies in the library. Copies available for borrowing: " + existingBook.getAvailableCopies());
            }
        } else {
                Book book = new Book(title, author, ISBN, copies); //adds book if it doesn't exist
                bookList.put(book.getTitle(), book);
                System.out.println("Book added: " + book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getISBN() + ")");
            }
        }

    public void searchBook(String type, String value) { //searches for book depending on user input
        switch (type) {
            case "title": //searches by title
                if (bookList.containsKey(value)) {
                    System.out.println("Book found: " + bookList.get(value));
                } else {
                    System.out.println("No books found with this title.");
                }
                break;
            case "author": //searches by author
                boolean author = false;
                System.out.println("Books by " + value + ":");
                for (Book book : bookList.values()) {
                    if (book.getAuthor().equalsIgnoreCase(value)) {
                        System.out.println(book);
                        author = true;
                    } if (!author) {
                        System.out.println("No books found by this author.");
                    }
                }
                break;
            case "ISBN": //searches by ISBN
                for (Book book : bookList.values()) {
                    if (book.getISBN().equals(value)) {
                        System.out.println("Book found: " + book);
                        return;
                    }
                }
                System.out.println("No books found with this ISBN.");
                break;
            default:
                System.out.println("Invalid search type.");
        }
    }

    public boolean checkValid(String ID, String title){ //checks for valid member id/book
        boolean check = true;
        if (!memberList.containsKey(ID)) {
            System.out.println("Invalid member ID.");
            check = false;
        }
        if (!bookList.containsKey(title)) {
            System.out.println("Book not found.");
            check = false;
        }
        return check;
    }

    public void issueBook(String ID, String title) { //issues book
        boolean check = checkValid(ID, title);
        if (check){
            Book book = bookList.get(title);

            if (book.getAvailableCopies() == 0) { //checks if there are copies available
                System.out.println("This book is currently unavailable. Could not issue the book.");
                return;
            }

            Member member = memberList.get(ID);
            member.borrowBook(book);
        }
    }

    public void returnBook(String ID, String title) { //returns book
        boolean check = checkValid(ID, title);
        if (check){
            Member member = memberList.get(ID);
            Book book = bookList.get(title);
            member.returnBook(book);
        }
    }

    public void listBorrowedBooks(String ID) { //lists all borrowed books by user
        if (!memberList.containsKey(ID)) {
            System.out.println("Invalid member ID.");
            return;
        }
        Member member = memberList.get(ID);
        member.listBorrowedBooks();
    }

    private void addMember(String name) { //adds member
        String ID = "M" + (memberList.size() + 1);
        Member newMember = new Member(ID, name);
        memberList.put(ID, newMember);
        System.out.println("Member added: " + name + " (Member ID: " + ID + ")");
    }

}

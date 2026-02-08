public class Book {
    private String title;
    private String author;
    private String ISBN;
    private int availableCopies;
    private int totalCopies;

    public Book(String title, String author, String ISBN, int totalCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getISBN() {return ISBN;}

    public void borrowBook() { //borrows book and removes it from available copies
        if (availableCopies > 0) {
            availableCopies--;
        }
    }
    public void returnBook() { //returns book and adds it to available copies
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }
    public void addCopies(int copy) { //adds copies to available and total copies
        availableCopies += copy;
        totalCopies += copy;
    }
    public int getAvailableCopies() { // returns available copies
        return availableCopies;
    }

    @Override
    public String toString() {
        return "Title: " + title +", Author: " + author + ", ISBN: " + ISBN + ", Available Copies: " + availableCopies + "/" + totalCopies;
    }
}

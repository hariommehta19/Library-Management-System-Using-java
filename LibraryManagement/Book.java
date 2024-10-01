public class Book {
    private String bookName;
    private String author;
    private String issuedTo;
    private String issuedOn;

    public Book(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
        this.issuedTo = null;
        this.issuedOn = null;
    }

    // Getters and setters for the attributes
    // (bookName, author, issuedTo, issuedOn)
}

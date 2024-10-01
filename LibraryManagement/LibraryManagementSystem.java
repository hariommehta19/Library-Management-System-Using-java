import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {

    public void addBook(String bookName, String author) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO books (book_name, author) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, bookName);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void issueBook(int bookId, String issuedTo) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE books SET issued_to = ?, issued_on = CURDATE() WHERE id = ? AND issued_to IS NULL";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, issuedTo);
            stmt.setInt(2, bookId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book is already issued or does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int bookId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE books SET issued_to = NULL, issued_on = NULL WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, bookId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book name: ");
                    String bookName = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    lms.addBook(bookName, author);
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter user name: ");
                    String issuedTo = sc.nextLine();
                    lms.issueBook(bookId, issuedTo);
                    break;
                case 3:
                    System.out.print("Enter book ID: ");
                    int returnBookId = sc.nextInt();
                    lms.returnBook(returnBookId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}


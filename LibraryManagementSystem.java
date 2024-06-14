import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isBorrowed;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}

class Patron {
    private String name;
    private List<Book> borrowedBooks;

    public Patron(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}

class Library {
    private List<Book> books;
    private List<Patron> patrons;

    public Library() {
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void removePatron(Patron patron) {
        patrons.remove(patron);
    }

    public Patron findPatronByName(String name) {
        for (Patron patron : patrons) {
            if (patron.getName().equalsIgnoreCase(name)) {
                return patron;
            }
        }
        return null;
    }

    public void borrowBook(Patron patron, Book book) {
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            patron.getBorrowedBooks().add(book);
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook(Patron patron, Book book) {
        if (patron.getBorrowedBooks().contains(book)) {
            book.setBorrowed(false);
            patron.getBorrowedBooks().remove(book);
        } else {
            System.out.println("Patron did not borrow this book.");
        }
    }

    public void checkOverdueBooks() {
          }

    public void generateReports() {
            }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command (addBook, removeBook, borrowBook, returnBook, search, exit):");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "addbook":
                    System.out.println("Enter title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter ISBN:");
                    String ISBN = scanner.nextLine();
                    Book newBook = new Book(title, author, ISBN);
                    library.addBook(newBook);
                    break;
                case "removebook":
                    System.out.println("Enter ISBN of the book to remove:");
                    ISBN = scanner.nextLine();
                    Book bookToRemove = library.findBookByISBN(ISBN);
                    if (bookToRemove != null) {
                        library.removeBook(bookToRemove);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case "borrowbook":
                    System.out.println("Enter patron name:");
                    String patronName = scanner.nextLine();
                    Patron patron = library.findPatronByName(patronName);
                    if (patron == null) {
                        patron = new Patron(patronName);
                        library.addPatron(patron);
                    }
                    System.out.println("Enter ISBN of the book to borrow:");
                    ISBN = scanner.nextLine();
                    Book bookToBorrow = library.findBookByISBN(ISBN);
                    if (bookToBorrow != null) {
                        library.borrowBook(patron, bookToBorrow);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case "returnbook":
                    System.out.println("Enter patron name:");
                    patronName = scanner.nextLine();
                    patron = library.findPatronByName(patronName);
                    if (patron == null) {
                        System.out.println("Patron not found.");
                        break;
                    }
                    System.out.println("Enter ISBN of the book to return:");
                    ISBN = scanner.nextLine();
                    bookToBorrow = library.findBookByISBN(ISBN);
                    if (bookToBorrow != null) {
                        library.returnBook(patron, bookToBorrow);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case "search":
                    System.out.println("Enter search term (title, author, ISBN):");
                    String searchBy = scanner.nextLine();
                    System.out.println("Enter search query:");
                    String query = scanner.nextLine();
                    switch (searchBy.toLowerCase()) {
                        case "title":
                            Book foundByTitle = library.findBookByTitle(query);
                            if (foundByTitle != null) {
                                System.out.println("Found: " + foundByTitle.getTitle() + " by " + foundByTitle.getAuthor());
                            } else {
                                System.out.println("Book not found.");
                            }
                            break;
                        case "author":
                            Book foundByAuthor = library.findBookByAuthor(query);
                            if (foundByAuthor != null) {
                                System.out.println("Found: " + foundByAuthor.getTitle() + " by " + foundByAuthor.getAuthor());
                            } else {
                                System.out.println("Book not found.");
                            }
                            break;
                        case "isbn":
                            Book foundByISBN = library.findBookByISBN(query);
                            if (foundByISBN != null) {
                                System.out.println("Found: " + foundByISBN.getTitle() + " by " + foundByISBN.getAuthor());
                            } else {
                                System.out.println("Book not found.");
                            }
                            break;
                        default:
                            System.out.println("Invalid search term.");
                            break;
                    }
                    break;
                case "exit":
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}

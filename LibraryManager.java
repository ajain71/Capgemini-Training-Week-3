import java.util.Scanner;
class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next;
    Book prev;
    Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}
class Library {
    Book head, tail;
    void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }
    void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }
    void addAtPosition(int pos, String title, String author, String genre, int bookId, boolean isAvailable) {
        if (pos <= 1 || head == null) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        Book temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        if (temp.next == null) {
            addAtEnd(title, author, genre, bookId, isAvailable);
            return;
        }
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;
    }
    void removeByBookId(int id) {
        if (head == null) return;
        if (head.bookId == id) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            return;
        }
        Book temp = head;
        while (temp != null && temp.bookId != id) {
            temp = temp.next;
        }
        if (temp == null) return;
        if (temp == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }
    void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }
    void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }
    void updateAvailability(int id, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == id) {
                temp.isAvailable = status;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found.");
    }
    void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        Book temp = head;
        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }
    void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }
        Book temp = tail;
        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }
    void displayBook(Book b) {
        System.out.println("Title: " + b.title + " | Author: " + b.author + " | Genre: " + b.genre + " | ID: " + b.bookId + " | Available: " + b.isAvailable);
    }
    void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total books: " + count);
    }
}
public class LibraryManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        while (true) {
            System.out.println("\n1. Add at beginning\n2. Add at end\n3. Add at position\n4. Remove by ID\n5. Search by Title\n6. Search by Author\n7. Update Availability\n8. Display Forward\n9. Display Reverse\n10. Count Books\n11. Exit");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    System.out.print("Enter title, author, genre, id, availability(true/false): ");
                    library.addAtBeginning(sc.nextLine(), sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine()), Boolean.parseBoolean(sc.nextLine()));
                    break;
                case 2:
                    System.out.print("Enter title, author, genre, id, availability(true/false): ");
                    library.addAtEnd(sc.nextLine(), sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine()), Boolean.parseBoolean(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Enter position, title, author, genre, id, availability(true/false): ");
                    int pos = Integer.parseInt(sc.nextLine());
                    library.addAtPosition(pos, sc.nextLine(), sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine()), Boolean.parseBoolean(sc.nextLine()));
                    break;
                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    library.removeByBookId(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    System.out.print("Enter Book Title to search: ");
                    library.searchByTitle(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter Author Name to search: ");
                    library.searchByAuthor(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Enter Book ID and new Availability(true/false): ");
                    library.updateAvailability(Integer.parseInt(sc.nextLine()), Boolean.parseBoolean(sc.nextLine()));
                    break;
                case 8:
                    library.displayForward();
                    break;
                case 9:
                    library.displayReverse();
                    break;
                case 10:
                    library.countBooks();
                    break;
                case 11:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
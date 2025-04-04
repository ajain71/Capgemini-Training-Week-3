import java.util.Scanner;
class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next;
    Movie prev;
    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}
class MovieList {
    Movie head;
    Movie tail;
    void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }
    void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }
    void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos <= 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        newMovie.next = temp.next;
        newMovie.prev = temp;
        temp.next.prev = newMovie;
        temp.next = newMovie;
    }
    void removeByTitle(String title) {
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) return;
        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }
    void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found for director: " + director);
    }
    void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with rating: " + rating);
    }
    void displayForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.next;
        }
    }
    void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.prev;
        }
    }
    void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }
}
public class MovieManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieList list = new MovieList();
        while (true) {
            System.out.println("\n1. Add at beginning\n2. Add at end\n3. Add at position\n4. Remove by title\n5. Search by director\n6. Search by rating\n7. Display forward\n8. Display reverse\n9. Update rating\n10. Exit");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Enter title, director, year, rating: ");
                    list.addAtBeginning(sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine()), Double.parseDouble(sc.nextLine()));
                    break;
                case 2:
                    System.out.print("Enter title, director, year, rating: ");
                    list.addAtEnd(sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine()), Double.parseDouble(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Enter position, title, director, year, rating: ");
                    int pos = Integer.parseInt(sc.nextLine());
                    list.addAtPosition(pos, sc.nextLine(), sc.nextLine(), Integer.parseInt(sc.nextLine()), Double.parseDouble(sc.nextLine()));
                    break;
                case 4:
                    System.out.print("Enter movie title to remove: ");
                    list.removeByTitle(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter director to search: ");
                    list.searchByDirector(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter rating to search: ");
                    list.searchByRating(Double.parseDouble(sc.nextLine()));
                    break;
                case 7:
                    list.displayForward();
                    break;
                case 8:
                    list.displayReverse();
                    break;
                case 9:
                    System.out.print("Enter movie title and new rating: ");
                    list.updateRating(sc.nextLine(), Double.parseDouble(sc.nextLine()));
                    break;
                case 10:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
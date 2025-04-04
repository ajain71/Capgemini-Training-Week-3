import java.util.Scanner;
class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;
    Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}
class TicketReservationSystem {
    Ticket head = null;
    Ticket tail = null;
    void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
        System.out.println("Ticket booked successfully.");
    }
    void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }
        Ticket current = head;
        Ticket prev = tail;
        boolean found = false;
        do {
            if (current.ticketId == ticketId) {
                found = true;
                if (current == head && current == tail) {
                    head = tail = null;
                } else {
                    prev.next = current.next;
                    if (current == head) head = current.next;
                    if (current == tail) tail = prev;
                }
                System.out.println("Ticket removed successfully.");
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head);
        if (!found) {
            System.out.println("Ticket ID not found.");
        }
    }
    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket current = head;
        System.out.println("\nBooked Tickets:");
        do {
            System.out.println("Ticket ID: " + current.ticketId + ", Name: " + current.customerName + ", Movie: " + current.movieName +
                               ", Seat: " + current.seatNumber + ", Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }
    void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }
        boolean found = false;
        Ticket current = head;
        System.out.println("\nSearch Results:");
        do {
            if (current.customerName.equalsIgnoreCase(keyword) || current.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("Ticket ID: " + current.ticketId + ", Name: " + current.customerName + ", Movie: " + current.movieName +
                                   ", Seat: " + current.seatNumber + ", Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);
        if (!found) {
            System.out.println("No ticket found with the given keyword.");
        }
    }
    void countTickets() {
        if (head == null) {
            System.out.println("Total booked tickets: 0");
            return;
        }
        int count = 0;
        Ticket current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);
        System.out.println("Total booked tickets: " + count);
    }
}
public class OnlineTicketReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();
        while (true) {
            System.out.println("\n1. Book Ticket\n2. Cancel Ticket\n3. Display Tickets\n4. Search Ticket\n5. Count Tickets\n6. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movie = sc.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seat = sc.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String time = sc.nextLine();
                    system.addTicket(id, name, movie, seat, time);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to cancel: ");
                    int removeId = sc.nextInt();
                    system.removeTicket(removeId);
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String keyword = sc.nextLine();
                    system.searchTicket(keyword);
                    break;
                case 5:
                    system.countTickets();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
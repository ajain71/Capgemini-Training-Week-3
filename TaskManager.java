import java.util.Scanner;
class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;
    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}
class TaskScheduler {
    Task head = null;
    Task current = null;
    void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
        if (current == null) current = head;
    }
    void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
        if (current == null) current = head;
    }
    void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos <= 1 || head == null) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        newTask.next = temp.next;
        temp.next = newTask;
    }
    void removeById(int id) {
        if (head == null) return;
        if (head.id == id && head.next == head) {
            head = current = null;
            return;
        }
        Task temp = head, prev = null;
        do {
            if (temp.id == id) {
                if (temp == head) {
                    Task last = head;
                    while (last.next != head) last = last.next;
                    head = head.next;
                    last.next = head;
                    if (current == temp) current = head;
                } else {
                    prev.next = temp.next;
                    if (current == temp) current = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }
    void viewCurrentAndNext() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Current Task: " + current.id + " | " + current.name + " | " + current.priority + " | " + current.dueDate);
        current = current.next;
    }
    void displayAll() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        do {
            System.out.println(temp.id + " | " + temp.name + " | " + temp.priority + " | " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }
    void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println(temp.id + " | " + temp.name + " | " + temp.priority + " | " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No task found with priority " + priority);
    }
}
public class TaskManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();
        while (true) {
            System.out.println("\n1. Add at beginning\n2. Add at end\n3. Add at position\n4. Remove by ID\n5. View current and move next\n6. Display all\n7. Search by priority\n8. Exit");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter id, name, priority, due date: ");
                    scheduler.addAtBeginning(Integer.parseInt(sc.nextLine()), sc.nextLine(), Integer.parseInt(sc.nextLine()), sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter id, name, priority, due date: ");
                    scheduler.addAtEnd(Integer.parseInt(sc.nextLine()), sc.nextLine(), Integer.parseInt(sc.nextLine()), sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter position, id, name, priority, due date: ");
                    int pos = Integer.parseInt(sc.nextLine());
                    scheduler.addAtPosition(pos, Integer.parseInt(sc.nextLine()), sc.nextLine(), Integer.parseInt(sc.nextLine()), sc.nextLine());
                    break;
                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    scheduler.removeById(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    scheduler.viewCurrentAndNext();
                    break;
                case 6:
                    scheduler.displayAll();
                    break;
                case 7:
                    System.out.print("Enter priority to search: ");
                    scheduler.searchByPriority(Integer.parseInt(sc.nextLine()));
                    break;
                case 8:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
import java.util.*;
class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;
    Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}
class Inventory {
    Item head;
    void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
    }
    void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newItem;
    }
    void addAtPosition(int pos, String name, int id, int quantity, double price) {
        if (pos <= 1 || head == null) {
            addAtBeginning(name, id, quantity, price);
            return;
        }
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++) {
            temp = temp.next;
        }
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = temp.next;
        temp.next = newItem;
    }
    void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }
    void updateQuantity(int id, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }
    void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                System.out.println(temp.name + " | " + temp.id + " | " + temp.quantity + " | " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }
    void searchByName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println(temp.name + " | " + temp.id + " | " + temp.quantity + " | " + temp.price);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item not found.");
    }
    void displayAll() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        Item temp = head;
        while (temp != null) {
            System.out.println(temp.name + " | " + temp.id + " | " + temp.quantity + " | " + temp.price);
            temp = temp.next;
        }
    }
    void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + total);
    }
    void sortInventory(String field, boolean ascending) {
        head = mergeSort(head, field, ascending);
    }
    Item mergeSort(Item head, String field, boolean ascending) {
        if (head == null || head.next == null) return head;
        Item mid = getMid(head);
        Item nextToMid = mid.next;
        mid.next = null;
        Item left = mergeSort(head, field, ascending);
        Item right = mergeSort(nextToMid, field, ascending);
        return merge(left, right, field, ascending);
    }
    Item getMid(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    Item merge(Item a, Item b, String field, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;
        Item result;
        boolean condition;
        if (field.equals("name")) {
            condition = ascending ? a.name.compareToIgnoreCase(b.name) <= 0 : a.name.compareToIgnoreCase(b.name) > 0;
        } else {
            condition = ascending ? a.price <= b.price : a.price > b.price;
        }
        if (condition) {
            result = a;
            result.next = merge(a.next, b, field, ascending);
        } else {
            result = b;
            result.next = merge(a, b.next, field, ascending);
        }
        return result;
    }
}
public class InventoryManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        while (true) {
            System.out.println("\n1. Add at beginning\n2. Add at end\n3. Add at position\n4. Remove by ID\n5. Update quantity\n6. Search by ID\n7. Search by name\n8. Display all\n9. Total inventory value\n10. Sort inventory\n11. Exit");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter name, id, quantity, price: ");
                    inventory.addAtBeginning(sc.nextLine(), Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()), Double.parseDouble(sc.nextLine()));
                    break;
                case 2:
                    System.out.print("Enter name, id, quantity, price: ");
                    inventory.addAtEnd(sc.nextLine(), Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()), Double.parseDouble(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Enter position, name, id, quantity, price: ");
                    int pos = Integer.parseInt(sc.nextLine());
                    inventory.addAtPosition(pos, sc.nextLine(), Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()), Double.parseDouble(sc.nextLine()));
                    break;
                case 4:
                    System.out.print("Enter item ID to remove: ");
                    inventory.removeById(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    System.out.print("Enter item ID and new quantity: ");
                    inventory.updateQuantity(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
                    break;
                case 6:
                    System.out.print("Enter item ID to search: ");
                    inventory.searchById(Integer.parseInt(sc.nextLine()));
                    break;
                case 7:
                    System.out.print("Enter item name to search: ");
                    inventory.searchByName(sc.nextLine());
                    break;
                case 8:
                    inventory.displayAll();
                    break;
                case 9:
                    inventory.calculateTotalValue();
                    break;
                case 10:
                    System.out.print("Sort by 'name' or 'price': ");
                    String field = sc.nextLine();
                    System.out.print("Ascending? (true/false): ");
                    boolean asc = Boolean.parseBoolean(sc.nextLine());
                    inventory.sortInventory(field, asc);
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
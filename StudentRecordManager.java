import java.util.Scanner;
class Student {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next;
    Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}
class StudentList {
    Student head;
    void addAtBeginning(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }
    void addAtEnd(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }
    void addAtPosition(int pos, int roll, String name, int age, String grade) {
        if (pos <= 0) return;
        if (pos == 1) {
            addAtBeginning(roll, name, age, grade);
            return;
        }
        Student newStudent = new Student(roll, name, age, grade);
        Student temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return;
        newStudent.next = temp.next;
        temp.next = newStudent;
    }
    void deleteByRollNumber(int roll) {
        if (head == null) return;
        if (head.rollNumber == roll) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != roll) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }
    void searchByRollNumber(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                System.out.println("Found: " + temp.rollNumber + " " + temp.name + " " + temp.age + " " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found.");
    }
    void displayAll() {
        Student temp = head;
        while (temp != null) {
            System.out.println(temp.rollNumber + " " + temp.name + " " + temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }
    void updateGrade(int roll, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                temp.grade = newGrade;
                return;
            }
            temp = temp.next;
        }
    }
}
public class StudentRecordManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();
        while (true) {
            System.out.println("\n1. Add at beginning\n2. Add at end\n3. Add at position\n4. Delete by roll\n5. Search by roll\n6. Display all\n7. Update grade\n8. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter roll, name, age, grade: ");
                    list.addAtBeginning(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 2:
                    System.out.print("Enter roll, name, age, grade: ");
                    list.addAtEnd(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 3:
                    System.out.print("Enter position, roll, name, age, grade: ");
                    list.addAtPosition(sc.nextInt(), sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 4:
                    System.out.print("Enter roll to delete: ");
                    list.deleteByRollNumber(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter roll to search: ");
                    list.searchByRollNumber(sc.nextInt());
                    break;
                case 6:
                    list.displayAll();
                    break;
                case 7:
                    System.out.print("Enter roll and new grade: ");
                    list.updateGrade(sc.nextInt(), sc.next());
                    break;
                case 8:
                    sc.close();
                    return;
            }
        }
    }
}
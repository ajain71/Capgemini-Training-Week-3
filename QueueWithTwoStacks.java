import java.util.Scanner;
import java.util.Stack;
class QueueUsingStacks {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    void enqueue(int data) {
        stack1.push(data);
        System.out.println(data + " enqueued.");
    }
    int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }
    boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
    void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        Stack<Integer> temp = new Stack<>();
        while (!stack2.isEmpty()) temp.push(stack2.pop());
        for (int item : temp) System.out.print(item + " ");
        while (!temp.isEmpty()) {
            int item = temp.pop();
            System.out.print(item + " ");
            stack2.push(item);
        }
        Stack<Integer> reversed = new Stack<>();
        for (int item : stack1) reversed.push(item);
        while (!reversed.isEmpty()) System.out.print(reversed.pop() + " ");
        System.out.println();
    }
}
public class QueueWithTwoStacks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QueueUsingStacks queue = new QueueUsingStacks();
        while (true) {
            System.out.println("\n1. Enqueue\n2. Dequeue\n3. Peek\n4. Display\n5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Enter value: ");
                    int val = sc.nextInt();
                    queue.enqueue(val);
                    break;
                case 2:
                    int removed = queue.dequeue();
                    if (removed != -1) System.out.println("Dequeued: " + removed);
                    break;
                case 3:
                    int front = queue.peek();
                    if (front != -1) System.out.println("Front: " + front);
                    break;
                case 4:
                    System.out.print("Queue: ");
                    queue.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
import java.util.Scanner;
class TextState {
    String content;
    TextState prev, next;
    TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}
class TextEditor {
    private TextState head = null;
    private TextState current = null;
    private int size = 0;
    private final int MAX_HISTORY = 10;
    void addState(String content) {
        TextState newState = new TextState(content);
        if (head == null) {
            head = newState;
            current = head;
            size = 1;
        } else {
            current.next = null;
            newState.prev = current;
            current.next = newState;
            current = newState;
            size++;
            if (size > MAX_HISTORY) {
                head = head.next;
                head.prev = null;
                size--;
            }
        }
    }
    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo performed.");
        } else {
            System.out.println("No more undo operations available.");
        }
    }
    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo performed.");
        } else {
            System.out.println("No more redo operations available.");
        }
    }
    void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: \"" + current.content + "\"");
        } else {
            System.out.println("Editor is empty.");
        }
    }
}
public class TextEditorUndoRedo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        while (true) {
            System.out.println("\n1. Type Text\n2. Undo\n3. Redo\n4. Show Current Text\n5. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();  
            switch (ch) {
                case 1:
                    System.out.print("Enter new text: ");
                    String text = sc.nextLine();
                    editor.addState(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayCurrentState();
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
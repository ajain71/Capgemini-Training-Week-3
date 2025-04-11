import java.util.Scanner;
public class FirstNegativeFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        int index = findFirstNegativeIndex(numbers);
        System.out.println("Index of first negative number: " + index);
        scanner.close();
    }
    public static int findFirstNegativeIndex(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                return i;
            }
        }
        return -1;
    }
}
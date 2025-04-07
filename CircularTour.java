import java.util.Scanner;
public class CircularTour {
    static int findStartingPoint(int[] petrol, int[] distance, int n) {
        int start = 0, deficit = 0, balance = 0;
        for (int i = 0; i < n; i++) {
            balance += petrol[i] - distance[i];
            if (balance < 0) {
                start = i + 1;
                deficit += balance;
                balance = 0;
            }
        }
        return (balance + deficit >= 0) ? start : -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of petrol pumps: ");
        int n = sc.nextInt();
        int[] petrol = new int[n];
        int[] distance = new int[n];
        System.out.println("Enter petrol and distance for each pump:");
        for (int i = 0; i < n; i++) {
            System.out.print("Pump " + (i + 1) + ": ");
            petrol[i] = sc.nextInt();
            distance[i] = sc.nextInt();
        }
        int start = findStartingPoint(petrol, distance, n);
        if (start == -1) {
            System.out.println("No possible tour. Cannot complete the circle.");
        } else {
            System.out.println("Start at petrol pump: " + start);
        }
    }
}
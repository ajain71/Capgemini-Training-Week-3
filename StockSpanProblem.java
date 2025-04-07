import java.util.Scanner;
import java.util.Stack;
public class StockSpanProblem {
    static void calculateSpan(int[] prices, int n, int[] span) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        span[0] = 1;
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            span[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of days: ");
        int n = sc.nextInt();
        int[] prices = new int[n];
        int[] span = new int[n];
        System.out.println("Enter stock prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        calculateSpan(prices, n, span);
        System.out.println("Stock Spans:");
        for (int i = 0; i < n; i++) {
            System.out.println("Day " + (i + 1) + ": " + span[i]);
        }
    }
}
import java.util.*;
public class PairWithGivenSum {
    public static List<int[]> findPairsWithSum(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        Set<String> printed = new HashSet<>();
        List<int[]> result = new ArrayList<>();
        for (int num : arr) {
            int complement = target - num;
            if (seen.contains(complement)) {
                int a = Math.min(num, complement);
                int b = Math.max(num, complement);
                String key = a + "," + b;
                if (!printed.contains(key)) {
                    result.add(new int[]{a, b});
                    printed.add(key);
                }
            }
            seen.add(num);
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr = {10, 15, 3, 7, 7, 10};
        int target = 17;
        List<int[]> pairs = findPairsWithSum(arr, target);
        if (pairs.isEmpty()) {
            System.out.println("No pairs found.");
        } else {
            System.out.println("Pairs with sum " + target + ":");
            for (int[] pair : pairs) {
                System.out.println(Arrays.toString(pair));
            }
        }
    }
}
import java.util.*;
public class ZeroSumSubarrays {
    public static List<List<Integer>> findZeroSumSubarrays(int[] arr) {
        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        int cumSum = 0;
        for (int i = 0; i < arr.length; i++) {
            cumSum += arr[i];
            if (cumSum == 0) {
                result.add(getSubarray(arr, 0, i));
            }
            if (sumMap.containsKey(cumSum)) {
                for (int startIndex : sumMap.get(cumSum)) {
                    result.add(getSubarray(arr, startIndex + 1, i));
                }
            }
            sumMap.computeIfAbsent(cumSum, k -> new ArrayList<>()).add(i);
        }
        return result;
    }
    private static List<Integer> getSubarray(int[] arr, int start, int end) {
        List<Integer> subarray = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            subarray.add(arr[i]);
        }
        return subarray;
    }
    public static void main(String[] args) {
        int[] arr = {3, 4, -7, 3, 1, 3, -4, -2, -2};
        List<List<Integer>> subarrays = findZeroSumSubarrays(arr);
        for (List<Integer> subarray : subarrays) {
            System.out.println(subarray);
        }
    }
}
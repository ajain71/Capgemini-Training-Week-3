import java.util.*;
public class TwoSum {
    public static int[] twoSumValues(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int complement = target - num;
            if (map.containsKey(complement)) {
                return new int[]{complement, num};
            }
            map.put(num, 1);
        }
        return new int[]{-1, -1};
    }
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSumValues(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
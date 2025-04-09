import java.util.*;
public class EmployeeIDSorter {
    public static void insertionSort(int[] ids) {
        int n = ids.length;
        for (int i = 1; i < n; i++) {
            int key = ids[i];
            int j = i - 1;
            while (j >= 0 && ids[j] > key) {
                ids[j + 1] = ids[j];
                j--;
            }
            ids[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        int[] employeeIDs = {105, 102, 110, 101, 108};
        insertionSort(employeeIDs);
        System.out.println(Arrays.toString(employeeIDs));
    }
}
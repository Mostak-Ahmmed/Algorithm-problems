
import java.util.ArrayList;
import java.util.List;

public class LIS1 {
    public static List<Integer> lis(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        int maxLIS = 1;
        int maxIndex = 0;

        // Initialize LIS values for all indexes
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
        }

        // Compute optimized LIS values
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLIS) {
                maxLIS = dp[i];
                maxIndex = i;
            }
        }

        // Build LIS from the computed values
        List<Integer> lis = new ArrayList<>();
        while (maxIndex != -1) {
            lis.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        }
        return lis;
    }

    public static void main(String[] args) {
        int[] nums = {9, 2,5,3,7,11,8,10,13,6};
        List<Integer> lis = lis(nums);
        
        System.out.println("Length of the Longest Increasing Subsequence: " + lis.size());
        System.out.println("Longest Increasing Subsequence: " + lis);
    }
}
//9, 2,5,3,7,11,8,10,13,6
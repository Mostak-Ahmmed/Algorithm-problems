

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonSubsequences {
    public static void main(String[] args) {
        String sequence1 = "AGGTAB";
        String sequence2 = "GXTXAYB";
        
        List<String> commonSubsequences = findCommonSubsequences(sequence1, sequence2);
        
        // Sort the common subsequences in descending order of length
        Collections.sort(commonSubsequences, (s1, s2) -> s2.length() - s1.length());
        
        // Print the common subsequences
        for (String subsequence : commonSubsequences) {
            System.out.println(subsequence);
        }
    }
    
    public static List<String> findCommonSubsequences(String sequence1, String sequence2) {
        int m = sequence1.length();
        int n = sequence2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        // Build the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sequence1.charAt(i-1) == sequence2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        List<String> subsequences = new ArrayList<>();
        backtrack(subsequences, "", sequence1, sequence2, m, n, dp);
        
        return subsequences;
    }
    
    public static void backtrack(List<String> subsequences, String current, String sequence1, String sequence2,
                                 int i, int j, int[][] dp) {
        if (i == 0 || j == 0) {
            subsequences.add(current);
            return;
        }
        
        if (sequence1.charAt(i-1) == sequence2.charAt(j-1)) {
            backtrack(subsequences, sequence1.charAt(i-1) + current, sequence1, sequence2, i-1, j-1, dp);
        } else {
            if (dp[i-1][j] >= dp[i][j-1]) {
                backtrack(subsequences, current, sequence1, sequence2, i-1, j, dp);
            }
            
            if (dp[i][j-1] >= dp[i-1][j]) {
                backtrack(subsequences, current, sequence1, sequence2, i, j-1, dp);
            }
        }
    }
}

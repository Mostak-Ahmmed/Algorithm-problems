
import java.util.Scanner;

public class StringDeletion {

    public static int minDeletions(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int commonLength = dp[s1.length()][s2.length()];
        int deletions = s1.length() + s2.length() - 2 * commonLength;
        return deletions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first string: ");
        String s1 = scanner.nextLine();

        System.out.print("Enter the second string: ");
        String s2 = scanner.nextLine();

        int minDeletions = minDeletions(s1, s2);
        System.out.println("Minimum deletions required: " + minDeletions);

        scanner.close();
    }
}

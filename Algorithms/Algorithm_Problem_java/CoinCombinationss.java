
import java.util.Scanner;

public class CoinCombinationss {
    public static void main(String[] args) {
        int[] coins = {1, 5, 10};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target amount: ");
        int targetAmount = scanner.nextInt();
        int combinations = countCoinCombinations(coins, targetAmount);
        System.out.println("Total combinations: " + combinations);
    }

    public static int countCoinCombinations(int[] coins, int targetAmount) {
        int[] dp = new int[targetAmount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= targetAmount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[targetAmount];
    }
}

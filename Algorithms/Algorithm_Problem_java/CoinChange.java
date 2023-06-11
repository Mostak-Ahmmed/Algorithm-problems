
import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of coin denominations: ");
        int numDenominations = scanner.nextInt();
        
        int[] coins = new int[numDenominations];
        int[] quantities = new int[numDenominations];

        System.out.println("Enter the coin denominations:");
        for (int i = 0; i < numDenominations; i++) {
            coins[i] = scanner.nextInt();
        }

        System.out.println("Enter the available quantities for each coin:");
        for (int i = 0; i < numDenominations; i++) {
            quantities[i] = scanner.nextInt();
        }

        System.out.print("Enter the target amount: ");
        int targetAmount = scanner.nextInt();

        scanner.close();

        int minCoins = minimumCoins(coins, quantities, targetAmount);
        System.out.println("Minimum number of coins required: " + minCoins);
    }

    private static int minimumCoins(int[] coins, int[] quantities, int targetAmount) {
        int[] dp = new int[targetAmount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int n = coins.length;

        for (int i = 0; i < n; i++) {
            int coin = coins[i];
            int quantity = Math.min(quantities[i], targetAmount / coin);

            for (int j = coin; j <= targetAmount; j++) {
                if (dp[j - coin] != Integer.MAX_VALUE && quantity > 0) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                    quantity--;
                }
            }
        }

        return dp[targetAmount] == Integer.MAX_VALUE ? -1 : dp[targetAmount];
    }
}

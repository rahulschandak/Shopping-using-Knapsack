import java.util.*;

public class knapsackBudget {
  static int[][] dp;
  static int[] cost;
  static int[] weight;
  static String[] name;
  static int n, C;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter your budget for today: ");
    C = sc.nextInt();
    System.out.print("Enter the number of items you plan to purchase: ");
    n = sc.nextInt();

    name = new String[n + 1];
    cost = new int[n + 1];
    weight = new int[n + 1];
    dp = new int[n + 1][C + 1];

    System.out.println("Enter the item name, cost per unit and weight (in lb) of each item:");
    for (int i = 1; i <= n; i++) {
      name[i] = sc.next();
      cost[i] = sc.nextInt();
      weight[i] = sc.nextInt();
    }

    int max_value = knapsack();
    List<String> items = getSelectedItems(name);

    System.out.println("For your budget, in a single trip, find the details below : ");
    System.out.println("The maximum weight you can carry is: " + max_value + " lbs");
    System.out.println("The items within this weight and budget will be: " + items);
  }

  static int knapsack() {
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= C; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (cost[i] <= j) {
          dp[i][j] = Math.max(dp[i-1][j], weight[i] + dp[i-1][j-cost[i]]);
        } else {
          dp[i][j] = dp[i-1][j];
        }
      }
    }
    return dp[n][C];
  }

  static List<String> getSelectedItems(String[] name) {
    List<String> items = new ArrayList<>();
    int j = C;
    for (int i = n; i >= 1; i--) {
      if (dp[i][j] > dp[i-1][j]) {
        items.add(name[i]);
        j = j - cost[i];
      }
    }
    Collections.reverse(items);
    return items;
  }
}

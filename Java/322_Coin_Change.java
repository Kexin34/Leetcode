// DP, 自底向上
class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i]存的是在目前金额为i时，所用coins最少的个数
       int[] dp = new int[amount + 1];
        int max = amount + 1; 
        Arrays.fill(dp, max);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++){
            // 对于目前金额i，尝试不同大小的coin，找到其中的min
            for (int j = 0; j < coins.length; j++){
                if (coins[j] <= i)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
// faster than 89.78% of Java 
// Time complexity : O(S*n). On each step the algorithm finds the next F(i) in n iterations, 
// where 1 ≤ i ≤ S. Therefore in total the iterations are S∗n.

// Space complexity :O(S). We use extra space for the memoization table.



// 记忆化递归
class Solution {
    int[] memo;
    
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        memo = new int[amount];
        return helper(coins, amount);
    }
    
    // rem: remaining coins after the last step; 
    // count[rem]: minimum number of coins to sum up to rem
    public int helper(int[] coins, int remain){
        if (remain < 0) return -1;      // not valid
        if (remain == 0) return 0;      // completed
        if (memo[remain - 1] != 0) return memo[remain - 1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        
        for (int coin : coins){
            int res = helper(coins, remain - coin);
            if (res >= 0 && res < min)
                min = res + 1;
        }
        memo[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[remain - 1];
    }
}
// faster than 20.92% of Java


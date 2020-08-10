// 完全背包问题
// 原始DP解法
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int [n + 1][amount + 1];
        
        // base case: dp[..][0] = 1 如果凑出的目标金额为 0，那么“无为而治”就是唯一的一种凑法。
        for (int i = 0 ; i <= n; i++)
            dp[i][0] = 1;
            
        //  i 是从 1 开始的，所以 coins 的索引是 i-1 时表示第 i 个硬币的面值。
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= amount; j++){
                if (j - coins[i- 1] >= 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];//不装 + 装本coin
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][amount];
    }
}
// faster than 32.98% of Java




// DP 空间优化
// dp[i][j] only rely on dp[i-1][j] and dp[i][j-coins[i]], 
// then we can optimize the space by only using one-dimension array.
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for (int coin : coins){
            // 对于每一个添加的硬币，我们将从金额0遍历到amount
            for (int i = coin; i <= amount; i++){
                // 对于每一个目标金额x，计算组合数dp[x] = dp[x] + dp[x - coin]
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
// faster than 100.00% of Java 
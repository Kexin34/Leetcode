// Recursion + Memorization 记忆化递归
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int memo[] = new int[n + 1];
        return climb(cost, memo, n);
    }
    
    public int climb(int[] cost, int memo[], int i){
        // min cost to climb to i-th step
        if (i <= 1) return 0;
        if (memo[i] > 0)
            return memo[i];
        memo[i] = Math.min(climb(cost, memo, i - 1) + cost[i - 1],
                           climb(cost, memo, i - 2) + cost[i - 2]);
        return memo[i];
    }
}
// faster than 87.74% of Java



// DP 动态规划 (重点)
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];// dp[i]: min cost before leaving i
        Arrays.fill(dp, 0);
        
        for (int i = 2; i <= n; i++)
            dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                             dp[i - 2] + cost[i - 2]);
        return dp[n];
    }
}
// faster than 87.74% of Jav


// DP 动态规划 (space优化), O(1) Space
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int pre1 = 0;
        int pre2 = 0;
        
        
        for (int i = 2; i <= n; i++){
            int cur = Math.min(pre1 + cost[i - 1],
                               pre2 + cost[i - 2]);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
//  faster than 87.74% of Java

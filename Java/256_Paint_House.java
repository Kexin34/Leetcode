// DP 模板

// dp[i][j]: 刷到第 i+1 房子用颜色j的最小花费
class Solution {
    public int minCost(int[][] costs) {
        if (costs.length == 0 || costs[0].length > 3) return 0;
        int n = costs.length;
        int[][] dp = costs;
        
        for (int i = 1; i < n; i++){
            for (int j = 0; j < 3; j++)
                dp[i][j] = dp[i][j] + Math.min(dp[i - 1][(j + 1) % 3], 
                                               dp[i - 1][(j + 2) % 3]);
        }
        return Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2] );
    }
}

// faster than 12.76% of Java



// 优化：由于只有红绿蓝三张颜色，所以就可以分别写出各种情况
class Solution {
    public int minCost(int[][] costs) {
        if (costs.length == 0 || costs[0].length > 3) return 0;
        int n = costs.length;
        
        for (int i = 1; i < n; i++){
            costs[i][0] += Math.min(costs[i-1][1],costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0],costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][1],costs[i-1][0]);
        }
        return Math.min(Math.min(costs[n-1][0], costs[n-1][1]), costs[n-1][2] );
    }
}
// faster than 71.86% of Java 
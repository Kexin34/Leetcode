// Greedy 解法：
// 对于 [a, b, c, d]，如果有 a <= b <= c <= d ，那么最大收益为 d - a。
// 而 d - a = (d - c) + (c - b) + (b - a) ，因此当访问到一个 prices[i] 
// 且 prices[i] - prices[i-1] > 0，那么就把 prices[i] - prices[i-1] 添加到收益中。
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int profit = 0;

        for (int i = 1; i < n; i++){
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
}
// 93%



// DP 股票问题模板解法
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        
        for (int i = 0; i < n; i++){
            if (i - 1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;     
            }               
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
// faster than 94.04% of Java


// DP空间优化后（滚动数组）
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++){
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
            dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i]);
        }
        return dp_i_0;
    }
}
// faster than 94.04% of Java

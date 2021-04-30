// dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
// dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
// 解释：相当于买入股票的价格升高了。
// 在第一个式子里减也是一样的，相当于卖出股票的价格减小了。


// 直接改写原模板（未优化）
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;
        
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }
}
//  faster than 10.92% of Java 


// DP 优化（不用dp数组）

class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++){
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_i_0 - prices[i] - fee);
        }
        return dp_i_0;
    }
}
// faster than 95.07% of Java

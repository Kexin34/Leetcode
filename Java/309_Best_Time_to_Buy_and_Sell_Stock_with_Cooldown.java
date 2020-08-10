// dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
// dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
// 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;      
        int dp_i_0 = 0;                     // 代表dp[i - 1][0]
        int dp_i_1 = Integer.MIN_VALUE;     // 代表dp[i - 1][1]
        int dp_pre2 = 0;                    // 代表dp[i - 2][0]
        
        for (int i = 0; i < n; i++){
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre2 - prices[i]);
            dp_pre2 = temp;
        }
        return dp_i_0;
    }
}
// faster than 100.00% of Java
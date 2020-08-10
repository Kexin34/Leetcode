class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) return 0;
        int n = prices.length;
        if (k > n / 2)      // 转化为普通股票问题
            return maxProfit(prices);

        int [][][] dp = new int[n][k + 1][2];
        
        for (int i = 0; i < n; i++){
            for(int t = k; t > 0; t--){
                if (i - 1 == -1){ /*处理 base case */
                    dp[i][t][0] = 0;
                    dp[i][t][1] = -prices[i];
                    continue;
                }
                dp[i][t][0] = Math.max(dp[i - 1][t][0], dp[i - 1][t][1] + prices[i]);
                dp[i][t][1] = Math.max(dp[i - 1][t][1], dp[i - 1][t - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }
    
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
// faster than 26.81% of Java



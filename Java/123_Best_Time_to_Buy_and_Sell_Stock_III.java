// 原始的动态转移方程，没有可化简的地方
// dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
// dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int maxK = 2;
        int [][][] dp = new int[n][maxK + 1][2];
        
        for (int i = 0; i < n; i++){
            for(int k = maxK; k > 0; k--){
                if (i - 1 == -1){ /*处理 base case */
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][maxK][0];
    }
}
// faster than 30.39% of Java 


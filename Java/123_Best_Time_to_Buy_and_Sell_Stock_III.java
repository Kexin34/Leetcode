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
                if (i == 0){ /*处理 base case */
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


// 上面模板的优化：dp滚动
/*
手动列举 k = 1, k = 2两种
dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
dp[i][1][1] = max(dp[i-1][1][1], -prices[i])

*/
class Solution {
    public int maxProfit(int[] prices) {
        int dp_i1_0 = 0;
        int dp_i1_1 = Integer.MIN_VALUE;
        int dp_i2_0 = 0;
        int dp_i2_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            // 先更新k = 2的，因为要用到dp[i-1][1][0]
            dp_i2_0 = Math.max(dp_i2_0, dp_i2_1 + price);
            dp_i2_1 = Math.max(dp_i2_1, dp_i1_0 - price);
            dp_i1_0 = Math.max(dp_i1_0, dp_i1_1 + price);
            dp_i1_1 = Math.max(dp_i1_1, -price);
        }
        return dp_i2_0;
    }
}
// faster than 99.29% of Java


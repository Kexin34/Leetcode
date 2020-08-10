// dp 股票模板
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        
        for (int i = 0; i < n; i++){
            if (i - 1 == -1){
                dp[i][0] = 0;
                // 解释：
                //   dp[i][0] 
                // = max(dp[-1][0], dp[-1][1] + prices[i])
                // = max(0, -infinity + prices[i]) = 0
                dp[i][1] = -prices[i];
                //解释：
                //   dp[i][1] 
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i]) 
                // = -prices[i]
                continue;     
            }               
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
        }
        return dp[n - 1][0];
    }
}
//  faster than 22.21% of Java



// DP 滚动数组优化
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++){
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, - prices[i]);
        }
        return dp_i_0;
    }
}
// faster than 99.42% of Java



// Greedy 解法：
// The idea is to find so far min price.
class Solution {
    public int maxProfit(int[] prices) {
         if (prices.length == 0) {
             return 0 ;
         }      
         int max = 0 ;
         int sofarMin = prices[0] ;
         for (int i = 0 ; i < prices.length ; ++i) {
             if (prices[i] > sofarMin) {
                 max = Math.max(max, prices[i] - sofarMin) ;
             } else{
                sofarMin = prices[i];  
             }
         }       
        return  max ;
     }
}
// faster than 99.42% of Java
// O(N) time and O(1) space
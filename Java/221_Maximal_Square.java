class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        // side length of the maximum square 
        int maxsqLen = 0;
        int[][] dp = new int[m + 1][n + 1]; // padding by 1
        
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (matrix[i - 1][j - 1] == '1') {
                    //matrix is start at [0][0], correspon to dp[1][1]
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), 
                                        dp[i - 1][j - 1]) + 1;
                    maxsqLen = Math.max(maxsqLen, dp[i][j]);
                }
            }
        }
        return maxsqLen * maxsqLen;
    }
}
// faster than 91.60% of Java online 
// Time complexity : O(mn). Single pass.
// Space complexity : O(mn). Another matrix of same size is used for dp

// dp空间优化后
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int [n + 1];
        int maxsqLen = 0;
        int prev = 0;
        
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                int temp = dp[j];   // 上面一格value
                if (matrix[i - 1][j - 1] == '1'){
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqLen = Math.max(maxsqLen, dp[j]);
                }else
                    dp[j] = 0;
                prev = temp;
            }
        }
        return maxsqLen * maxsqLen;
    }
}
// faster than 91.60% of Java online 
// Time complexity : O(mn). Single pass.
// Space complexity : O(n). Another array which stores elements in a row is used for dp.

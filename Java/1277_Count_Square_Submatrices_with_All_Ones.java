// DP 二维
class Solution {
    public int countSquares(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int sum = 0;
        int[][] dp = new int[m + 1][n+ 1];//dp[i+1][j+1]对应matrix[i][j]

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    sum += dp[i][j];
                }
            }
        }
        return sum;
    }
}
// faster than 97.86% of Java 
//Time O(MN)



// DP空间优化（一维）
class Solution {
    public int countSquares(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int sum = 0;
        int[] dp = new int[n+ 1];
        int prev = 0;

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                int temp = dp[j];   // 上面一格value
                if (matrix[i - 1][j - 1] == 1) {
                    dp[j] = Math.min(Math.min(dp[j-1], prev), dp[j]) + 1;
                    sum += dp[j];
                }else
                    dp[j] = 0;
                prev = temp;
            }
        }
        return sum;
    }
}
// faster than 97.86% of Java 
//Time O(MN)
//Space O(1)
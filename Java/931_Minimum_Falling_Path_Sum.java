// DP
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        
        for (int j = 0; j < n; j++)
            dp[0][j] = matrix[0][j];
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int down = (i < n) ? dp[i - 1][j] : 6666;
                int downLeft = (i < n  && j - 1 >= 0 ) ? dp[i - 1][j - 1] : 6666;
                int downRight = (i < n  && j + 1 < n ) ? dp[i - 1][j + 1] : 6666;
                dp[i][j] = matrix[i][j] + Math.min(down, Math.min(downLeft, downRight));
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++){
            min = Math.min(min, dp[n - 1][j]);
        return min;
    }
}
// Runtime: 4 ms, faster than 33.80% of Java
// time: mn
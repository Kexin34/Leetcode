class NumMatrix {
    private int[][] dp;
    
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m + 1][n + 1]; // padding
        for (int r = 1; r <= m; r++) {   //dp is padding by 1
            for (int c = 1; c <= n; c++) {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + matrix[r - 1][c - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - 
            dp[row2 + 1][col1] + dp[row1][col1];
    }
}
// faster than 99.97% of Java 
//Time complexity : O(1) time per query, O(mn) time pre-computation. 
// The pre-computation in the constructor takes O(mn) time. Each sumRegion query takes O(1) time.
//Space complexity : O(mn). The algorithm uses O(mn) space to store the cumulative region sum.
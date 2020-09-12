// 暴力解（TLE) 递归
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 1 || 1 == 0) return 1;
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }
}


// 解法一：二维DP
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // put number of paths equal to 1 for the first row and the first column. 
        for (int[] arr : dp)
            Arrays.fill(arr, 1);
        for (int r = 1; r < m; r++)
            for(int c = 1; c < n; c++)
                dp[r][c] = dp[r][c - 1] + dp[r - 1][c];
        return dp[m-1][n-1];
    }
}
// faster than 100.00% of Java


// 解法二：一维DP
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }
}
// faster than 100.00% of Java



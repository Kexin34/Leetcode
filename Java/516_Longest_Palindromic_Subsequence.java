class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        // base case: [i][i]单个字母自身是回文
        for (int i = 0; i < n; i++)
            dp[i][i] = 1;
        
        // 反着遍历，保证正确的状态转移,[i][j]
        for (int i = n - 1; i >= 0; i--){
            for (int j = i + 1; j < n; j++){
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        // 整个s的最长回文串长度（右上角）
        return dp[0][n - 1];
    }
}
// faster than 84.13% of Java
// 时间复杂度一般都是 O(n^2)
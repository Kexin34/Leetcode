// 最基础LCS问题
// dp[i][j]: 在A[i]和B[j] 最长公共数组长度

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
         // dp[i][j] a前i个和b前j个字符最长公共子序列
        int[][] dp = new int[n1 + 1][n2 + 1];//the dp start 1, end n
        
        for (int i = 1; i <= n1; i++){
            for (int j = 1; j <= n2; j++){
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i -1][j], dp[i][j - 1]);
            }
        }
        return dp[n1][n2];
    }
}
// faster than 89.36% of Java


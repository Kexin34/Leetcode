// 匹配类DP

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if (n3 != n1 + n2) return false;
        
        // dp[i][j]表示是s1的前i个字符和s2的前j个字符是否能组成s3的前i+j个字符
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        
        // initialization
        dp[0][0] = true;
        
        for (int i = 1; i <= n1; i++)//如果s2零个字符，取决于s1
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        for (int j = 1; j <= n2; j++)
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        
        
        for (int i = 1; i <= n1; i++){
            for (int j = 1; j <= n2; j++){
                // (选s1[i]，选s2[j])
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        
        return dp[n1][n2];
    }
}
// Runtime: 2 ms, faster than 80.92% of Java 
// Time complexity: O(mn)
// Space complexity: O(mn)


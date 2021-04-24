// DP
// dp[i][j]表示S的前i个字符中选取T的前j个字符，有多少种方案

class Solution {
    public int numDistinct(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        int[][]dp = new int[n1 + 1][n2 + 1];
        
        // 初始化, 无论是s的第几位，t选取前0个字符(null)的方案都是1种
        for (int i = 0; i <= n1; i++)
            dp[i][0] = 1;
        for (int j = 1; j <= n2; j++)//s第0位(null)，方案肯定是0
            dp[0][j] = 0;
        
        for (int i = 1; i <= n1; i++){
            for (int j = 1; j <= n2; j++){
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    // 相等， dp = 两个都回退一位 + s回退一位看是否也可行
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else
                    // 不等，s可以回退一位来看，t不能动
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n1][n2];
    }
}
// Runtime: 8 ms, faster than 23.73% of Java 

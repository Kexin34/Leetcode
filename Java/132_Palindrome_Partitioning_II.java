// 解法一: 两个DP
/*
 * 一维的dp数组，其中dp[i]表示子串 [0, i] 范围内的最小分割数
判断子串的方法用的是之前那道 Palindromic Substrings 一样的方法，使用一个二维的dp数组p，
其中 p[i][j] 表示区间 [i, j] 内的子串是否为回文串，
其状态转移方程为 p[i][j] = (s[i] == s[j]) && p[i+1][j-1]，其中 p[i][j] = true if [i, j]为回文。
*/
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isP = new boolean[n][n];
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++){
            dp[i] = i;
            for (int j = 0; j <= i; j++){   //用 j 遍历区间 [0, j]
                // 验证的是区间 [j, i] 内的子串是否为回文串
                if(s.charAt(i) == s.charAt(j) && (i - j < 2 || isP[j + 1][i - 1])){
                    isP[j][i] = true;
                    dp[i] = (j == 0) ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                }
                    
            }
        }
        return dp[n - 1];
    }
}
// faster than 82.17% of Java
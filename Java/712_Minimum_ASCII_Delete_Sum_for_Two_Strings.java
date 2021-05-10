// 1、Recursion with memorization
class Solution {
    int[][] memo;
    public int minimumDeleteSum(String s1, String s2) {
        // 备忘录值为 -1 代表未曾计算
        memo = new int[s1.length()][s2.length()];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        
        return dp(s1, 0, s2, 0);
    }
    // 定义：将 s1[i..] 和 s2[j..] 删除成相同字符串，
    // 最小的 ASCII 码之和为 dp(s1, i, s2, j)。
    public int dp(String s1, int i, String s2, int j) {
        int res = 0;
        int m = s1.length(), n = s2.length();
        // base case
        if (i == m) {               // 如果 s1 到头了，那么 s2 剩下的都得删除
            for (;j < n; j++)
                res += s2.charAt(j);
            return res;
        }
        if (j == n) {               // 如果 s2 到头了，那么 s1 剩下的都得删除
            for (; i < m; i++) 
                res += s1.charAt(i);
            return res;
        }
        if (memo[i][j] != -1)
            return memo[i][j];
        if (s1.charAt(i) == s2.charAt(j))
            // s1[i] 和 s2[j] 都是在 lcs 中的，不用删除
            memo[i][j] = dp(s1, i + 1, s2, j + 1);
        else {
            // s1[i] 和 s2[j] 至少有一个不在 lcs 中，删一个
            memo[i][j] = Math.min(
                s1.charAt(i) + dp(s1, i + 1, s2, j),  // s1[i]不在
                s2.charAt(j) + dp(s1, i, s2, j + 1)); // s2[j]不在
        }
        return memo[i][j];
    }
}
// Runtime: 30 ms, faster than 21.73% of Java 



// DP  Table （更快）
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        // dp[i][j] := min delete sum of (s1.substr(0, i), s2.substr(0, j))
        int[][] dp = new int[l1 + 1][l2 + 1];
        
        // Initialization of first row and first col
        for (int i = 1; i <= l1; i++) //l2为0时，l1[i]取决于l1[i-1]+s1[i-1]
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        for (int j = 1; j <= l2; j++)
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        
        for (int i = 1; i <= l1; i++){
            for (int j = 1; j <= l2; j++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    // keep s1[i - 1] and s2[j - 1]
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1),// delete s1[i - 1]
                                       dp[i][j - 1] + s2.charAt(j - 1));// delete s2[j - 1]
            }
        }
        return dp[l1][l2];
    }
}
// faster than 87.21% of Java
// Time complexity: O(l1 * l2)
// Space complexity: O(l1 * l2)




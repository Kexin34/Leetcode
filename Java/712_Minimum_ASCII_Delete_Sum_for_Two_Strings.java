// (不成功的）（内存溢出）Recursion with memorization
class Solution {
    int[][]memo;
        
    public int minimumDeleteSum(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        memo = new int[l1 + 1][l2 + 1];
        for (int[] row: memo)
            Arrays.fill(row, Integer.MAX_VALUE);
        return dp(s1, s2);
    }
    
    public int dp(String s1, String s2){
        int i = s1.length(), j = s2.length();
        if (i == 0 && j == 0) return 0;
        if (memo[i][j] != Integer.MAX_VALUE) return memo[i][j];
        if (i == 0) // Case1: s1 is empty.
            return memo[i][j] = dp(s1, s2.substring(j - 1)) + s2.charAt(j - 1);
        if (j == 0) // Case2: s2 is empty.
            return memo[i][j] = dp(s1.substring(i - 1), s2) + s1.charAt(i - 1);
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) // Case3: skip s1[i-1] / s2[j-1]
            return memo[i][j] = dp(s1.substring(i - 1), s2.substring(j - 1));
        
        //else not equal, find the min(del s1[i-1], del s2[j-1])
        return memo[i][j] = Math.min(dp(s1.substring(i - 1), s2) + s1.charAt(i - 1), 
                                    dp(s1, s2.substring(j - 1) + s2.charAt(j - 1)));
        
    }
}



// DP 
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

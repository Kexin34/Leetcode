// 暴力解（递归）
class Solution {
    public int climbStairs(int n) {
        return climb(n);
    }
    
    public int climb(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        return climb(n - 1) + climb(n - 2);
    }
}
// TLE
// Time complexity : O(2^n), Size of recursion tree will be 2^n
// Space complexity : O(n). The depth of the recursion tree can go upto n.



// 解法一：Recursion with Memoization
// 0(n) complexity

class Solution {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climb(n, memo);
    }
    
    public int climb(int n, int[] memo){
        if (n == 1) return 1;
        if (n == 0) return 1;
        if (memo[n] > 0)
            return memo[n];
        memo[n] = climb(n - 1, memo) + climb(n - 2, memo);
        return memo[n];
    }
}
// faster than 100.00% of Java


// 解法二：Dynamic Programming
class Solution {
    public int climbStairs(int n) {
        // base case
        if (n == 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
}
// faster than 100.00% of Java
// 0(n) complexity



// 解法三：DP 滚动数组
class Solution {
    public int climbStairs(int n) {
        // dp[i] 只与 dp[i - 1] 和 dp[i - 2] 
        // base case
        if (n <= 2) return n;
        int pre2 = 1;       // dp[1] = 1;
        int pre1 = 2;       // dp[2] = 2;
        int cur = 0;
        
        for (int i = 3; i <= n; i++){
            cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
//faster than 100.00% of Java

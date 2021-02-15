
// 解法一：递归（暴力）
class Solution {
    public int fib(int N) {
        if (N <= 0) return 0;
        if (N == 1) return 1;
        return fib(N - 1) + fib(N - 2);
    }
}
// faster than 7.28% of Java


// （上面的优化）记忆化递归
class Solution {
    public int fib(int N) {
        if (N <= 1) return N;
        int[] memo = new int[N + 1];
        memo[1] = 1;
        return helper(N, memo);
    }
    
    public int helper(int N, int[] memo){
        if (memo[N] > 0) return memo[N];
        if (N <= 0) return 0;
        memo[N] = helper(N - 1, memo) + helper(N - 2, memo);
        return memo[N];
    }
}
// faster than 100.00% of Java



// 解法二：DP
class Solution {
    public int fib(int N) {
        if (N <= 1) return N;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[N];
    }
}
// faster than 100.00% of Java



// DP空间优化：采用滚动数组
class Solution {
    public int fib(int N) {
        if (N <= 1) return N;
        int pre2 = 0;
        int pre1 = 1;
        int cur = 0;
        
        for (int i = 2; i <= N; i++){
            cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
            
        return pre1;
    }
}
// faster than 100.00% of Java






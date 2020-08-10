// Recursion with Memoization
class Solution {
    int[] memo; 
    public boolean divisorGame(int N) {
        memo = new int[N + 1]; // initialize to 0
        return canWin(N) == 1 ? true : false;
    }
    
    public int canWin(int n){
        if (n == 1) return -1;
        if (memo[n] != 0) return memo[n];
        boolean win = false;
        for (int i = 1; i <= n/2; i++){
            if (n % i == 0){
                if (canWin(n - i) == -1){
                    win = true;
                    break;
                }
            }
        }
        memo[n] = win ? 1 : -1;
        return memo[n];
    }
}
// faster than 25.99% of Java
// Time complexity: O(n^2)
// Space complexity: O(n)


// DP 解法
class Solution {
    Boolean[] dp = null;
    
    public boolean divisorGame(int N) {
        dp = new Boolean[Math.max(2, N + 1)];
        // Don't worry about dp[0] as N > 1 always. 
        // Jusy to avoid adding one more condition when factor=1,
        dp[0] = true;
        dp[1] = false;
        boolean ans = helper(N);
        return ans;
    }
    
    private boolean helper(int n){
        if (dp[n] != null) return dp[n];     //已有
        dp[n] = false;
        for (int i = 1; i * i <= n; i++){
            if(n % i == 0)
                if (!helper(n - i) || !helper(n - (n / i))){
                    dp[n] = true;
                    break;
                }
        }
        return dp[n];
    }
}
//faster than 100.00% of Java


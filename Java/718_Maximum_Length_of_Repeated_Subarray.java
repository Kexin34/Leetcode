// DP
class Solution {
    public int findLength(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][]dp = new int[m + 1][n + 1];
        int ans = 0;
        
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (A[i - 1] == B[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
//faster than 95.04% of Java
// Time complexity: O(m*n)
// Space complexity: O(m*n) -> O(n)


// DP 空间优化（一位数组）
class Solution {
    public int findLength(int[] A, int[] B) {
        // 可优化； if (A.size() < B.size()) swap(A, B);
        int m = A.length;
        int n = B.length;
        
        int[] dp = new int[n + 1];
        int ans = 0;
        
        for (int i = 1; i <= m; i++){
            for (int j = n; j >= 1; j--){
                if (A[i - 1] == B[j - 1])
                    dp[j] = dp[j - 1] + 1;
                else
                    dp[j] = 0;
                ans = Math.max(ans, dp[j]);
            }
        }
        return ans;
    }
}
//faster than 80.99% of Java
// space: O(min(m,n)) 如果开头有swap那条的话

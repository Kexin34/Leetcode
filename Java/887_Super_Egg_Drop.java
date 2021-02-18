
// Dp 解法：

class Solution {
    public int superEggDrop(int K, int N) {
        int m = 0; // m 最多不会超过N次
        int[][] dp = new int[K + 1][N + 1];
        // BASE CASE: java 默认数组值为0
        // dp[0][...] = 0;
        // dp[...][0] = 0;
        
        while (dp[K][m] < N){
            m++;
            for (int k = 1; k <= K; k++)
                // 总的楼层数 = 楼上的楼层数 + 楼下的楼层数 + 1（当前这层楼）。
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
        }
        return m;
    }
}
// faster than 88.41% of Java
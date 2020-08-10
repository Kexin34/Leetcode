// DP 再加一点数学
// num_ways(i) = num_ways_diff(i) + num_ways_same(i)
// num_ways_diff(i) = num_ways(i-1) * (k-1)  
// num_ways_same(i) = num_ways_diff(i-1) * 1
// num_ways(i) = num_ways(i-1) * (k-1) + num_ways_diff(i-1) * 1
//             = num_ways(i-1) * (k-1) + num_ways(i-2) * (k-1) 
//             = (num_ways(i-1) + num_ways(i-2)) * (k-1)

class Solution {
    public int numWays(int n, int k) {
        if (n == 0) return 0;   // n=0 的话，说明没有需要刷的部分，直接返回0即可
        if (n == 1) return k;   // 如果只有一根柱子，有k种
        if (n == 2) return k * k; // 如果有两根，怎么都不会triplet
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k * k;
        
        for (int i = 3; i <= n; i++){
            // the recursive formula that we derived
            dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
        }
        return dp[n];
    }
}
// faster than 100.00% of Java
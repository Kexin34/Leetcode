/*
假设：A = [1,15,7,9,2,5,10], K = 3
Define A' to be a partition over A that gives max sum

#0
A = {1}
A' = {1} => 1

#1
A = {1, 15}
A' = {1}{15} => 1 + 15 = 16 X
A' = {1, 15} => {15, 15} = 30 AC

#2
A = {1, 15, 7}
A' = {1, 15} {7} = {15, 15} + {7} = 30 + 7 = 37 (k = 1, curMax = 7, dp[i - 1] = 30)
A' = {1}{15, 7}  = {1} + {15, 15} = 1 + 30 = 31 (k = 2, curMax = A[i-k+1] = 15, dp[i - 2] = 1)
A‘ = {1, 15, 7} = {15, 15, 15} = 45 AC （k = 3, curMax = 15, dp[i - 3] = 1)

#3
A = {1, 15, 7, 9}
A' = {1, 15, 7}{9} = 45 + 9 = 54 AC( k = 1, curMax = A[3] = 9, dp[2] = 45)
A' = {1, 15} {7, 9} = {15, 15}{9,9} = 48(k = 2, curMax = 9, dp[1] = 30)
A' = {1} {15, 7, 9}  = 1 + {15, 15, 15} = 46(k = 3, curMax = 15, dp[0] = 1)
*/

class Solution {
    //明确「状态」 -> 定义 dp 数组/函数的含义 -> 明确「选择」-> 明确 base case
    public int maxSumAfterPartitioning(int[] A, int K) {
        int N = A.length;
        int dp[] = new int[N];//represent the AC value of that partition
        
        for (int i = 0; i < N; i++){
            int curMax = 0;
            //以当前i开始，尝试所有可能性，找到能得到最大的k
            // 一般来说有k种可能性，但是确保i最左边的可能性（k-1）在数组里
            for (int k = 1; k <= K && i - (k - 1) >= 0; k++){
                curMax = Math.max(curMax, A[i - k + 1]); //找到可重复数字的最大数， k现在为可重复次数
                dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + curMax * k);
            }
        }
        return dp[N - 1];
    }
}
//faster than 85.90% of Java

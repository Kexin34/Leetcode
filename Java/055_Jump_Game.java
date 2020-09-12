// 解法一：（效率不高的DP）
class Solution {
    public boolean canJump(int[] nums) {
        // 思路：看最后一跳
        // 状态：dp[i] 表示是否能从0跳到i
        // 推导：dp[i] = OR(dp[j],j<i&&j能跳到i) 判断之前所有的点最后一跳是否能跳到当前点
        // 初始化：dp[0] = 0
        // 结果： dp[n-1]
        
        int n = nums.length;
        if (n == 1) return true;
        
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && nums[j] + j >= i)
                    dp[i] = true;
            }
        }
        return dp[n - 1];
    }
}
// faster than 15.70% of Java


// 解法：（最优）DP
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        
        int[] dp = new int[n];  // dp[i] 表示达到i位置时剩余的跳力
        
        for (int i = 1; i < n; i++){
            // 状态转移方程
            dp[i] = Math.max(dp[i - 1], nums[i - 1]) - 1;
            // 如果当某一个时刻 dp 数组的值为负了，说明无法抵达当前位置
            if (dp[i] < 0) return false;
        }
        return true;
    }
}
// faster than 99.42% of Java 
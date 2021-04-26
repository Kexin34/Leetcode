// 标准DP-背包问题解

class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        
        // 0-1背包
        // 前i个物品里面是否能取出一些来组成j
        // dp[i][j] means whether the specific sum j can be gotten from the first i numbers
        
        boolean[][] dp = new boolean[n + 1][target + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;
        for (int j = 1; j <= target; j++)
            dp[0][j] = false;
        
        // transition function is dp[i][j] = dp[i-1][j](不选) || dp[i-1][j-nums[i]](选)
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= target; j++){
                dp[i][j] = dp[i-1][j];
                if (nums[i-1] <= j) 
                    dp[i][j] = (dp[i][j] || dp[i - 1][j - nums[i - 1]]);
            }
        }
        return dp[n][target];
    }
}
// Runtime: 64 ms, faster than 22.65% of Java 




// 优化DP 花花酱解说
// 一维数组（因为sum反过来遍历所以不需要第二维[sum]
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int num : nums) {  //外圈遍历nums
            // 倒过来做，这样不需要另一个数组进行滚动（常见技巧）
            for (int i = target; i > 0; i--) {
                if (num <= i) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
// Runtime: 21 ms, faster than 70.36% of Java

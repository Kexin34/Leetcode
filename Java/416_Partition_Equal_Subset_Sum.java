// 标准DP-背包问题解
// 0-1背包
// 前i个物品里面是否能取出一些来组成j
// dp[i][j] means whether the specific sum j can be gotten from the first i numbers
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) sum += num;
        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        // base case, 因为背包没有空间的时候，就相当于装满了
        for (int i = 0; i <= n; i++) 
            dp[i][0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++){
                if (nums[i - 1] > j)
                    // 背包容量不足，不能装入第 i 个物品
                    dp[i][j] = dp[i - 1][j];
                else
                    // 不装入或装入背包
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[n][target];
    }
}
// Runtime: 40 ms, faster than 44.74% of Java




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

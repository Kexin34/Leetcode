// 标准DP-背包问题解
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        
        int target = sum / 2;
        // DP数组[物品索引][用量(包括0)]
        boolean[][] dp = new boolean[n][target + 1];
        
        //base case: 第0行，target sum为第一个nums数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target)
            dp[0][nums[0]] = true;
        
        for (int i = 1; i < n; i++){//外圈遍历nums
            for (int j = 0; j <= target; j++){//内圈遍历target
                // 先继承i- 1的再修正
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j){          // 恰好满足 
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j)
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }
        }
        return dp[n - 1][target];
    }
}
// faster than 39.24% of Java




// 优化DP 花花酱解说（不懂）
// 一维数组（因为sum反过来遍历所以不需要第二维[sum]
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0) return false;
        
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int num : nums){		//外圈遍历nums
            // 倒过来做，这样不需要另一个数组进行滚动（常见技巧）
            for (int i = sum; i >= 0; --i)
                if (dp[i] != 0)
                    dp[i + num] = 1;
            if (dp[sum / 2] != 0) return true;
        }
        return false;
    }
}
// faster than 88.50% of Java
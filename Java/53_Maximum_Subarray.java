
// 纯DP解法
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        // 建立DP
        for (int i = 1; i < n; i++)
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        
        // 找出DP中最大的value
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            res = Math.max(res, dp[i]);
        return res;
    }
}
// faster than 100.00% of Java 




// DP + GREEDY
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        int n = nums.length;
        int maxSum = nums[0];
        
        for (int i = 1; i < n; i++){
            nums[i] = nums[i - 1] > 0 ? nums[i - 1] + nums[i] : nums[i];
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }
}
// faster than 77.43% of Java 



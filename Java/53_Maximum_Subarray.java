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


// Recursion with memorization
class Solution {
    private int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return rob(nums, 0);
    }
    
    public int rob(int[] nums, int i){
        if (i >= nums.length) return 0;
        if (memo[i] != -1) return memo[i];
        memo[i] = Math.max(rob(nums, i + 2) + nums[i], rob(nums, i + 1));
        return memo[i];
    }
}
// faster than 100.00% 



// DP 滚动数组
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int pre2 = 0, pre1 = 0;
        int curr;
        for (int i = 0; i < n; i++){
            curr =  Math.max(pre2+ nums[i], pre1);
            pre2 = pre1;
            pre1 = curr;
        }
        return pre1;
    }
}
// faster than 100.00% 
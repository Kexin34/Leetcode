
// 
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前是连续递增
            if (i == 0 || nums[i - 1] < nums[i]){
                count++;
                res = Math.max(res, count);
            }else// 如果当前不是连续递增，reset计数器
                count = 1;
        }
        return res;
    }
}
// faster than 98.19% of Java


// dp solution
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];// 在i位置的时候最长长度
        
        dp[0] = 1;// 初始化，第一个元素为止，最长为1
        
        for (int i = 1; i < n; i++){
            if (nums[i] > nums[i - 1]){
                dp[i] = dp[i - 1] + 1;
            }else
                dp[i] = 1;
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
// Runtime: 1 ms, faster than 98.60% of Java
// 环状 -> 两种情况：要么第一间房子被抢最后一间不抢；要么最后一间房子被抢第一间不抢, 找出最大
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRange(nums, 0, n - 2), // 第一间房子被抢最后一间不抢
                        robRange(nums, 1, n - 1));// 最后一间房子被抢第一间不抢
    }
    
    // 仅计算闭区间 [start,end] 的最优结果
    public int robRange(int[] nums, int first, int last){
        int pre2 = 0, pre1 = 0;
        int cur = 0;
        for (int i = first; i <= last; i++){
            cur = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
// faster than 100.00% of Java
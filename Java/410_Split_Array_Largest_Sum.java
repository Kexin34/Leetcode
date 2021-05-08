// 二分查找
class Solution {
    public int splitArray(int[] nums, int m) {
        // 一般搜索区间是左开右闭的，所以 hi 要额外加一
        int lo = getMax(nums), hi = getSum(nums);
        while (lo < hi) {
            // 根据分割子数组的个数收缩搜索区间
            int mid = lo + (hi - lo) / 2;
            int n = split(nums, mid);
            if (n == m)             // 只要n <= m 满足都是满足条件，可以收缩右边
                hi = mid;           // 收缩右边界，达到搜索左边界的目的
            else if (n < m) 
                hi = mid;           // 最大子数组和上限高了，减小一些
            else
                lo = mid + 1;      // 最大子数组和上限低了，增加一些
        }
        return lo;
    }
    
    /* 辅助函数，若限制最大子数组和为 max，
       计算 nums 至少可以被分割成几个子数组 
    */
    public int split(int[] nums, int max) {
        // 至少可以分割的子数组数量
        int count = 1;
        // 记录每个子数组的元素和
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curSum + nums[i] > max){
                // 如果当前子数组和大于 max 限制
                // 则这个子数组不能再添加元素了
                count++;
                curSum = nums[i];
            }else
                // 当前子数组和还没达到 max 限制
                // 还可以添加元素
                curSum += nums[i];
        }
        return count;
    }

    // 计算数组中的最大值
    public int getMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums)
            max = Math.max(max, num);
        return max;
    }
    // 计算数组元素和
    public int getSum(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java 
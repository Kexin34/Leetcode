//求区间 i ~ j 的和，可以转换为 sum[j + 1] - sum[i]，其中 sum[i] 为 0 ~ i - 1 的和。
//变成动态规划

class NumArray {
    private int[] sums;
    
    public NumArray(int[] nums) {           
        int n = nums.length;
        //用来存储sums array,[0]为空/0，[len+1]为所有总和
        sums = new int[n + 1];
        for(int i = 1; i <= n; i++){
            sums[i] = sums[i - 1] + nums[i - 1];
        }
    }
    
    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

//  faster than 100.00% of Java
// 解法一：
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] fwd = new int[n];
        int[] bwd = new int[n];
        fwd[0] = 1;
        bwd[n - 1] = 1;
        
        // 从左向右计算forward，前面所有数字的乘积
        for (int i = 1; i < n; i++)
            fwd[i] = fwd[i - 1] * nums[i - 1];
        
        // 从右向左计算res，后面所有的数乘积
        for (int i = n - 2; i >= 0; i--)
            bwd[i] = bwd[i + 1] * nums[i + 1];
        
        // 计算ans,二者相乘就是我们要的结果
        for (int i = 0; i < n; i++)
            res[i] = fwd[i] * bwd[i];
        
        return res;
    }
}
// faster than 100.00% of Java
// Time: O(n)



// 解法二：Follow-up 空间优化
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        
        for (int i = 1; i < n; i++)// 算好res[i] = ith左边的乘积
            res[i] = res[i - 1] * nums[i - 1];
        
        // 左边乘积已有，开始从右往左乘上right，right每次乘上nums[i]，其实就是bwd逐渐形成
        int right = 1;
        for (int i = n - 1; i >= 0; i--){
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}
// faster than 100.00% of Java
// Time: O(n)
// Space: O(1)
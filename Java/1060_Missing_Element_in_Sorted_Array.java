class Solution {
    public int missingElement(int[] nums, int k) {
        int left = 0; 
        int right = nums.length - 1;
        int count = missingCount(nums, right);
        
        if (k > count)              //大于原array计算的missingCount，到右边外面计算
            return nums[right] + k - count;
        
        while (left < right){       //面试会问why position left is the lower bound of K?
            int mid = left + (right - left) / 2;
            count = missingCount(nums, mid);
            if (count >= k)
                right = mid;    //去左侧找  面试：why right = mid?
            else
                left = mid + 1;
        }    
        // 此时left就是搜索左侧边界的index
        return nums[left - 1] + k - missingCount(nums, left - 1);
    }
    
    public int missingCount(int[] nums, int idx){
        return nums[idx] - nums[0] - idx;
    }
}
// faster than 100.00% of Java
// [O(logn)]
// 跟33. Search in Rotated Sorted Array一本一样，唯一需要处理重复

class Solution {
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) 
                return true;
            
            // 如果左区间有序
            if (nums[mid] > nums[start] || nums[mid] > nums[end]){
                // 检查左边区间首尾，看目标是否在左边区间首尾内,是的话改右边界
                if (nums[start] <= target && target < nums[mid])
                    end = mid;
                else
                    start = mid;
            }
            // 如果右区间有序
            else if (nums[mid] < nums[end] || nums[mid] < nums[start]){  
                // 检查右边区间首尾，看目标是否在右边区间首尾内,是的话改左边界
                if (nums[mid] < target && target <= nums[end])
                    start = mid;
                else
                    end = mid;
            }else
            // 如果有重复元素，跳过
                end--;
        }
        
        if (nums[start] == target) return true;
        if (nums[end] == target) return true;
        return false;
    }
}
//  0 ms, faster than 100.00% of Java
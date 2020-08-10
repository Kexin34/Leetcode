// binary sreach fing the lower bound (找左侧边界)
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right){                       /*❶*/
            int mid = left + (right - right) / 2;
            if (nums[mid] >= nums[mid + 1])         /*❷*/
                //mid左边还有可能比mid大的，去左边搜索
                right = mid;                        /*❷*/ 
            else
                left = mid + 1;
        }
        return left;
    }
}
// faster than 100.00% of Java 
// ❶, ❷: all are key attributes of lower bound binary search template.
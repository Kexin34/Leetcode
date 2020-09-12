// 解法：二分查找
// 如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的
// 我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > nums[right]){  // 说明左边有序 
                // 检查左边区间首尾，看target是否在左边区间首尾内,是的话改右边界
                if (nums[left] <= target && nums[mid] > target)
                    right = mid - 1;
                else
                    left = mid + 1;
            }else{                               // 说明右边有序 
                // 检查右边区间首尾，看target是否在左边区间首尾内,是的话改右边界
                if (nums[mid] < target && nums[right] >= target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}
// faster than 100.00% of Java 
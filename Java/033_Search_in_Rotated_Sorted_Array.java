// 解法：二分查找
// 如果中间的数小于最右边的数，则右半段是有序的，若中间数大于最右边数，则左半段是有序的
// 我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
class Solution {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int mid;
        
        while (start + 1 < end) {
            mid = start +(end - start) / 2;
            
            if (nums[mid] > nums[start]){// 此时mid左边区间为有序
                // 检查左边区间首尾，看目标是否在左边区间首尾内,是的话改右边界
                if (nums[start] <= target && target < nums[mid])
                    end = mid; // 如果target在左区间，收缩右指针
                else
                    start = mid; // 如果target不在左区间，收缩左指针
            }else {// 此时mid右区间有序
                // 检查右边区间首尾，看目标是否在右边区间首尾内,是的话改左边界
                if (nums[mid] < target && target <= nums[end])
                    start = mid;// 如果target在右区间，收缩左指针
                else
                    end = mid;// 如果target不在右区间，收缩右指针
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}
// faster than 100.00% of Java 
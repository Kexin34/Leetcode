// binary search
// 思路：找到第一个 >= target 的元素位置
// 左边界
// 第一个 >= target的位置（first pos..找不着就返回当前元素个数（插末尾）
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) 
            return 0;
        int start = 0, end = nums.length - 1;
        int mid;
        
        // 标准的find first position模板
        while (start + 1 < end){
            mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                start = mid;
            else
                end = mid;
        }
        
        // 和模板的区别在这里：注意equal符号
        if (nums[start] >= target)
            return start;
        else if (nums[end] >= target)
            return end;
        else
            return end + 1;
    }
}
//Time complexity : O(logN).//master theorem
// Space: O(1)
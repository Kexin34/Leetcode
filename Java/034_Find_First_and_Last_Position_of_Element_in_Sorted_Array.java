/*
If A[mid] < target, then the range must begins on the right of mid (hence i = mid+1 for the next iteration)
If A[mid] > target, it means the range must begins on the left of mid (j = mid-1)
If A[mid] = target, then the range must begins on the left of or at mid (j= mid)
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0) return result;
        
        // 第一轮二分搜索：搜索左边的索引
        int start = 0, end = nums.length-  1;
        int mid;
        while (start < end){
            mid = start + (end - start) / 2;
            if (target > nums[mid])
                start = mid + 1;
            else // 继续向左找，就能找到第一个目标值的位置
                end = mid;
        }
        // 此时left/right都应该是左边target index
        if (nums[start] != target) return result;
        result[0] = start;
        
        // 第二轮二分搜索：搜索右边的索引
        end = nums.length - 1;      // start目前是左边target index，不要reset
        while (start < end){
            mid = (start + (end - start) / 2)  + 1;// Make mid biased to the right
            if (target < nums[mid])
                end = mid - 1;
            else // 继续向右找，就能找到最后一个目标值的位置
                start = mid;
        }
        result[1] = end;
        return result;
    }
}
// faster than 100.00% of Java
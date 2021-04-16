/*
If A[mid] < target, then the range must begins on the right of mid (hence i = mid+1 for the next iteration)
If A[mid] > target, it means the range must begins on the left of mid (j = mid-1)
If A[mid] = target, then the range must begins on the left of or at mid (j= mid)
*/

// 二分法模板，两次二分
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0) return result;
        
        // 第一轮二分搜索：搜索左边的索引
        int start = 0, end = nums.length-  1;
        int mid;
        while (start + 1 < end){
            mid = start + (end - start) / 2;
            if (nums[mid] == target)
                end = mid;
            else if (nums[mid] < target)
                start = mid;
            else
                end = mid;
        }
        if (nums[start] == target)
            res[0] = start;
        else if (nums[end] == target)
            res[0] = end;
        else{
            res[0] = res[1] = -1;
            return res;
        }

        
        // 第二轮二分搜索：搜索右边的索引
        end = nums.length - 1;      // start目前是左边target index，不要reset
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                start = mid;
            else if (nums[mid] < target)
                start = mid;
            else 
                end = mid;
        }
        if (nums[end] == target)
            res[1] = end;
        else if (nums[start] == target)
            res[1] = start;
        else{
            res[0] = res[1] = -1;
        }
        return res;
    }
}
// faster than 100.00% of Java
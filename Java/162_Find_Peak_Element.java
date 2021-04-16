// 二分法模板。 每次取中间元素，如果大于左右，则这就是peek。 
// 否则取大的一边，两个都大，可以随便取一边。最终会找到peek。

class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid - 1])
                end = mid;
            else if (nums[mid] < nums[mid + 1])
                start = mid;
            else
                // 如果大于左右，则这就是peek
                return mid;
        }
        
        if (nums[start] > nums[end])
            return start;
        else
            return end;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java
// 时间复杂度O (logN)
// 二分法模板
// 思路： 最后一个值作为target，然后往左移动，最后比较start、end的值
class Solution {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;

        while (start + 1 < end){
            int mid = start + (end - start) / 2;

            // 如果mid小于end，说明右半段有序，去左半边继续搜索
            if (nums[mid] < nums[end])
                end = mid;
            // 如果mid大于end，说明右边已rotate，最小数在右边
            else
                start = mid;
        }
        
        if (nums[start] < nums[end])
            return nums[start];
        else 
            return nums[end];
    }
}
// 0 ms, faster than 100.00% of Java




// 解法：分治
// T(n) = O(1) + T(n/2) = O(logn)
// Average: O(logn)
// Worst: O(n)
class Solution {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    
    public int findMin(int[] nums, int l, int r){
        // Based case: Only 1 or 2 elements
        if (l + 1 >= r) return Math.min(nums[l], nums[r]);
        
        // If sotred
        if (nums[l] < nums[r]) return nums[l];
        
        int mid = l + (r - l) / 2;
        return Math.min(findMin(nums, l, mid), 
                       findMin(nums, mid + 1, r));
    }
}
// Runtime: 0 ms, faster than 100.00% of Java 
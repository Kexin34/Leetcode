// 153. Find Minimum in Rotated Sorted Array
// 假设按照升序排序的数组在预先未知的某个点上进行了旋转( 例如，数组  [0,1,2,4,5,6,7] 可能变为
// [4,5,6,7,0,1,2] )。 请找出其中最小的元素。
// 思路： 最后一个值作为target，然后往左移动，最后比较start、end的值
class Solution {
    public int findMin(int[] nums) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;

        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right])    //数组没有旋转或者旋转点在左半段
                right = mid;            	// 去左半边继续搜索
            else    
                left = mid + 1;         	// 去右半段查找
        }
        return nums[right];
    }
}
// faster than 100.00% of Java


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
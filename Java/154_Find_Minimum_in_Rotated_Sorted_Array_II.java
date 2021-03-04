// 解法：二分查找
// 思路：跳过重复元素，mid值和end值比较，分为两种情况进行处理
class Solution {
    public int findMin(int[] nums) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right])    //数组没有旋转或者旋转点在左半段
                right = mid;                // 去左半边继续搜索
            else if (nums[mid] > nums[right])
                left = mid + 1;             // 去右半段查找
            else              //如果两者重复，右指针左移一位，略过一个相同数字
                right--;
        }
        return nums[right];
    }
}
//faster than 100.00% of Java
// 在最坏的情况下，比如数组所有元素都相同，时间复杂度会升到 O(n)




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

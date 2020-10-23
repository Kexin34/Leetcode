// 双指针解法
// 维护 nums[0..i] 无重复
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        int i = 0;                  // i指向的是新数组当前保存的数字，可以跟j比较
        for (int j = 1; j < n; j++){
            if (nums[j] != nums[i]){    //当fast遇到新元素
                i++;
                nums[i] = nums[j];
            }
        }
        // i是当前新数组末尾index，长度是i+ 1
        return i + 1;
    }
}
// faster than 100.00% of Java 
// Time  : O(n). Assume that n is the length of array. Each of i and j traverses at most nn steps.
// Space  : O(1)
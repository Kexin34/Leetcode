// 解法：双指针
class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++){
            if (nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
// faster than 100.00% of Java
// Time : O(n). Assume the array has a total of n elements, both i and j traverse at most 2n steps.
//Space : O(1).
// 三段翻转法

class Solution {
    public void rotate(int[] nums, int k) {
        
        if(nums == null || nums.length < 2)
            return;
        k = k % nums.length;
        
        // 注意，三段翻转法，这里不是直接把k用作index
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums,nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
        
    }
    
    private void reverse(int[] nums, int i, int j) {
        int tmp = 0;
        while (i < j){
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }
}
// Runtime: 1 ms, faster than 41.70% of Java
// DP，从官方解释里推倒出来的
class Solution {
    public int maxProduct(int[] nums) {
        // restart combo chain after zero.
        if (nums.length == 0 || nums == null) return 0;
        int len = nums.length;
        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;
        
        for (int i = 1; i < len; i++){
            int curr = nums[i];
            int temp_max = max_so_far;
            max_so_far = Math.max(Math.max(curr, max_so_far * curr), min_so_far * curr);
            min_so_far = Math.min(Math.min(curr, temp_max * curr), min_so_far * curr);
            
            result = Math.max(result, max_so_far);
        }
        return result;
    }
}
// faster than 95.83% of Java 
// Time complexity : O(N), where N is the size of nums. 
// The algorithm achieves linear runtime since we are going through nums only once.

// Space complexity : O(1)
// Since no additional space is consumed rather than variables which keep track of 
// the maximum product so far, the minimum product so far, current variable, temp variable, 
// and placeholder variable for the result.
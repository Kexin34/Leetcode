// Solution 1: Sorting
// Time complexity: O(nlogn)
// Space complexity: O(1)
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2)
            res += nums[i];
        
        return res;
    }
}
// Runtime: 10 ms, faster than 97.09% of Java
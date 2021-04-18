// Two Sum变形，用双指针
// 用一个变量来记录最接近答案的值

class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int minDiff = Integer.MAX_VALUE;
        int best = -1;
        
        while (i < j){
            int diff = Math.abs(nums[i] + nums[j] - k);
            if (minDiff > diff && nums[i] + nums[j] < k){
                minDiff = diff;
                best = nums[i] + nums[j];
            }
            if (nums[i] + nums[j] < k)
                i++;
            else
                j--;
        }
        return best;
    }
}
// Runtime: 1 ms, faster than 100.00% of Java
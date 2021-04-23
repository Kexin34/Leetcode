class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        
        for (i  = 0; i < nums.length; i++){
            while (j < nums.length && sum < target){
                sum += nums[j];
                j++;
            }
            // 找到第一个大于sum的j，此时j在第一个不符合的位置，所以下面长度不用加一
            if (sum >= target)
                    min = Math.min(min, j - i); 
            sum -= nums[i];
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
// Runtime: 1 ms, faster than 99.94% of Java
// Time: O(n)
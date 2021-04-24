// Sliding Window

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        
        for (int i  = 0; i < n; i++){
            if (i >= k) 
                sum -= nums[i - k];
            sum += nums[i];
            if (i + 1 >= k)
                max = Math.max(max, sum);
        }
        return (double) max / k;
    }
}
//Runtime: 2 ms, faster than 89.33% of Java
//Time complexity: O(n)
//Space complexity: O(1)
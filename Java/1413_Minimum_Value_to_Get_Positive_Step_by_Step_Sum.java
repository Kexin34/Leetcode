// 解法：Find the minimum prefix sum
// ans = – min(prefix_sum,  0)  +  1


class Solution {
    public int minStartValue(int[] nums) {
        int sum = 0;
        int minPrefixSum = 0;
        for (int n : nums){
            sum += n;
            minPrefixSum = Math.min(sum, minPrefixSum);
        }
        return 1- minPrefixSum; // make it a positive number and add 1 to it 
    }
}
// faster than 100.00% of Java 
// Time complexity: O(n)
// Space complexity: O(1)

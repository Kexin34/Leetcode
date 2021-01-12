// 遍历数组一次
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int res = 0;
        for (int num : nums){
            if (num == 1)
                count++;
            else{
                res = Math.max(res, count);
                count = 0;
            }
        }
        return Math.max(res, count);
    }
}
//  faster than 100.00% of Java
// Time : O(N), where N is the number of elements in the array.
// Space : O(1). We do not use any extra space.
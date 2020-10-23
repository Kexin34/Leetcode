// 遍历数组一次
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 1)
                count++;
            else{
                res = Math.max(res, count);
                count = 0;
            }
        }
        return Math.max(res, count);
    }
}
// Time : O(N), where N is the number of elements in the array.
// Space : O(1). We do not use any extra space.
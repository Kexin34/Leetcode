
// 
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前是连续递增
            if (i == 0 || nums[i - 1] < nums[i]){
                count++;
                res = Math.max(res, count);
            }else// 如果当前不是连续递增，reset计数器
                count = 1;
        }
        return res;
    }
}
// faster than 98.19% of Java
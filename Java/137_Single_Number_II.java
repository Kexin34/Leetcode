class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        // 统计每位的‘1’的个数
        for (int i = 0; i < 32; i++){
            int sum = 0;    //sum记录了所有nums数字在i位共有多少个‘1’
            // 遍历nums中每一个数
            for (int num : nums)
                sum += (num >> i) & 1;      // 在i位统计1的个数
            
            // 还原位00^10=10 或者用| 也可以
            res = res | (sum % 3) << i;
        }
        return res;
    }
}
// faster than 83.06% of Java
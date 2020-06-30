// Method 1，异或运算抵消相同数字pair
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int ret = 0;
        // 先和新补的索引异或一下,因为i其实有0123...length，比nums[]多一个element
        ret ^= n;
        // 和其他的元素、索引做异或
        for(int i = 0; i < n; i++){
            ret = ret ^ i ^ nums[i];
        }
        return ret;
    }
}
//faster than 100.00% of Java,时间复杂度 O(N)，空间复杂度 O(1)

// Method 2 等差数列求和公式
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        // 公式：(首项 + 末项) * 项数 / 2
        int expect = (0 + n) * (n + 1) / 2;
        int sum = 0;
        for (int x : nums)
            sum += x;
        return expect - sum;
    }
}
//faster than 100.00% of Java


// Method 3: 让每个索引减去其对应的元素，再把相减的结果加起来，就是那个缺失的元素
public int missingNumber(int[] nums) {
    int n = nums.length;
    int res = 0;
    // 新补的索引
    res += n - 0;
    // 剩下索引和元素的差加起来
    for (int i = 0; i < n; i++) 
        res += i - nums[i];
    return res;
}
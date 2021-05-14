// 位运算
// 要把所有数字进行异或，成对儿的数字就会变成 0，
// 落单的数字和 0 做异或还是它本身，所以最后异或的结果就是只出现一次的元素：

class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums)
            res = res ^ num;
        return res;
    }
}
// faster than 100.00% of Java 
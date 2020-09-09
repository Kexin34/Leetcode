// 位运算：移除最后一个 1 : a = n & (n-1)
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {// isolate the rightmost 1-bit
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
// faster than 100.00% of Java
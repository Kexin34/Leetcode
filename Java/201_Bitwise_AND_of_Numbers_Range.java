// 解法一：平移
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        // 直接平移m和n，每次向右移一位，直到m和n相等，记录下所有平移的次数count，
        while (m != n){
            m >>= 1;
            n >>= 1;
            count++;
        }
        // 然后再把m左移count位即为最终结果
        return m << count;
    }
}
//  faster than 100.00% of Java 
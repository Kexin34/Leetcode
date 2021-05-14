//一个数如果是2的指数，那么它的二进制表示一定只含有一个1
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        
        // 去除最右边的1后应该是0
        return (n & (n - 1)) == 0;
    }
}
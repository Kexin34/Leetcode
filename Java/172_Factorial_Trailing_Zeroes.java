// 阶乘，找能分解出几个因子 5
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        long dividor = 5;
        while (dividor <= n){
            res += n / dividor;
            dividor *= 5;
        }
        return res;
    }
}
// Runtime: 2 ms, faster than 17.08% of Java

// 改写地更简单一些
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        for (int d = n; d / 5 > 0; d = d / 5){
            res += d / 5;
        }
        return res;
    }
}
// Runtime: 2 ms, faster than 17.08% of Java
// 时间复杂度是底数为 5 的对数，也就是 O(logN)
// 快速模幂算法
// 对乘法的结果求模，等价于先对每个因子都求模，然后对因子相乘的结果再求模
class Solution {
    private int base = 1337;
    
    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length - 1);
    }
    public int superPow(int a, int[] b, int index) {
        // 递归的 base case
        if (index < 0) return 1;
        
        // 取出最后一个数
        int last = b[index];
        // 将原问题化简，缩小规模递归求解
        int part1 = mypow(a, last);
        int part2 = mypow(superPow(a, b, index - 1), 10);
        // 合并出结果, 每次乘法都要求模
        return (part1 * part2) % base;
    }
    
    // 计算 a 的 k 次方然后与 base 求模的结果
    public int mypow(int a, int k) {
        // 对因子求模
        a %= base;
        int res = 1;
        for (int i = 0; i < k; i++) {
            // 这里有乘法，是潜在的溢出点
            res = res * a;
            // 对乘法结果求模
            res = res % base;
        }
        return res;
    }
}
// Runtime: 6 ms, faster than 61.81% of Java
// 每次调用 mypow 的时间复杂度就是 O(1)
// 整个算法的时间复杂度是 O(N)，N 为 b 的长度


// 递归优化求幂
public int mypow(int a, int k) {
    if (k == 0) return 1;
    a %= base;
    if (k % 2 == 1)
        // k 是奇数
        return (a * mypow(a, k - 1)) % base;
    else {
        // k 是偶数
        int sub = mypow(a, k / 2);
        return (sub * sub) % base;
    }
}






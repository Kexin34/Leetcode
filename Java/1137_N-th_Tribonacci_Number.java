// 优化后的DP

//Calculate next element d = a + b + c,
//let (a,b,c) = (b,c,d).
//Repeat this process n - 2 times;
class Solution {
    public int tribonacci(int n) {
        // if n=0 or n=1 then the tribonacci number is n itself 
        if (n < 2) return n;

         //dp[i] 只与 dp[i - 1] 和 dp[i - 2] dp[i - 3]
        int pre1 = 1;
        int pre2 = 1;
        int pre3 = 0;
        int cur;

        for (int i = 2; i < n; i++){
            cur = pre1 + pre2 + pre3;
            pre3 = pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
// faster than 100.00% of Java 
//Time O(N), Space O(1)
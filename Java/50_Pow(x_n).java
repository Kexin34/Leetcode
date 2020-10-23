// 递归

class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0){
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }
    
    public double fastPow(double x, long n){
        if (n == 0) return 1.0;
        if (n % 2 == 1)             
            return fastPow(x * x, n / 2) * x;   // odd
        else
            return fastPow(x * x, n / 2);   //even
    }
}
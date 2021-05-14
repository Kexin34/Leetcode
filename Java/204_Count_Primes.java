//暴力解法：时间复杂度 O(n^2)
int countPrimes(int n) {
    int count = 0;
    for (int i = 2; i < n; i++)
        if (isPrim(i)) count++;
    return count;
}

// 判断整数 n 是否是素数
boolean isPrime(int n) {
    for (int i = 2; i < n; i++)
        if (n % i == 0)
            // 有其他整除因子
            return false;
    return true;
}


// 解法一：埃拉托斯特尼筛法 Sieve of Eratosthenes
// 在每次找到一个素数时，将能被素数整除的数排除掉。

class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);// 将数组都初始化为 true

        for(int i = 2; i * i < n; i++){
            if(isPrime[i]){
                 // i 的倍数不可能是素数了
                for(int j = i * i; j < n; j += i)
                    isPrime[j] = false;
            }
        }
        int count = 0;
        for(int i = 2; i < n; i++){
            if(isPrime[i]) 
                count++;
        }
        return count;
    }
}
// Runtime: 52 ms, faster than 24.66% of Java
















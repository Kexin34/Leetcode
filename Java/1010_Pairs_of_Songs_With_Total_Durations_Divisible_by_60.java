class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int[] seconds = new int[60];    //对应各余数出现次数
        for (int t: time)
            seconds[t % 60] += 1;
        
        // 也可以用k * (k - 1) / 2表示
        count += combination(seconds[30], 2);
        count += combination(seconds[0], 2);

        // 用双指针分别从1和59两头向中间遍历
        int i = 1, j = 59;
        while (i < j)
            count += seconds[i++] * seconds[j--];
        
        return count;
    }
    
    private int combination(int n, int k){
        long result = 1;
        for (int i = 1; i <= k; i++)
            result = result * (n - i + 1) / i;
        return (int) result;
    }
}
// faster than 99.70% of Java 
// 时间复杂度：O(n)
// 空间复杂度：O(1) 固定空间开销（长度为60的数组）


//直接套公式也行
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        int[] seconds = new int[60];    //对应各余数出现次数
        for (int t: time)
            seconds[t % 60] += 1;
        
        // 组合：k * (k - 1) / 2表示
        count += seconds[0] * (seconds[0] - 1) / 2;
        count += seconds[30] * (seconds[30] - 1) / 2;
        
        for (int i = 1; i < 30; i++)
            count += seconds[i] * seconds[60 - i];
        
        return count;
    }
}
//  faster than 99.70% of Java 
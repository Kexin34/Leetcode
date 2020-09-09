// 移除最后一个 1 ： a = n & (n-1)
class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];   // 包括0
        for (int i = 0; i <= num; i++)
            res[i] = count(i);
        return res;
    }
    
    private int count(int num){
        int count = 0;
        while (num != 0){
            num = num & (num - 1);
            count++;
        }
        return count;
    }
}
// faster than 59.25% of Java 
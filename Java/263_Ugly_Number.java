//https://zxi.mytechroad.com/blog/?s=263.+Ugly+Number

class Solution {
    public boolean isUgly(int n) {
       int[] factors = new int[]{2, 3, 5};
       
        for (int factor: factors){
        	// 一直去被这三个数字整除
            while (n != 0 && n % factor == 0)
                n = n / factor;
        }
        // 判断最后是不是能被整除完
        return n == 1;
    }
}
// Runtime: 1 ms, faster than 100.00% of Java
// time: O(logn)
// space: O(1)
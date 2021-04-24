// 双指针模板
// 改为：last number that number ^ 2 <= x  
//  -->  number <= x/number

class Solution {
    public int mySqrt(int x) {
        if (0 == x) return 0;
        int left = 1, right = x;
        
        while (left + 1 < right){
            int mid = left + (right - left) / 2;
            if (mid <= x / mid)
                left = mid;
            else
                right = mid;
        }
        
        // 因为是找最后一个满足情况的，所以先判断right
        if (right <= x / right)
            return (int) right;
        return 
            (int) left;
    }
}
// Runtime: 1 ms, faster than 99.96% of Java

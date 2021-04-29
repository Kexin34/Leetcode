//https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/koko%E5%81%B7%E9%A6%99%E8%95%89.md

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        // 套用搜索左侧边界的算法框架
        int left = 1, right = getMax(piles) + 1;
        while(left < right){
            int mid = left + (right - left) / 2; // 防止溢出
            if(canFinish(piles, mid, H))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
    
    // 辅助函数
    // 时间复杂度 O(N)
    boolean canFinish(int[] piles, int speed, int H){
        int time = 0;
        for(int n : piles)
            time += timeOf(n, speed);
        return time <= H;
    }
    
    int timeOf(int n, int speed){
        return (n / speed) + ((n % speed > 0) ? 1 : 0);
    }
    
    int getMax(int[] piles){
        int max = 0;
        for(int n : piles)
            max = Math.max(n, max);
        return max;
    }
}
// Runtime: 15 ms, faster than 59.14% of Java


// 九章二分模板，找左边边界
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = getMax(piles); // speed可能性是在1-max_value之间
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (canFinish(piles, mid, h)) {
                r = mid;
            }else
                l = mid;
        }
        if (canFinish(piles, l, h)) 
            return l;
        else
            return r;
    }
    
    private int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr)
            max = Math.max(max, num);
        return max;
    }
    
    private boolean canFinish(int[] piles, int speed, int h) {
        int time = 0;
        for (int n : piles)
            time += (n / speed) + (n % speed > 0 ? 1 : 0);
        return time <= h;
    }
}
// Runtime: 15 ms, faster than 59.14% of Java

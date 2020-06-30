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


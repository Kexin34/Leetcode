//https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/koko%E5%81%B7%E9%A6%99%E8%95%89.md
class Solution {
    // 寻找左侧边界的二分查找
    public int shipWithinDays(int[] weights, int D) {
        // 载重可能的最小值
        int left = getMax(weights);
        // 载重可能的最大值 + 1   (Q: why +1 ????)
        int right = getSum(weights) + 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(canFinish(weights, D, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
    
    // 如果载重为 cap，是否能在 D 天内运完货物？
    boolean canFinish(int[] w, int D, int cap){
        int i = 0;
        for(int day = 0; day < D; day++){
            int maxCap = cap;
            while((maxCap -= w[i]) >= 0){//have some cap remain for that day
                i++;
                if(i == w.length)  // 当这天内，载运箱子数 = 所有w个数，成功
                    return true;
            }
        }
        return false;
    }   
    
    int getMax(int[] w){
        int max = 0;
        for(int n : w)
            max = Math.max(n, max);
        return max;
    }
    
    int getSum(int[] w){
        int sum = 0;
        for(int n : w)
            sum += n;
        return sum;
    }
}

// faster than 35.59% of Java 

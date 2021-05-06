// 区间问题

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // 按照起点升序排列，若起点相同时就按终点降序排列
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });
        
        // 记录合并区间的起点和终点
        int left = intervals[0][0];
        int right = intervals[0][1];
        int res = 0;
        
        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            // 情况一，找到覆盖区间
            if (right > intv[0] && right >= intv[1])
                res++;
            // 情况二，找到相交区间，合并
            else if (right > intv[0] && right < intv[1])
                right = intv[1];
            // 情况三，完全不相交，更新起点和终点
            else if (right < intv[0]){
                left = intv[0];
                right = intv[1];
            }
        }
        return intervals.length - res;
    }
}
// Runtime: 4 ms, faster than 97.47% of Java
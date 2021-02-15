class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        return n - intervalSchedule(intervals);
    }
    
    public int intervalSchedule(int[][] intervals) {
        if (intervals.length == 0) return 0;
        // 按 end 升序排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[]a, int[] b) {
                // 这里不能使用 a[1] - b[1]，要注意溢出问题
                if (a[1] < b[1])
                    return -1;
                else if (a[1] > b[1])
                    return 1;
                else return 0;
            }
        });
         // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
        int x_end = intervals[0][1];
        // 从区间集合 intvs 中选择一个区间 x，这个 x 是在当前所有区间中结束最早的（end 最小）。
        // 把所有与 x 区间相交的区间从区间集合 intvs 中删除。
        for (int [] interval: intervals){
            int start_time = interval[0];
            if (start_time >= x_end){
                // 找到下一个选择的区间了
                count++;
                x_end = interval[1];
            }
        }
        return count;
    }
}
// faster than 58.85% of Java 

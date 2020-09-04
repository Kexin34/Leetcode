// sort + 优先队列
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int max = 0;
        // 先按区间的 start 升序排列
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        
        // PQ，规则：按照结束时间从大到小排列（到时候poll最小（最早）的）
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            intervals.length, (a, b) -> (a[1] - b[1]));
        
        // 遍历所有intervals
        for (int i = 0; i < intervals.length; i++){
            // 每次新interval开头若大于pa最小的end time, pop the interval in the pq.
            while (!pq.isEmpty() && intervals[i][0] >= pq.peek()[1])
                pq.poll();
            pq.offer(intervals[i]);
            max = Math.max(max, pq.size());
        }
        return max;
    }
}
// faster than 78.16% of Java
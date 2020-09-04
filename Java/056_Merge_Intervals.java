class Solution {
	public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1)
            return intervals;
        // 按区间的 start 升序排列
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]>result = new ArrayList<>();//result is a list of merged intervals
        int[] newInterval = intervals[0];
        result.add(newInterval);
        
        for(int[] interval : intervals){// each interval is (a,b) 
            // a[0] means start value, [1] means end value
            // if the current interval does not overlap with the previous, simply append it.
            if(newInterval[1] < interval[0]){
                newInterval = interval;
                result.add(newInterval);
            }else
                newInterval[1] = Math.max(newInterval[1], interval[1]);
        }
        return result.toArray(new int[result.size()][]);
    }
}

// faster than 95.58% of Java  
// Time complexity :O(nlogn)
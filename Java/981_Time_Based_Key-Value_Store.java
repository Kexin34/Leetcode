// HashMap + Binary Search
// 二分前提：题目The timestamps in "set" operation are strictly increasing, it's already sorted.

class TimeMap {
    Map<String, List<Data>> map;  // Data:value+timestamp
    
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<String, List<Data>>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) 
            map.put(key, new ArrayList<Data>());
        map.get(key).add(new Data(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        // 在sorted list里面二分搜索时间戳，找到第一个小于等于target的value
        return binarySearch(map.get(key), timestamp);
    }
    
    // 二分搜索函数， find the last value that <= target.
    private String binarySearch(List<Data> list, int time) {
        int l = 0, r = list.size() - 1;
        
        while (l < r) {
            int mid = l + (r - l) / 2;
            int midTime = list.get(mid).time;
            if (midTime == time) return list.get(mid).val;
            if (midTime < time) {
                if (list.get(mid+1).time > time)// mid就是当前最后一个小于target的位置
                    return list.get(mid).val;
                l = mid + 1;
            }else
                r = mid - 1;
        }
        // 如果low（最后一个小于等于time的值）不满足，返回空
        return list.get(l).time <= time ? list.get(l).val : "";
    }
}

class Data {
    String val;
    int time;
    Data(String val, int time) {
        this.val = val;
        this.time = time;
    }
}
// Runtime: 124 ms, faster than 79.14% of Java


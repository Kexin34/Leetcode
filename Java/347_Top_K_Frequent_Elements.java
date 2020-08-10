//和692基本一模一样，按照692的code改了两行就可以直接跑

// 利用HashMap来记录元素和对于freq
// 要找k个最频繁word，可以用priority Queue来保存前k个（从大到小）

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[k];
        
        // 扔到hashmap中，key为string，value为freq count
        for (int i = 0; i < nums.length; i++){
            int count = map.getOrDefault(nums[i], 0);
            map.put(nums[i], count + 1);
        }
         // [重点]: priority queue的建立, 每个内容都是hashmap的entry<int,freq>
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            // 比较规则(freq, 如果freq相等，则比较keys（int)
            (a, b) -> a.getValue().equals(b.getValue()) ? b.getKey() - a.getKey() :
            a.getValue() - b.getValue()
        );
        
        // 把map里面entry放入priority queue, 只保留最大的k个
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            pq.offer(entry);    // 放入
            if (pq.size() > k)
                pq.poll();      // 先poll pq里面最小的
        }
        
        // 把pq里面(一共k个）的copy到答案list里面
        for (int i = k - 1; i >= 0; i--){
            result[i] = pq.poll().getKey();
        }
        return result;
    }
}
//faster than 55.78% of Java 
// O(nlogk) time & O(n) space


// 利用HashMap来记录word和对于freq
// 要找k个最频繁word，可以用priority Queue来保存前k个（从大到小）

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> result = new LinkedList<>();
        
        // 扔到hashmap中，key为string，value为freq count
        for (int i = 0; i < words.length; i++){
            int count = map.getOrDefault(words[i], 0);
            map.put(words[i], count + 1);
        }
        
        // [重点]: priority queue的建立, 每个内容都是hashmap的entry<str,freq>
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            // 比较规则(freq, 如果frequ相等，则比较keys（string)
            //  用equal(). '==' can be used only when the Integer is between -127 ~ 128.
            // grab the higher frequency and smaller alphabet
            (a, b) -> a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey())
            : a.getValue() - b.getValue()
        );
        
        // 把map里面entry放入priority queue, 只保留最大的k个
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            pq.offer(entry);    // 放入
            if (pq.size() > k)
                pq.poll();      // 先poll pq里面最小的
        }
        // 把pq里面(一共k个）的copy到答案list里面, 因为pq先pull最小的，所以要倒着插入list里（插到0）
        while (!pq.isEmpty())
            result.add(0, pq.poll().getKey());
        
        return result;
    }
}
//  faster than 44.79% of Java 
// O(nlogk) time & O(n) space
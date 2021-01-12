// 利用HashMap来记录word和对于freq
// 要找k个最频繁word，可以用priority Queue来保存前k个（从大到小）
// 这里用的是最小优先队列，从小到大递增排列，poll最小的，这样，当pq size大于k时，poll掉pq内最小的，保持里面都是最大的数字
// PQ: [day=1, sunny=2, is=3, the=4]，从头（最小）开始poll
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

            // frequ“递增”排列（ a.getValue() - b.getValue()
            // 对比word，需要“递减”（因为ahpa序列小的要先被poll）：b.getKey().compareTo(a.getKey())
            
            (a, b) -> a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey())
            : a.getValue() - b.getValue()
        );
        
        // 把map里面entry放入priority queue, 只保留最大的k个
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            pq.offer(entry);    // 放入
            if (pq.size() > k)
                pq.poll();      // 先poll pq里面最小的
        }
        // 把pq里面(一共k个）的copy到答案list里面, res是从大到小
        // 因为pq先poll最小的，所以要倒着插入list里（插到0）
        while (!pq.isEmpty())
            result.add(0, pq.poll().getKey());
        
        return result;
    }
}
// faster than 95.01% of Java 
// O(nlogk) time & O(n) space，满足Follow-up要求



// 同样算法，但是用的是最大优先队列(递减，字母从小到大递增)
// 缺点：把所有数字都存放在了pq内，空间不好
// pq打印：[the=4, sunny=2, is=3, haha=1, day=1]
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);
        
        PriorityQueue <Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            // 因为从大到小“递减”排列，所以frequ：b.getValue() - a.getValue()
            // 字母alpha从小到大“递增”， 所以a.getKey().compareTo(b.getKey())
            (a, b) -> a.getValue().equals(b.getValue()) ? 
                a.getKey().compareTo(b.getKey()) : 
                b.getValue() - a.getValue()
        );
        for (Map.Entry<String, Integer> entry : map.entrySet())
            pq.offer(entry);
        
        for (int i = 0; i < k; i++) {
            res.add(pq.poll().getKey());
        }
        return res;
    }
}
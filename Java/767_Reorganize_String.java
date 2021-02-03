// 优先队列
class Solution {
    public String reorganizeString(String S) {
        StringBuilder sb = new StringBuilder();
        
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()){
            int count = map.getOrDefault(c, 0) + 1;
            // Impossible to form a solution
            if (count > (S.length() + 1) / 2) return ""; 
            map.put(c, count);
        }
        
        // Greedy: fetch char of max count as next char in the result.
        // 建立优先队列，存pairs of (char, count) and sort by count值从大到小
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (char c : map.keySet())
            pq.add(new int[] {c, map.get(c)});
        
        // 每次从优先队列中取队首处理
        while (!pq.isEmpty()) {
            int[] t1 = pq.poll();
            // 如果当前最大项跟sb最后一个字母不同，放入答案
            if (sb.length() == 0 || t1[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) t1[0]);
                // 如果还有多余的字母，即减1后的次数仍大于0的话，将其再放回最大堆
                if (--t1[1] > 0) pq.add(t1);
            } else {
                int[] t2 = pq.poll();
                sb.append((char) t2[0]);
                if (--t2[1] > 0) pq.add(t2);
                pq.add(t1);
            }
        }
        return sb.toString();
    }
}
// faster than 63.71% of Java





// 同样解法，更好理解；
class Solution {
    public String reorganizeString(String S) {
        /* store char-frequency pair into map */
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        // push all map entry into priority element, by sorting from high frequency to low frequency
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> (b.getValue() - a.getValue())
        );
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
            
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            // store character with highest frequency in cache
            Map.Entry<Character, Integer> cache = pq.poll();
            // 如果当前最大项跟sb最后一个字母不同，放入答案
            if (sb.length() == 0 || cache.getKey() != sb.charAt(sb.length() - 1)) {
                sb.append(cache.getKey());
                cache.setValue(cache.getValue() - 1);

                // if current character still have more quota left, push back to queue
                if (cache.getValue() != 0) 
                    pq.offer(cache);
            }
            // if character in cache is same as tail character in current string
            // we need to try the character with second highest frequency
            else {
                Map.Entry<Character, Integer> cache2 = pq.poll();
                // corner case: if no more elements in queue, the input string should be invalid
                // because we do not have any other characters that different with current string tail
                if (cache2 == null) return "";
                
                sb.append(cache2.getKey());
                cache2.setValue(cache2.getValue() - 1);

                // if current character still have more quota left, push back to queue
                if (cache2.getValue() != 0) 
                    pq.offer(cache2);
                
                // DO NOT FORGET to push top frequency entry into queue as well
                pq.offer(cache);
            }
           
        }
        return sb.toString();
    }
}
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

// Topological Sorting
class Solution {
    public String alienOrder(String[] words) {
        StringBuilder result = new StringBuilder();
        if (words == null || words.length == 0) return "";
        //每个ch对应一set个在它之后的nodes
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        
        
        // 1. build a degree map for each character in all the words:
        for (String s : words)
            for (char ch : s.toCharArray())
                degree.put(ch, 0);
        
        // 2. 建图(hashmap) by comparing the adjacent words
        // 两个相邻单词的首个不同的字符，已经反映了其lexicographical order
        for (int i = 0; i < words.length - 1; i++){
            String cur = words[i];
            String next = words[i + 1];
            
            // 易错点：In a valid alphabet, prefixes are always first
            if (cur.length() > next.length() && cur.startsWith(next)) return "";
            
            int len = Math.min(cur.length(), next.length());
            // 逐一对比字符，找到首个不同的字符，此时ch1是在ch2前面，关系放入邻接表
            for (int j = 0; j < len; j++){
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2){  // order添加到graph中
                    Set<Character> set = new HashSet<>();
                    if (map.containsKey(c1)) set = map.get(c1);
                    if (!set.contains(c2)){
                        set.add(c2);
                        map.put(c1, set);
                        // ch1 -> c2，ch2的入度要更新
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        
        // 3. Topological sorting: keep adding elements whose in-degree is 0
        Queue<Character> queue = new LinkedList<>();
        for (char c : degree.keySet())
            if (degree.get(c) == 0) queue.offer(c);
        
        while (!queue.isEmpty()){
            char ch = queue.poll();
            result.append(ch);
            // 遍历其指向的nodes，对应的字母的入度减1,若此时入度减为0，则将对应的字母放入queue
            if (map.containsKey(ch)){
                for (char next : map.get(ch)){
                    degree.put(next, degree.get(next) - 1);
                    if (degree.get(next) == 0) queue.offer(next);
                }
            }
        }
        // 最后检查有无环（result存的字母量应该等于degree拥有的独特字母量）无其他全连接
        if (result.length() != degree.size()) return "";
        return result.toString();
    }
}
// Time: O(V+E) in topological sort part. 
// 因为Each node is pushed into queue once, and each edge is checked exactly once.
// Space: O(max(V, E)) while degree use O(V) and map uses O(E).

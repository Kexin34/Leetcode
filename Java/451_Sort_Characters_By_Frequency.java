// 更简单的方法（而且更高效）
// 先loop算一遍算freq存入hashmap，建立一个list，再根据每个char对应的freq（d），list中每个长度d都有一个array
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int len = s.length();
        
        // //用hashmap存储每个char的出现次数
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        //建立一个list，再根据每个char对应的freq（d），list中每个长度d都有一个list,里面有符合这长度的char
        List <Character>[] freqBucket = new ArrayList[s.length() + 1];//why +1?
        for (char c : map.keySet()){
            int f_value = map.get(c);
            if(freqBucket[f_value] == null)
                freqBucket[f_value] = new ArrayList<>();
            freqBucket[f_value].add(c);
        }
        
        //按照list里面freq顺序从大到小，读取各个独自list 放入string
        StringBuilder result = new StringBuilder();
        for (int i = freqBucket.length - 1; i >= 0; i--){
            if(freqBucket[i] == null)
                continue;
            for(char c : freqBucket[i]){//在这个freq值中可能有不同的字母，按照list储存.现处理每个字母重复输出
                for (int j = 0; j < i; j++)
                    result.append(c);
            } 
        }
        return result.toString();
    }
}
// faster than 91.68% of Java 



// 我按照692 hashmap + priorityqueue模板写的啰嗦版
// 最起码是自己写的
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int len = s.length();
        
        // 扔到hashmap中，key为Character，value为freq count
        for (int i = 0; i < len; i++){
            int count = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), count + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            // 比较规则(freq, 如果freq相等，则比较keys（Character)
            (a, b) -> a.getValue().equals(b.getValue()) ? b.getKey() - a.getKey() :
            a.getValue() - b.getValue()
        );
        
        for (Map.Entry<Character, Integer> entry : map.entrySet()){
            pq.offer(entry);    // 放入
        }
        
        // Convert the counts into a string with a sb.
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < map.size(); i++){
            Map.Entry<Character, Integer> entry = pq.poll();
            int copies = entry.getValue();
            for (int j = 0; j < copies; j++)
                result.append(entry.getKey());
        }
        result.reverse();  
        return result.toString();
    }
}
// faster than 40.46% of Java







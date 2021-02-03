class Pair {
    int time;
    String web;
    public Pair(int time, String web) {
        this.time = time;
        this.web = web;
    }
}

class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int n = username.length;
        
        // 构建哈希表map：收集每个用户的web info。 key: username, value: (timestamp, website)
        Map<String, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }
        
        // 构建一个count map来记录every 3 combination occuring time for the different user.
        Map<String, Integer> count = new HashMap<>();
        String res = "";

        for (String key : map.keySet()) {
            Set<String> set = new HashSet<>();   // 用set来避免访问same 3-seq in one user
            List<Pair> list = map.get(key);      // 当前user的所有web info pair
            Collections.sort(list, (a, b)->(a.time - b.time)); // sort by time

            // brutal force O(N ^ 3)
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                        // 找出所有 3-seq，放入count map中，更新出现频率，也放入set中
                        StringBuilder sb = new StringBuilder();
                        sb.append(list.get(i).web).append(" ").append(list.get(j).web).append(" ").append(list.get(k).web);
                        String str= sb.toString();

                        if (!set.contains(str)) {
                            count.put(str, count.getOrDefault(str, 0) + 1);
                            set.add(str);
                        }
                        if (res.equals("") 
                            || count.get(res) < count.get(str) 
                            || (count.get(res) == count.get(str) && res.compareTo(str) > 0)) {
                            // make sure the right lexi order
                            res = str;
                        }
                    }
                }
            }
        }
        // 把string拆成list of三个web作为答案，用空格分开
        return Arrays.asList(res.split(" "));
    }
}
// faster than 38.68% of Java
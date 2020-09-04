class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        
        // add banned words to set,就可以常数级时间内查询了
        Set<String> ban = new HashSet<>();
        for (String word : banned) ban.add(word);
        
        // 遍历每个字符，如果不是字母，就换成空格符号，如果是大写字母，就换成小写的
        // split paragraph "\\W" means matches the nonword characters.
        String[] parts = paragraph.toLowerCase().split("\\W+");
        
        // add paragraph words to hash map
        for (String s : parts){
            if (!ban.contains(s))
                map.put(s, map.getOrDefault(s, 0) + 1);
        }
        
        // get the most frequent word
        String res = "";
        int max = 0;
        for (String str : map.keySet()){
            if (map.get(str) > max){
                max = map.get(str);
                res = str;
            }
        }
        return res;
    }
}
// faster than 81.86% of Java 

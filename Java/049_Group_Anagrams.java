class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>(); 
        if (strs == null || strs.length == 0) return res;
        
        Map<String, List> map = new HashMap<>();
        // 每个单词先sort，把sort好的value当成这个统一错位词的key
        for (String s : strs){
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);// valueOf− 返回string representation
            
            // 如果哈希表不存在这个，just add a new array list
            if (!map.containsKey(key))
                map.put(key, new ArrayList());

            // 把这个错位词加到key为这个统一独特值的entry里
            // add the s into this arrayList(indexed by key)
            map.get(key).add(s);
        }

        for (List list : map.values()) 
            res.add(list);
        
        return res;
        //可以直接用return new ArrayList(map.values());
    }
}
// faster than 98.18% of Java
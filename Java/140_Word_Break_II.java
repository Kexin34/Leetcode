// memorized DFS
// Time complexity: O(2^n)
// Space complexity: O(2^n)

class Solution {
    protected Set<String> dict;
    protected HashMap<String, List<String>> memo = new HashMap<String, List<String>>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        // 先把wordDict变成Hashset，便于查找
        dict = new HashSet<>(); 
        for (String word : wordDict) 
            dict.add(word);
        
        return dfs(s);
    }
    public List<String> dfs(String s){
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0) return res;
        
        if(memo.containsKey(s)) return memo.get(s);
        
        if(dict.contains(s)) res.add(s);
        
        for (int i = 1; i < s.length(); i++){
            // 判断右边是否是dict里面单词
            String right = s.substring(i);
            if (dict.contains(right)){
                // 把左边的扔到递归查询
                List<String> temp = dfs(s.substring(0, i));
                // >> append({"cats and", "cat sand"}, "dog");
                // {"cats and dog", "cat sand dog"}
                if (temp.size() != 0){
                    for (int j = 0; j < temp.size(); j++)
                        res.add(temp.get(j) + " " + right);
                }
            }
        }
        memo.put(s , res);
        return res;
    }
}
// faster than 86.18% of Java
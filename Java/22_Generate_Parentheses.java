// dfs + backtracking
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }
    
    public void backtrack(List<String> result, String cur, int open, int close, int maxPair){
        if (cur.length() == maxPair * 2){
            result.add(cur);
            return;
        }
        if (open < maxPair)// 还可以添加（
            backtrack(result, cur + "(", open + 1, close, maxPair);
        if (close < open) //当close < open, 还可以添加)， 不是对比close和max，否则会有））（
            backtrack(result, cur + ")", open, close + 1, maxPair);
    }
}
// faster than 88.10% of Java
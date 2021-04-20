// combination模板：DFS+回溯

class Solution {
    List<List<String>> res = new ArrayList<>();
    
    public List<List<String>> partition(String s) {
        int n = s.length();
        dfs(s, 0, new ArrayList<String>());
        return res;
    }
    
    private void dfs(String s, int start, ArrayList<String> path){
        if (start == s.length()){
            res.add(new ArrayList(path));
            return;
        }
        for (int i = start; i < s.length(); i++){
            if (!isPalindrome(s, start, i)) continue;
            path.add(s.substring(start, i + 1));
            dfs(s, i + 1, path);
            path.remove(path.size() - 1);
        }
    }
    
    private boolean isPalindrome(String s, int l, int r){
        while (l < r){
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}
// Runtime: 7 ms, faster than 99.38% of Java
// Time complexity: O(2^n)
// Space complexity: O(n)
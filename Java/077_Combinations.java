// Combination DFS 模板

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combine(int n, int k) {
        dfs(n, 0, k, new ArrayList<>());
        return res;
    }
    
    private void dfs(int n, int start, int limit, ArrayList<Integer> path) {
        if (path.size() == limit){
            res.add(new ArrayList(path));
            return;
        }
        for (int i = start; i < n; i++) {
            path.add(i + 1);                // arr是[1-n]，start从0开始，需要加1
            dfs(n, i + 1, limit, path);
            path.remove(path.size() - 1);
        }
    }
}
// Runtime: 12 ms, faster than 82.85% of Java
// Time complexity: O(C(n, k)) = C(n, K) = (n! / k!) * (1 / (n - k)!)
// Space complexity: O(k)
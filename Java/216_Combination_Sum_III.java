// combination模板
// 区别：只需要寻找1-9

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k, 1, n, new ArrayList<Integer>());
        return res;
    }
    
    private void dfs(int limit, int start, int target, ArrayList<Integer> path){
        if (path.size() == limit){
            if (target == 0)
                res.add(new ArrayList(path));
        }
        for (int i = start; i <= 9; i++){
            if (i > target) break;
            path.add(i);
            dfs(limit, i + 1, target - i, path);
            path.remove(path.size() - 1);
        }
    }
}
// Runtime: 0 ms, faster than 100.00% of Java
// Time: C(m, k) = C(9, K) = (9! / k!) * (1 / (9 - k)!)
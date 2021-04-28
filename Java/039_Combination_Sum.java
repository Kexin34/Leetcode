// combiantion模板，重点：可以使用重复元素

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[]candidates, int start, int target, ArrayList<Integer> path){
        if (target == 0){
            res.add(new ArrayList(path));
            return;
        }
        for (int i = start; i < candidates.length; i++){
            if (candidates[i] > target) break;   // 剪枝
            path.add(candidates[i]);
            dfs(candidates, i, target - candidates[i], path);// 因为可以使用重复元素，所以i不加一
            path.remove(path.size() - 1);
        }
    }
}
// Runtime: 2 ms, faster than 98.74% of Java
// combination模板，重点是用set决绝重复答案，且每个元素只能用一次

class Solution {
    Set<List<Integer>> res = new HashSet<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<Integer>());
        return new ArrayList<>(res);
    }
    
    private void dfs(int[] candidates, int start, int target, ArrayList<Integer> path){
        if (target == 0){
            res.add(new ArrayList(path));
            return;
        }
        for (int i = start; i < candidates.length; i++){
            if (candidates[i] > target) break;
            path.add(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], path);//每个元素只能用一次
            path.remove(path.size() - 1);
        }
    }
}
// Runtime: 394 ms, faster than 19.14% of Java
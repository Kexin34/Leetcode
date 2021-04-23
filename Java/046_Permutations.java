// 解法：DFS + 回溯 （花花的permutation模板，推荐）
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        dfs(nums, visited, new ArrayList<Integer>());
        return res;
    }
    
    private void dfs(int[] nums, boolean[] visited, ArrayList<Integer> path){
        if (path.size() == nums.length){
            res.add(new ArrayList(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (visited[i] == true) continue;
            visited[i] = true;
            path.add(nums[i]);
            dfs(nums, visited, path);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
// Runtime: 1 ms, faster than 92.65% of Java
// Time complexity: O(n!)
// Space complexity: O(n)






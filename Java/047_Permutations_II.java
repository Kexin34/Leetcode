// 解法：DFS+回溯 （花花permutation模板）
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
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
            // 易错点：Same number can be only used once at each depth.
            // visited[i - 1] == false: we can make sure that 1b cannot be choosed before 1a
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false) continue;
            
            visited[i] = true;
            path.add(nums[i]);
            dfs(nums, visited, path);
            
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
//Runtime: 1 ms, faster than 99.35% of Java
// Time complexity: O(n!)
// Space complexity: O(n + k)
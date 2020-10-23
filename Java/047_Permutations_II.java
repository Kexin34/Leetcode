// 解法：DFS+回溯 （花花permutation模板）
class Solution {
    List<List<Integer>> res;
    boolean[] used;
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new LinkedList<>();
        if (nums.length == 0) return res;
        
        Arrays.sort(nums);          //因为考虑重复元素，所以先sort
        int n = nums.length;
        ArrayList<Integer> path = new ArrayList<>();
        used = new boolean[n];
        
        dfs(nums, path);
        return res;
    }
    
    private void dfs(int[] nums, ArrayList<Integer> path){
        if (path.size() == nums.length){
            res.add(new ArrayList(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (used[i]) continue;
            // 易错点：Same number can be only used once at each depth.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            
            used[i] = true;
            path.add(nums[i]);
            dfs(nums, path);
            
            path.remove(path.size() - 1);   // resume
            used[i] = false;
        }
        return;
    }
}
// faster than 63.41% of Java
// Time complexity: O(n!)
// Space complexity: O(n + k)
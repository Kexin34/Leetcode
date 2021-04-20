// Conmibation模板
// DFS + 回溯

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length; i++)
            dfs(nums, i, 0, new ArrayList<Integer>());
        return res;
    }
    
    private void dfs(int[] nums, int n, int start, ArrayList<Integer> path){
        if (path.size() == n)
            res.add(new ArrayList(path));
        for (int i = start; i < nums.length; i++){
            if (i > start && nums[i] == nums[i - 1]) continue;  // 跳过重复元素
            path.add(nums[i]);
            dfs(nums, n, i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}

// Runtime: 1 ms, faster than 99.61% of Java

// Time complexity: O(2^n * n)
// Space complexity: O(n)
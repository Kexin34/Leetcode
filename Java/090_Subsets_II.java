// DFS + 回溯

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length == 0) return res;
        Arrays.sort(nums);
        
        List<Integer> list = new ArrayList<>();
        dfs(nums, 0, res, list);
        return res;
    }
    
    private void dfs(int[] nums, int start, List<List<Integer>> res, List<Integer> list ){
        res.add(new ArrayList(list));
        if (list.size() == nums.length)     // 满足结束条件
            return;
        for (int i = start; i < nums.length; i++){  
            // 重点：对于重复数字，only the first number can be used.
            if (i > start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);              // 做选择
            dfs(nums, i + 1, res, list);    // dfs
            list.remove(list.size() - 1);   // 撤销选择
        }
        return;
    }
}
//  faster than 99.91% of Java
// Time complexity: O(2^n * n)
// Space complexity: O(n)
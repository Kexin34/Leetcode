// 解法：DFS + 回溯 （花花的permutation模板，推荐）
class Solution {
    boolean[] used;
    List<List<Integer>> res;
    
    public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>();
        if (nums.length == 0) return res;
        int n = nums.length;

        ArrayList<Integer> path = new ArrayList<>();
        used = new boolean[n];
        
        dfs(nums, 0, path);
        return res;
    }
    
    public void dfs(int[] nums, int depth, ArrayList<Integer> path){
        if (depth == nums.length){
            res.add(new ArrayList(path));
            return;
        }
        
        for (int i = 0; i < nums.length; i++){
            // 重点：本次path已经访问过的跳过
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            dfs(nums, depth + 1, path);
            
            path.remove(path.size() - 1);   // resume
            used[i] = false;
        }
        return;
    }
}
// faster than 92.88% of Java 
// Time complexity: O(n!)
// Space complexity: O(n)








// 官方版：DFS + backtracking （不推荐）
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        
        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_list = new ArrayList<Integer>();
        for (int num : nums)        //把input里的数字单独分别放入num_lst中
            nums_list.add(num);
        
        int len = nums.length;
        backtrack(len, nums_list, result, 0);
        return result;
    }
    
    public void backtrack(int len, 
                         ArrayList<Integer> nums,
                         List<List<Integer>> result,
                         int first){
         //if all integers in this turn are used up, add it into output list
        if (first == len)
            result.add(new ArrayList<Integer>(nums));
        
        for (int i = first; i < len; i++){
            // place i-th integer first in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(len, nums, result, first + 1);
            //backtrack
            Collections.swap(nums, first, i);
        }
    }
}
//faster than 61.86% of Java 
//Time complexity: O(n!)
//Space complexity: O(n)
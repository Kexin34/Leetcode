/*
背景:
	回溯法（backtrack）常用于遍历列表所有子集，是 DFS 深度搜索一种，一般用于全排列，穷尽所有可能，
	遍历的过程实际上是一个决策树的遍历过程。时间复杂度一般 O(N!)，它不像动态规划存在重叠子问题可以优化，
	回溯算法就是纯暴力穷举，复杂度一般都很高。

模板：
	result = []
	func backtrack(选择列表,路径):
	    if 满足结束条件:
	        result.add(路径)
	        return
	    for 选择 in 选择列表:
	        做选择
	        backtrack(选择列表,路径)
	        撤销选择

核心就是从选择列表里做一个选择，然后一直递归往下搜索答案，如果遇到路径不通，就返回来撤销这次选择。

	一般分为两大类：
		1. combination
		2. permutation

*/


///////////////////////////////// Combination ////////////////////////////////////

// 78. Subsets
// 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length == 0) return res;
        List<Integer> list = new ArrayList<>();
        // 遍历从0到nums.length作为新list的长度
        for (int i = 0; i <= nums.length; i++)
            dfs(nums, i, 0, res, list);
        return res;
    }
    
    private void dfs(int[] nums, int n, int start, List<List<Integer>> res, List<Integer> list ){
        if (list.size() == n){      // 满足结束条件
            res.add(new ArrayList(list));
            return;
        }
        for (int i = start; i < nums.length; i++){  // 选择 in 选择列表:
            list.add(nums[i]);
            dfs(nums, n, i + 1, res, list);
            list.remove(list.size() - 1);
        }
        return;
    }
}




// 90. Subsets II
// 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。说明：解集不能包含重复的子集。
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


///////////////////////////////// Permutation ////////////////////////////////////

// 46. Permutations
// 给定一个 "没有重复" 数字的序列，返回其所有可能的全排列
// 思路：需要记录已经选择过的元素，满足条件的结果才进行返回
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


// 47. Permutations II
// 给定一个可包含重复数字的序列，返回所有不重复的全排列
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






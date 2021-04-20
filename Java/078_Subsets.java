// 解法：DFS+回溯

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
            list.add(nums[i]);                      // 做选择
            dfs(nums, n, i + 1, res, list);         // dfs
            list.remove(list.size() - 1);           // 撤销选择
        }
        return;
    }
}
// faster than 100.00% of Java
// Time complexity: O(N×2^N) to generate all subsets(2^N) and then copy them into output list.
// Space: O(N×2^N) to keep all the subsets of length N, since each of N elements could be present or absent.





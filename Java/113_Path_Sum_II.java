// 解法：递归 + backtrack
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(root, targetSum, cur, res);
        return res;
    }
    
    public void dfs(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res){
        if (root == null) return;
        // 先添加至path
        cur.add(root.val);

        // 如果当前到达根节点，把path添加到result
        if (root.left == null && root.right == null){
            if (root.val == sum)
                res.add(new ArrayList<Integer> (cur));
            return;
        }
        // 如果还没到达根节点，递归继续，最后backtrack本节点
        if(root.left!=null) {
            dfs(root.left,sum-root.val,cur, res);
            cur.remove(cur.size()-1);
        }
        if(root.right!=null) {
            dfs(root.right,sum-root.val, cur, res);
            cur.remove(cur.size()-1);
        }
    }
}
// Runtime: 1 ms, faster than 99.97% of Java 
// TIME：0(NlogN）：
// Recursion time compexity should be nlogn, the max list length is logn, and n/2 leafs


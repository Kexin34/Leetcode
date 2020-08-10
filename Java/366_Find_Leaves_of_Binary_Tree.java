// DFS
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    // return the height of current nod
    public int helper(TreeNode node, List<List<Integer>> res){
        if (node == null) return -1;
        int depth = 1 + Math.max(helper(node.left, res), helper(node.right, res));
        // The height of a node is also the its index in the result list (res). 
        if (depth >= res.size())//如果新的高度比目前res最高还要高，添加对应list
            res.add(new ArrayList<>());
        res.get(depth).add(node.val);
        return depth;
    } 
}
// faster than 100.00% of Java online


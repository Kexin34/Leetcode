// 递归

class Solution {
    // 记录所有子树以及出现的次数
    Map<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    List<TreeNode> result = new ArrayList<>();
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return result;
    }
    
    public String traverse(TreeNode root) {
        if (root == null) // base case
            return "#";
        
        String left = traverse(root.left);
        String right = traverse(root.right);
        
        String subTree = left + "," + right + "," + root.val;
        
        int freq = memo.getOrDefault(subTree, 0);
        // 多次重复也只会被加入结果集一次
        if (freq == 1)
            result.add(root);
        // 给子树对应出现次数加一
        memo.put(subTree, freq + 1);
        return subTree;
    }
}
// Runtime: 20 ms, faster than 55.13% of Java
// Time: O(n)
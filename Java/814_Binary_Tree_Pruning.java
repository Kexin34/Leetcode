// 解法：递归
// Time complexity: O(n)
// Space complexity: O(h)

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return root;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        
        if (root.val == 1 || root.left != null || root.right != null)
            return root;
        else
            return null;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java

//543. Diameter of Binary Tree
//一个节点的最大路径是它左右子树深度之和

class Solution {
    public int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }
    
    public int helper(TreeNode node) {
        if (node == null) return 0;
        int leftDepth = helper(node.left);
        int rightDepth = helper(node.right);
        max = Math.max(max, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
// faster than 100.00% of Java 
// Time: O(N), traversal a binary tree each node
// Space: O(height), recursion
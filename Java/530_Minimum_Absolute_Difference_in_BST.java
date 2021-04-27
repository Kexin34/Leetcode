// DFS递归，中序遍历

class Solution {
    int minDiff = Integer.MAX_VALUE;
    TreeNode prev = null;
    
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return minDiff;
    }
    
    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);

        if (prev != null)
            minDiff = Math.min(minDiff, root.val - prev.val);
        prev = root;
        
        inOrder(root.right);
    }
}
// Runtime: 0 ms, faster than 100.00% of Java 
// 给定一个二叉树，判断它是否是高度平衡的二叉树。
// 思路：分治法，左边平衡 && 右边平衡 && 左右两边高度 <= 1，遍历一遍，
// 如果有左右两边高度 > 1，则不是高度平衡的二叉树。
class Solution {
    private boolean result = true;
    
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }
    
    private int maxDepth(TreeNode root){
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        if (Math.abs(left - right) > 1) 
            result = false;
        // 返回的相当于子树的最大高度
        return Math.max(left, right) + 1;
    }
}
// faster than 100.00% of Java
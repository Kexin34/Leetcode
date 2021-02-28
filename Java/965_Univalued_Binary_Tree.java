// 任意一种遍历

class Solution {
    int rootVal;
    boolean flag = true;
    
    public boolean isUnivalTree(TreeNode root) {
        rootVal = root.val;
        inOrder(root);
        return flag;
    }
    public void inOrder(TreeNode root){
        if (root == null) return;
        inOrder(root.left);
        if (root.val != rootVal) flag = false;
        inOrder(root.right);
    }
}
// Runtime: 0 ms, faster than 100.00% of Java
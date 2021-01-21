// 解法：递归，中序遍历反过来

class Solution {
    private int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;
        bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst(root.left);
        return root;
    }
}
//  faster than 100.00% of Java 
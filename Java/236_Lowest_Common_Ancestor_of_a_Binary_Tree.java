// 解法：递归，分治法
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	// 终止条件：为空或者找到当前是其中一个，返回当前节点
        if (root == null || root == p || root == q) return root;
        
        // 遍历左右子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // 若都不为空，说明p，q分别在左右子树中，当前就为最近公共祖先
        if (left != null && right != null) return root;
        // 否则返回非空子树节点（包含p或者q的子树）
        return left == null ? right : left;
    }
}
// faster than 100.00% of Java 
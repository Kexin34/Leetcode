class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 规定p.val < q.val
        if (p.val < q.val) return find(root, p, q);
        else return find(root, q, p);
    }
    
    public TreeNode find(TreeNode node, TreeNode p, TreeNode q) {
        if (p == node || q == node) return node;
        if (p.val < node.val && q.val > node.val)  //说明p, q分别在x左右子树中，x为最小公共节点
            return node;
        else if (q.val < node.val)                 // 说明p，q都在x的左子树中
            return find(node.left, p, q);
        else                                       // 说明p，q都在x的右子树中
            return find(node.right, p, q);
    }
}
// faster than 100.00% of Java
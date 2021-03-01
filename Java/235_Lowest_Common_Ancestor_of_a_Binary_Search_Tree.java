// 暴力递归
// Time complexity: O(n)
// Space complexity: O(n)
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        else if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        else
            return root;
    }
}
// Runtime: 7 ms, faster than 14.04% of Java 


// 优化后：递归
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
// Time: O(N), space : O(N)




// 改成迭代版
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null){
            if (node.val < p.val && node.val < q.val)
                node = node.right;
            else if (node.val > p.val && node.val > q.val)
                node = node.left;
            else
                return node;
        }
        return null;
    }
}
// Runtime: 12 ms, faster than 5.34% of Java 
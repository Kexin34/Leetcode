class Solution {
    public void flatten(TreeNode root) {
        // base case
        if (root == null) return;
        
        flatten(root.left);
        flatten(root.right);
        
        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;
        
        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null)
            p = p.right;
        p.right = right;
    }
}
// faster than 100.00% of Java 
// O(n


// 同样原理，非递归版
class Solution {
    public void flatten(TreeNode root) {
        TreeNode cur = root;

        while (cur != null){
            if (cur.left != null){
                //Find current node's prenode that links to current node's right subtree
                TreeNode rightMostL = cur.left;
                while (rightMostL.right != null)
                    rightMostL = rightMostL.right;
                
                // 左子树最右端的右子节点接上原根节点的右子树
                rightMostL.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}
// faster than 100.00% of Java
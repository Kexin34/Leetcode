class Solution {
    public void flatten(TreeNode root) {
        flattenTree(root);
    }
    
    // 返回的是已经flatten的子树最右端node（链的末端）
    public TreeNode flattenTree(TreeNode node){
        if (node == null) return null;
        // 如果本结点是leave，返回本结点
        if (node.left == null && node.right == null) 
            return node;
        
        // 递归左子树
        TreeNode leftTail = flattenTree(node.left);
        // 递归右子树
        TreeNode rightTail = flattenTree(node.right);
        
        // 如果存在左子树，开始shuffle the connections
        if (leftTail != null){
            // 把父节点和右子节点断开,把原右子节点连到原左子树末端的右子节点上
            leftTail.right = node.right;
            // 将原左子结点连上父节点的右子节点上
            node.right = node.left;
            node.left = null;   // 让左边没有
        }
        // We need to return the "rightmost" node after we are
        // done wiring the new connections. rightTail现在是flat的末端
        return rightTail == null? leftTail : rightTail;
    }
}
// faster than 100.00% of Java 


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
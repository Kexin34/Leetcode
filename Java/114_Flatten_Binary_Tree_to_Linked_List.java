class Solution {
    public void flatten(TreeNode root) {
        flattenTree(root);
    }
    
    private TreeNode flattenTree(TreeNode node){
        if (node == null) return null;
        if (node.left == null && node.right == null)
            return node;
        
        //Recursively flatten the left subtree
        TreeNode leftTail = flattenTree(node.left);
        // Recursively flatten the right subtree
        TreeNode rightTail = flattenTree(node.right);
        
        // If there was a left subtree, we shuffle the connections
        // around so that there is nothing on the left side anymore.
        if (leftTail != null){
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        // We need to return the "rightmost" node after we are
        // done wiring the new connections. rightTail现在是flat的末端
        return rightTail == null? leftTail : rightTail;
    }
}
// faster than 100.00% of Java 
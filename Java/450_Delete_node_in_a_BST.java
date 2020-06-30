class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        
        // delete from the right subtree
        if (key > root.val) root.right = deleteNode(root.right, key);
        // delete from the left subtree
        else if (key < root.val) root.left = deleteNode(root.left, key);
        // delete the current node
        else{
            // the node is a leaf
            if (root.left == null && root.right == null) root = null;
            // the node is not a leaf and has a right child, replaced by its successor 
            else if (root.right != null){
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child    
            else{
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
    
    /* successor: One step right and then always left */
    private int successor(TreeNode root){
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }
    
    /* predecessor: One step left and then always right */
    private int predecessor(TreeNode root){
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }
}

//faster than 100.00% of Java
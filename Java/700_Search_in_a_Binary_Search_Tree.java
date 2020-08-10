// recursion
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (val == root.val) 
            return root;
        else if(val < root.val) 
            return searchBST(root.left, val);
        else
            return searchBST(root.right, val);
    }
}
// faster than 100.00% of Java


// iteration
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && val != root.val){
            if (val > root.val) 
                root = root.right;
            else
                root = root.left;
        }
        return root;
    }
}
//faster than 100.00% of Java
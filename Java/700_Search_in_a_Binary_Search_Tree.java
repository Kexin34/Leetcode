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
// time: O(H), where H is a tree height. That results in O(logN) in the average case, andO(N) in the worst case.


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
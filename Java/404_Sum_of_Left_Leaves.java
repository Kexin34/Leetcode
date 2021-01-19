// 我自己写的递归
class Solution {
    int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return res;
    }
     
     public void helper(TreeNode root){
         if (root == null) return;
         // 当前左子节点是叶节点，数值添加到答案
         if (root.left != null && root.left.left == null && root.left.right == null) 
             res += root.left.val;
         helper(root.left);
         helper(root.right);
     }
}
// faster than 100.00% of Java
// less than 30.44% of Java




// 另一种递归答案
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (isLeaf(root.left))  //遍历到左叶节点
            return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
    
    public boolean isLeaf(TreeNode node) {
        if (node == null) return false;
        return node.left == null && node.right == null;
    }
}
// faster than 100.00% of Java, less than 77.33% of Java
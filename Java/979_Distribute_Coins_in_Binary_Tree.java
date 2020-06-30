
//Method 1: Write a dfs helper, return the number of coins given to the parent.
class Solution {
    int moves = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return moves;
    }
    private int dfs(TreeNode root){
        if(root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        moves += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }
}
//faster than 100.00% of Java


// Method 2:(也是DFS，但是没有全局变量，也不需要helper function)
// 其实不理解
class Solution {
    public int distributeCoins(TreeNode root) {
        int moves = 0;
        if(root.left != null){
            moves += distributeCoins(root.left);
            root.val += root.left.val - 1;
            moves += Math.abs(root.left.val - 1);
        }
        if(root.right != null){
            moves += distributeCoins(root.right);
            root.val += root.right.val - 1;
            moves += Math.abs(root.right.val - 1);
        }
        return moves;
    }
}
//faster than 100.00% of Java
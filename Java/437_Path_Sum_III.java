class Solution {
    //PathSum 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，和为目标值的路径总数。
    public int pathSum(TreeNode root, int sum) {
       if(root == null) return 0;
        int pathImLeading = count(root, sum); // 自己为开头的路径数
        int leftPathSum = pathSum(root.left, sum);// 左边路径总数（相信他能算出来）
        int rightPathSum = pathSum(root.right, sum); // 右边路径总数
        return leftPathSum + rightPathSum + pathImLeading;
    }
    
    // count 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中,
    // 能凑出几个以该节点为路径开头，和为目标值的路径总数。
    int count(TreeNode node, int sum){
        if(node == null) return 0;
        // 我自己能不能独当一面，作为一条单独的路径呢？
        int isMe = (node.val == sum) ? 1 : 0;
        // 左子树能凑几个 sum - node.val？
        int leftBrother = count(node.left, sum - node.val);
        // 右子树能凑几个 sum - node.val？
        int rightBrother = count(node.right, sum - node.val);
        return isMe + leftBrother + rightBrother;// 我这能凑这么多个
    }
}


//faster than 56.42% of Java 
// 二叉树递归
// 给定一个非空二叉树，返回其最大路径和。
// 思路：分治法，递归计算出左右子节点的最大贡献值，只有在最大贡献值大于 0 时，才会选取对应子节点； 
// 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值；
// 每次递归返回该节点的最大贡献值
class Solution {
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
    
    public int maxGain(TreeNode node){
        if (node == null) return 0;
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        
        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int curMaxSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, curMaxSum);
        
        // 每次递归返回该节点的最大贡献值,只能是单path，所以要挑max sub gain
        return node.val + Math.max(leftGain, rightGain);
    }
}
// Runtime: 0 ms, faster than 100.00% of Java
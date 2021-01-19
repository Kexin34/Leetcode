class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        // 递归条件分为三种：
        // 1. 左子节点和右子节点都为空时，说明到达叶节点，直接返回1即可
        if (root.left == null && root.right == null) return 1;
        
        // 2. 如果左子节点和右子节点其中一个为空，返回不为空的子节点深度+1
        int leftD = minDepth(root.left);
        int rightD = minDepth(root.right);
        
        if (root.left == null || root.right == null)
            return leftD + rightD + 1;
        
        // 3. 最后一种情况，左右子节点都不为空，返回最小深度+1即可
        return Math.min(leftD, rightD) + 1;
    }
}



// 稍微优化一下（当左右孩子为空时 m1和 m2 都为 0，可以和情况 2进行合并，即返回 m1+m2+1）
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int leftD = minDepth(root.left);
        int rightD = minDepth(root.right);

        // 1. 如果左子节点和右子节点有为空的情况，直接返回m1 + m2 + 1
        if (root.left == null || root.right == null)
            return leftD + rightD + 1;
        
        // 2. 最后一种情况，左右子节点都不为空，返回最小深度+1即可
        else
            return Math.min(leftD, rightD) + 1;
    }
}

class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        // 如果有子节点，那根节点就是最小的节点。如果没有子节点，意味着当前没有最小节点。
        if (root == null) return -1;
        if (root.left == null && root.right == null) return -1;
        
        // 选出候选数，默认就是子节点值
        int left = root.left.val;
        int right = root.right.val;
        
        // 如果子节点值和root值相等，递归，在子树中寻找候选数
        if (left == root.val) left = findSecondMinimumValue(root.left);
        if (right == root.val) right = findSecondMinimumValue(root.right);
        
        // 如果左右候选数都合法，返回较小值即可
        if (left != -1 && right != -1)
            return Math.min(left, right);
        // 如果候选数有不合法的，说明其整个子树中没有可供候选的树
        if (left != -1) return left;    //左子树正常，答案就在左边的候选数
        else return right;           //右子树正常，返回答案，若也无候选数，即-1位答案
    }
}
// faster than 100.00% of Java
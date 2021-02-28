// 解法：递归（Post-order traversal
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;
        
        // post-Order, 先递归检查左右子节点
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        
        // 同时满足三种情况才会返回空节点：左右都为空且本节点值为目标，否则返回本节点
         if (root.left == null && root.right == null && root.val == target)
             return null;
        else
            return root;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java


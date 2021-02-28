// 解法：递归
// There 3 cases in total depends on the root value and L, R
// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        
        if (root.val < low) // 当前节点值小于min，说明当前节点和左子节点都不要了，直接把修改了的右子节点接上去
            return trimBST(root.right, low, high);
        if (root.val > high) // 当前节点值大于max，说明当前节点和右子节点都不要，直接把修改了的左子节点接上去
            return trimBST(root.left, low, high);
        
        // 当前在值域范围内，左右继续递归修改
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
// faster than 100.00% of Java
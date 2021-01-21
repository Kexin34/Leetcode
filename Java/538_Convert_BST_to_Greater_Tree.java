
// 解法：递归，中序遍历，反过来

class Solution {
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
// faster than 100.00% of Java 



// 解法：迭代+栈（中序遍历非递归版）
class Solution {
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        
        // 在栈中有未遍历节点或者node节点不为空
        while (!stack.isEmpty() || node != null) {
            // 当前节点到最右边叶子节点路径上的节点全部入栈
            while (node != null){
                // 这样pop出来的时候，是从最大元素（最右node）开始
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            sum += node.val;
            node.val = sum;
            // 去处理它的左子节点，就像递归中先遍历当前节点再遍历它的左子树的思路
            node = node.left;
        }
        return root;
    }
}
//  faster than 19.08% of Java
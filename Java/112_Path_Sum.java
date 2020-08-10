// 递归 DFS
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        //如果当前节点是叶子，检查是否找到了给定的目标和。
        if ((root.left == null && root.right == null && root.val == sum)) 
            return true;

        //如果当前节点不是叶子，对它的所有孩子节点，递归调用 hasPathSum 函数
        return hasPathSum(root.left, sum - root.val) 
            || hasPathSum(root.right, sum - root.val);
    }
}
//  faster than 100.00% of Java



// 解法二：迭代
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();

            //如果当前节点是叶子，检查是否找到了给定的目标和。
            if (cur.left == null && cur.right == null)
                if (cur.val == sum) return true;

            // 左右子结点都需要加上其父结点值
            if (cur.left != null){
                cur.left.val += cur.val;
                stack.push(cur.left);
            }
            if (cur.right != null){
                cur.right.val += cur.val;
                stack.push(cur.right);
            }
        }
        return false;
    }
}
// faster than 28.43% of Java


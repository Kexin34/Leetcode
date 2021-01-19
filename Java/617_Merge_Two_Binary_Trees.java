// 解法一：递归

class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }
}
// faster than 31.37% of Java
// Time: O(N), O(N)



// 解法二：迭代
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        Queue<TreeNode[]> queue = new LinkedList<TreeNode[]>();
        queue.offer(new TreeNode[] {t1, t2});
        while (!queue.isEmpty()) {
            TreeNode[] t = queue.poll();
            TreeNode n1 = t[0];
            TreeNode n2 = t[1];
            n1.val += n2.val;
            // 如果两个都做左子节点，都入队列
            if (n1.left != null && n2.left != null)
                queue.offer(new TreeNode[]{n1.left, n2.left});
            //如果只有一个左子节点，那就作为第一个节点的左子节点
            else
                n1.left = n2.left == null ? n1.left : n2.left;
            
            if (n1.right != null && n2.right != null)
                queue.offer(new TreeNode[]{n1.right, n2.right});
            else
                n1.right = n2.right == null ? n1.right : n2.right;
        }
        return t1;
    }
}
// faster than 31.37% of Java

// 解法一：递归
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
// faster than 100.00% of Java, less than 96.55% of Java
// Time: O(N), space: O(H)



// 解法二：迭代
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // swap 左右节点
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            // 子节点入队列
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
            
        }
        return root;
    }
}
//  faster than 100.00% of Java, less than 66.64% of Java
// Time: O(N), space: O(N)
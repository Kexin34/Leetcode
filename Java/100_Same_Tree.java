// 递归
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
// 0 ms, faster than 100.00% of Java
// Time complexity : O(N), 
// Space complexity : O(log(N)) best case of completely balanced tree 
// O(N) in the worst case of completely unbalanced tree, to keep a recursion stack.


// 遍历
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()){
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();
            if (first == null && second == null) continue;
            if (first == null || second == null) return false;
            if (first.val != second.val) return false;
            queue.offer(first.left);
            queue.offer(second.left);
            queue.offer(first.right);
            queue.offer(second.right);
        }
        return true;
    }
}
// 0 ms, faster than 100.00% of Java
// Time complexity : O(N), 
// Space complexity : O(log(N)) best case of completely balanced tree 
// O(N) in the worst case of completely unbalanced tree, to keep a recursion stack.
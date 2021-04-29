// 递归-中序遍历

class Solution {
    private TreeNode x, y;
    private TreeNode prev  = null;
    
    public void recoverTree(TreeNode root) {
        inOrder(root);
        swap(x, y);
    }
    
    public void inOrder(TreeNode node){
        if (node == null) return;
        // 左
        inOrder(node.left);
        // 中
        if (prev != null && node.val < prev.val){
            y = node;
            if (x == null) x = prev;
            else return;    // 因为只有一对错误，当x已存在，可以立刻结束返回
        }
        prev = node;
        // 右
        inOrder(node.right);
    }
    
    public void swap(TreeNode a, TreeNode b){
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}
//faster than 100.00% of Java


// 遍历 - 中序遍历
class Solution {
    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode x = null, y = null, prev = null;
        
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null){
            // 把cur最左侧所有node压入
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            if (prev != null && node.val < prev.val){
                y = node;
                if (x == null) x = prev;
                else break;
            }
            prev = node;
            cur = node.right;
        }
        swap(x, y);
    }
    
    
    public void swap(TreeNode a, TreeNode b){
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}
// faster than 9.94% of Java
// Time complexity: O(n)
// Space complexity: O(h)







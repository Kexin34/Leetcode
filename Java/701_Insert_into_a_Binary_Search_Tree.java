// 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
// 思路：找到最后一个叶子节点满足插入条件即可

// 解法：递归分治法，DFS查找插入位置
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 终止条件：找到插入的位置，添加新的节点并返回
        if (root == null) 
            return new TreeNode(val);
        
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        else
            root.left = insertIntoBST(root.left, val);
        return root;
    }
}
// faster than 100.00% of Java online 


// 解法：遍历
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        
        TreeNode cur = root;
        while (cur.val != val){
            if (cur.val > val){     // go left
                if (cur.left == null) cur.left = new TreeNode(val);
                cur = cur.left;
            }else{                  // go right
                if (cur.right == null) cur.right = new TreeNode(val);
                cur = cur.right;
            }
        }
        return root;
    }
}
//  faster than 100.00% of Java

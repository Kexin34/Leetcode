/*
	定义:
		每个节点中的值必须大于（或等于）存储在其左侧子树中的任何值。
		每个节点中的值必须小于（或等于）存储在其右子树中的任何值。

*/

// 98. Validate Binary Search Tree
class Solution {
    private TreeNode preVal;
    
    public boolean isValidBST(TreeNode root) {
        preVal = null;
        return inOrder(root);
    }
    
    private boolean inOrder(TreeNode node){
        if (node == null) return true;
        
        if (!inOrder(node.left))
            return false;
        
        if (preVal != null && node.val <= preVal.val)
            return false;
        preVal = node;
        
        if (!inOrder(node.right))
             return false;
        
        return true;
    }
}

// 701. Insert into a Binary Search Tree
// 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 
// 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值
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



// 450. Delete Node in a BST
// 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的  key  对应的节点，
// 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val)
            root.left = deleteNode(root.left, key);
        else if (key > root.val)
            root.right = deleteNode(root.right, key);
        else{
            if (root.left == null && root.right == null) return null;
            else if (root.left != null){
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }else{
                root.val = sucessor(root);
                root.right = deleteNode(root.right, root.val);
            }
        }
        return root;
    }
    // 找前任：走一次左边，然后一直右边
    private int predecessor(TreeNode node){
        node = node.left;
        while (node.right != null) node = node.right;
        return node.val;
    }
    // 找下一任：走一次右边，然后一直左边
    private int sucessor(TreeNode node){
        node = node.right;
        while (node.left != null) node = node.left;
        return node.val;
    }
}



// 110. Balanced Binary Tree
// 给定一个二叉树，判断它是否是高度平衡的二叉树。
class Solution {
    private boolean result = true;
    
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }
    
    private int maxDepth(TreeNode root){
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        if (Math.abs(left - right) > 1) 
            result = false;
        // 返回的相当于子树的最大高度
        return Math.max(left, right) + 1;
    }
}
































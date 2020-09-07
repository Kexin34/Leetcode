// Solution#1: using min/max range to check the node value
// 递归法，判断左 MAX < 根 < 右 MIN
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isValidBST(root, null, null);
    }
    
    public boolean isValidBST(TreeNode node, Integer min, Integer max){
        if (node == null) return true; //base case: when the node has no child node

        // compare the node value with lower/upper limit
        if ((min != null && node.val <= min) || 
            (max != null && node.val >= max))
            return false;
        else
            return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }
}
//  faster than 100% of Java




// solution #2 : 递归版-中序遍历, check if the current node > previous node (if sorted)
// 中序遍历，如果中序遍历得到的节点的值小于等于前一个 preVal，说明不是二叉搜索树

class Solution {
    private TreeNode prev;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return inOrder(root);
    }
    
    private boolean inOrder(TreeNode node){
        if (node == null) return true;
        // 左
        if (!inOrder(node.left)) return false;

        // 中
        if (prev != null && node.val <= prev.val) return false;
        prev = node;
        
        // 右
        if (!inOrder(node.right)) return false;

        return true;
    }
}
//faster than 100.00% of Java




//解法三： 中序遍历, using BFS stack (用queue模仿stack)
class Solution {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double preVal = - Double.MAX_VALUE;
        TreeNode node = root;
        
        while (!stack.isEmpty() || node != null){
            // 左
            while (node != null){
                stack.offerFirst(node);
                node = node.left;
            }

            node = stack.pollFirst();
            // 如果中序遍历得到的节点的值小于等于前一个 preVal，说明不是二叉搜索树
            if (node.val <= preVal) return false;
            preVal = node.val;

            // 处理右子树
            node = node.right;
        }
        return true;
    }
}
//  faster than 46.18% of Java 



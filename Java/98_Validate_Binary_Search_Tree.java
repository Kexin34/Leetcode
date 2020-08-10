// Solution#1: using min/max range to check the node value
// Recursion
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

//Interation mathod, using BFS stack  
class Solution {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>();
    LinkedList<Integer> lowers = new LinkedList<>();
    
    public boolean isValidBST(TreeNode root) {
        Integer lower = null, upper = null;
        update(root, lower, upper);
        
        while (!stack.isEmpty()){
            TreeNode node = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();
            if (node == null) continue;
            
            if ((lower != null && node.val <= lower) || 
                (upper != null && node.val >= upper))
                return false;
            update(node.right, node.val, upper);
            update(node.left, lower, node.val);
        }
        return true;
    }
    
    public void update(TreeNode node, Integer lower, Integer upper){
        stack.add(node);
        uppers.add(upper);
        lowers.add(lower);
    }
}
//faster than 7.64% of Java 

// solution #2 : In-order traversal, check if the current node > previous node (if sorted)
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



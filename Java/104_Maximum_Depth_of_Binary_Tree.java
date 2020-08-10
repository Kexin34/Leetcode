// DFS + Recursion
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
// faster than 100.00% of Java



// 官方Interation解法（很垃圾）

/* Start from a stack which contains the root node and the corresponding depth which is 1. 
 * Then we proceed to the iterations: 
 * 		Pop the current node out of the stack and push the child nodes. 
 * 		The depth is updated at each step.
*/
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> depths = new LinkedList<>();
        queue.offer(root);
        depths.offer(1);
        
        int depth = 1;
        int current_depth = 0;
        
        while (!queue.isEmpty()){
            root = queue.poll();
            current_depth = depths.poll();
            if(root != null){
                depth = Math.max(depth, current_depth);
                //push the child nodes.
                queue.offer(root.left);
                queue.offer(root.right);
                depths.offer(current_depth + 1);
                depths.offer(current_depth + 1);
            }
        }
        return depth;
    }
}
// faster than 6.47% of Java
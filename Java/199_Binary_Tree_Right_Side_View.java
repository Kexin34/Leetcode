// BFS: One Queue + Sentinel

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);// Add null sentinel to mark the end of the first level.
        TreeNode prev, curr = root;
        List<Integer> rightside = new ArrayList<>();
        
        while (!queue.isEmpty()){
            prev = curr;
            curr = queue.poll();
            
            // 处理本层
            while (curr != null){
                /* Add first left and then right child node into the queue. */
                if (curr.left != null)
                    queue.offer(curr.left);
                if (curr.right != null)
                    queue.offer(curr.right);
                /* Update both previous and current nodes */
                prev = curr;
                curr = queue.poll();
            }
            //we reached the end of the current level.此时prev指向最右node
            rightside.add(prev.val);
            // add a sentinel to mark the end of the next level
            if (!queue.isEmpty())//如果下层还有东西，queue不为空
                queue.offer(null);
        }
        return rightside;
    }
}
// faster than 78.72% of Java 

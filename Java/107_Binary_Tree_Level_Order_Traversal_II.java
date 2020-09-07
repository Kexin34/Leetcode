// Approach 1: Recursion: DFS Preorder Traversal

class Solution {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        Collections.reverse(levels);
        return levels;
    }
    
    private void helper(TreeNode node, int level){
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());
        
        // append the current node value
        levels.get(level).add(node.val);
        
        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }
}
//faster than 100.00% of Java


//Approach 2: Iteration: BFS Traversal
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // 本层list建立好了，需要添加到ans的头部（倒叙）
            res.add(0, list);
        }
        return res;
    }
}
// faster than 88.63% of Java




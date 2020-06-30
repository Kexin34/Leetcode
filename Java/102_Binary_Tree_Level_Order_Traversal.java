// 递归解法（重要）

class Solution {
    List<List<Integer>> levels = new ArrayList<List<Integer>>();
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }
    
    private void helper(TreeNode node, int level){
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());
        
        // fulfil the current level
        levels.get(level).add(node.val);
        
        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if(node.right != null)
            helper(node.right, level + 1);
    }
}
// faster than 100.00% of Java
//Time complexity : O(N) since each node is processed exactly once.
//Space complexity : O(N) to keep the output structure which contains N node values.


// 遍历解法 (BFS using queue) （和递归里面同样处理思路）
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if(root == null) return levels;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        
        // start BFS
        while(!queue.isEmpty()){
            // start the current level
            levels.add(new ArrayList<Integer>());
            
            // number of elements in the current level
            int level_length = queue.size();//这层有多少个node
            for(int i = 0; i < level_length; i++){
                TreeNode node = queue.remove();
                
                // fill the current level
                levels.get(level).add(node.val);
                
                // push child nodes of the current level to queue for next level
                if(node.left != null) 
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            level++;    // go to next level
        }
        return levels;
    }
}
// faster than 60.04% of Java
//Time complexity : O(N) since each node is processed exactly once.
//Space complexity : O(N) to keep the output structure which contains N node values.

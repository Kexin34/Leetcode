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
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
            int levelNums = queue.size(); // 当前层元素的个数
            List<Integer> subList = new LinkedList<Integer>();
            for (int i = 0; i < levelNums; i++){
                TreeNode curNode = queue.poll();
                if (curNode != null){
                    subList.add(curNode.val);
                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                }
            }
            // 本层list建立好了，需要添加到ans的头部（倒叙）
            if (subList.size() > 0)
                ans.add(0, subList);
        }
        return ans;
    }
}
//faster than 79.89% of Java




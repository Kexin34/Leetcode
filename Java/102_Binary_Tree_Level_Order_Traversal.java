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
// 思路：用一个队列记录一层的元素，然后扫描这一层元素添加下一层元素到队列（一个数进去出来一次，所以复杂度 O(logN)）
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
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
            res.add(list);
        }
        return res;
    }
}
//faster than 100.00% of Java

//Time complexity : O(N) since each node is processed exactly once.
//Space complexity : O(N) to keep the output structure which contains N node values.

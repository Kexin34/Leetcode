// 每层各自分别从左到右、从右到左顺序把node value放入当层list中，再add到答案里

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (root == null) return results;
        
        // add the root element with a delimiter to kick off the BFS loop
        LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
        node_queue.addLast(root);
        node_queue.addLast(null);
        
        // 用来存放当层元素，每一层结束时把它加到result中
        LinkedList<Integer> level_list = new LinkedList<Integer>();
        boolean is_order_left = true;
        
        while(!node_queue.isEmpty()){
            TreeNode curr_node = node_queue.pollFirst();
            if (curr_node != null){
                if(is_order_left)// 从左到右添加
                    level_list.addLast(curr_node.val);
                else            //从右到左，添加到头部
                    level_list.addFirst(curr_node.val);
                
                // 把下层子node压到queue中
                if (curr_node.left != null)
                    node_queue.addLast(curr_node.left);
                if (curr_node.right != null)
                    node_queue.addLast(curr_node.right);
            }else{
                // we finish the scan of one level
                results.add(level_list);
                level_list = new LinkedList<Integer>();
                // prepare for the next level
                if (!node_queue.isEmpty())
                    node_queue.addLast(null);
                is_order_left = !is_order_left;
            }
        }
        return results;
    }
}
// faster than 100.00% of Java 


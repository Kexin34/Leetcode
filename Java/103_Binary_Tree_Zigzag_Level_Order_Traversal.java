// 解法一：（最基础BFS）奇数层翻转放入result
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int cnt = 0; // 层数
        
        while (!queue.isEmpty()){
            List<Integer> oneLevel = new ArrayList<>();
            
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                oneLevel.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (cnt % 2 == 1) // 如果是奇数层，翻转再放入result
                Collections.reverse(oneLevel);
            res.add(oneLevel);
            cnt++;
        }
        return res;
    }
}
// faster than 85.78% of Java 



// 解法二：BFS（不翻转数组，直接计算index插入
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        boolean leftToRight = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                
                // find position to fill node's value
                if (leftToRight)
                    row.add(node.val);
                else
                    row.add(0, node.val);
                //int index = (leftToRight) ? i : (size - 1 - i);
                
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // after this level
            leftToRight = !leftToRight;
            res.add(row);
        }
        return res;
    }
}
// faster than 85.78% of Java 




//解法三： DFS（重要！）
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }
    
    public void helper(TreeNode node, List<List<Integer>> res, int level){
        if (node == null) return;
        // 结果 res 的大小等于 level，就需要在结果 res 中新加一个空集
        if (res.size() == level){
            List<Integer> newLevel = new LinkedList<>();
            res.add(newLevel);
        }
        // 取出 res[level] 之后，判断 levle 的奇偶, 若其为偶数，
        // 则将 node->val 加入 oneLevel 的末尾; 若为奇数，则加在 oneLevel 的开头
        List<Integer> currList = res.get(level);
        if (level % 2 == 0) 
            currList.add(node.val);
        else 
            currList.add(0, node.val);
        
        //分别对 node 的左右子结点调用递归函数，此时要传入 level+1 即可
        helper(node.left, res, level + 1);
        helper(node.right, res, level + 1);
    }
}
// faster than 100.00% of Java 
// O(n) solution 






// 官方BFS答案（不想看，好啰嗦）
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


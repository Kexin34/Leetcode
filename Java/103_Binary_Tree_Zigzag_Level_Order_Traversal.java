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
            // 本层已经遍历完，考虑添加到res的order
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
        if (root == null) return res;
        helper(root, res, 0);
        return res;
    }
    
    public void helper(TreeNode node, List<List<Integer>> res, int level){
        if (node == null) return;
        
        // 结果 res 的大小等于 level，就需要在结果 res 中新加一个空集
        if (res.size() == level)
            res.add(new LinkedList<Integer>());
        
        // 取出 res[level] 之后，判断 level 的奇偶, 若其为偶数，
        // 则将 node->val 加入本Level list的末尾; 若为奇数，则加在开头
        if (level % 2 == 0) 
            res.get(level).add(node.val);
        else 
            res.get(level).add(0, node.val);
        
        //分别对 node 的左右子结点调用递归函数，此时要传入 level+1 即可
        helper(node.left, res, level + 1);
        helper(node.right, res, level + 1);
    }
}
// faster than 100.00% of Java 
// O(n) solution 





// DFS遍历 
// (自己写的，非最优)
// 时间O(N) 空间O(N)
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        int res = 0;
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0){
                size--;
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ans.add(list);
        }
        // 找出最底层的元素，全部向相加
        List<Integer> deepest = ans.get(ans.size() - 1);
        for (int num: deepest)
            res += num;
        return res;
    }
}
//5 ms, faster than 17.91% of Java 


// 解法二：（最优）preOrder 递归
// Time complexity: O(n)
// Space complexity: O(n)
class Solution {
    int sum = 0;
    int max_depth = 0;
    
    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }
    public void dfs(TreeNode root, int depth){
        if (root == null) return;
        // 如果当前比之前深，重新计算sum
        if (depth > max_depth){
            max_depth = depth;
            sum = 0;
        }
        // 如果当前达到之前的最深，添加到sum中
        if (depth == max_depth)
            sum += root.val;
        // preOrder结束root处理，开始处理左右子节点
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}
//  0 ms, faster than 100.00% of Java



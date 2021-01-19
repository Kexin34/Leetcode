// 解法一：层次遍历
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return null;
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()){
            int size = queue.size();
            Double num = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                num += cur.val; 
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            } 
            res.add(num / size);
        }
        return res;
    }
}
// faster than 80.53% of Java, less than 86.30% of Java 
// Time：O(n), 广度优先搜索需要对每个节点访问一次，时间复杂度是 O(n)。
// 需要对二叉树的每一层计算平均值，时间复杂度是 O(h)，其中 h 是二叉树的高度，任何情况下都满足 h≤n。因此总时间复杂度是 O(n)。
// Space: O(n)，空间复杂度取决于队列开销，队列中的节点个数不会超过 n。



// 解法二： DFS
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> counts = new ArrayList<Integer>();
        List<Double> sums = new ArrayList<Double>();
        dfs(root, 0, counts, sums);
        
        List<Double> res = new ArrayList<Double>();
        int size = sums.size();
        for (int i = 0; i < size; i++)
            res.add(sums.get(i) / counts.get(i));
        return res;
    }
    
    public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) return;
        if (level < sums.size()){
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        }else{
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }
}
// faster than 100.00% of Java
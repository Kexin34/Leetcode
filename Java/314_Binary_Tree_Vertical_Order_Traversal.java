// BFS + 哈希表存<col, array of node val>
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<Integer> cols = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 哈希表建立col序号和其对应的所有节点值的映射
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        queue.add(root);
        cols.add(0);
        int min = 0;        //用来维护cols的两个边界
        int max = 0;
        
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            int col = cols.poll();//这个node对应的col
            //看看哈希表有没有这个col的bucket
            if (!map.containsKey(col))
                map.put(col, new ArrayList<Integer>());
            map.get(col).add(node.val);//找到这个bucket往里面加
            
            // 左右子树进queue，对应col是相对于本node的col
            if (node.left != null){
                queue.add(node.left);
                cols.add(col - 1);
                // 更新cols左边界
                min = Math.min(min, col - 1);
            }
            if (node.right != null){
                queue.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        // 把col range里面每个col对应的bucket节点值（array of int）全部添加到res中
        for (int i = min; i <= max; i++)
            res.add(map.get(i));
        return res;
    }
}
// faster than 93.57% of Java


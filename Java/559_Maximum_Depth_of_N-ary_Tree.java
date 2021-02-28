// 递归
class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int max = 0;
        for (Node child : root.children)
            max = Math.max(max, maxDepth(child));
        return max + 1;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java


// 解法：迭代
class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<Node>();
        Queue<Integer> depths = new LinkedList<Integer>();
        queue.offer(root);
        depths.offer(1);
        
        int max_depth = 1;
        int cur_depth = 0;
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            cur_depth = depths.poll();
            if (node != null){
                max_depth = Math.max(max_depth, cur_depth);
                // push the children into queue
                for (Node child: node.children){
                    queue.offer(child);
                    depths.offer(cur_depth + 1);
                }
            }
        }
        return max_depth;
    }
}
// 2 ms, faster than 19.78% of Java
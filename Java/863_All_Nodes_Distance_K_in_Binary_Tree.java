// 解法一：DFS + 哈希表
class Solution {
    // <node, distance from the node in that path to target node>
    Map<TreeNode, Integer> map = new HashMap<>();
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        find(root, target);
        int root_dist = map.get(root);
        dfs(root, target, K, root_dist, res);
        return res;
    }
    
    //先找目标节点，更新哈希表，只存有从root到target这条path每个节点相对距离（用于DFS）
    public int find(TreeNode root, TreeNode target){
        if (root == null) return -1;
        if (root == target) {
            map.put(root, 0); // 本node就是target，距离为0
            return 0;
        }
        // 左右子树递归向下找, 记录path每个node的距离值
        int left = find(root.left, target);
        if (left >= 0){
            map.put(root, left + 1);//本node不是，
            return left + 1;
        }
        int right = find(root.right, target);
        if (right >= 0){
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }
    
    private void dfs(TreeNode root, TreeNode target, int K, int length, List<Integer>res){
        if (root == null) return;
        if (map.containsKey(root)) 
            length = map.get(root);
        if (length == K)         // 找到距离目标为K的节点，存入答案
            res.add(root.val);      
        dfs(root.left, target, K, length + 1, res);
        dfs(root.right, target, K, length + 1, res);
    }
}
//faster than 100.00% of Jav




// 解法二：DFS + 邻接表
//1. build a undirected graph using treenodes as vertices, and the parent-child relation as edges
//2. do BFS with source vertice (target) to find all vertices with distance K to it.
// 建立一个邻接链表：哈希表来建立每个结点和其相邻（俩子一父）的结点数组之间的映射
// BFS 进行层序遍历，就要使用队列 queue，还要一个 HashSet 来记录访问过的结点。
class Solution {
    Map<TreeNode, List<TreeNode>> map = new HashMap();
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<Integer>();
        buildMap(root, null);          // 建好邻接链表
        if (!map.containsKey(target)) return res;// 如果target不在图里
        
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // BFS是从target开始一层层向外搜索
        queue.add(target);
        visited.add(target);
        while(!queue.isEmpty()){
            // 本层都是距离target同样距离的nodes
            int size = queue.size();
            //若K为0，说明当前这层的结点都是符合题意，把当前队列中所有的结点加入结果并返回即可
            if (K == 0){
                for (int i = 0; i < size; i++)
                    res.add(queue.poll().val);
                return res;
            }
            // 否则就进行层序遍历，取出当前层的每个结点，并在邻接链表中找到和其相邻的结点
            // 若没有访问过，就加入 visited 和 queue 中即可。
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                for (TreeNode next : map.get(node)){
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    queue.add(next);
                }
            }
            // 记得每层遍历完成之后，K要自减1
            K--;
        }
        return res;
    }
    
    public void buildMap(TreeNode node, TreeNode parent){
        if (node == null) return;
        if (!map.containsKey(node)){
            map.put(node, new ArrayList<TreeNode>());
            // 重点：把父和子节点相互关联
            if (parent != null){
                map.get(node).add(parent);
                map.get(parent).add(node);
            }
            buildMap(node.left, node);
            buildMap(node.right, node);
        }
    }
}
// faster than 60.82% of Java



// BFS topological sort 剥洋葱
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        if (n == 1) return Collections.singletonList(0);
        List<Integer> leaves = new ArrayList<>();
        List<Set<Integer>> adjList = new ArrayList<>();
        // 建立graph （填充adjList）一共n个hashset，保存了i节点可以到达的所有节点
        for (int i = 0; i < n; i++)
            adjList.add(new HashSet<>());
        for (int[] edge : edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        // 找到外圈（hashset size = 1的）
        for (int i = 0; i < n; i++)
            if (adjList.get(i).size() == 1)
                leaves.add(i);
        while (n > 2){
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            // 删掉所有外圈的叶节点
            for (int i : leaves) {
                int inner = adjList.get(i).iterator().next();
                // 在相连node的hashset里面删除本叶节点
                adjList.get(inner).remove(i);
                if (adjList.get(inner).size() == 1)
                    newLeaves.add(inner);
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}


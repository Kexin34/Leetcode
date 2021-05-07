// 因为是DAG，所以确定不会有环，不需要visited
// 标准的图的遍历

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> path = new ArrayList<>();
        path.add(0);
        traverse(graph, 0, graph.length - 1, path);
        return res;
    }
    
    public void traverse(int[][] graph, int start, int end, List<Integer> path) {
        if (start == end) {
            res.add(new ArrayList(path));
            return;
        }
        
        for (int nei : graph[start]) {
            path.add(nei);
            traverse(graph, nei, end, path);
            path.remove(path.size() - 1);
        }
    }
}
// Runtime: 1 ms, faster than 100.00% of Java
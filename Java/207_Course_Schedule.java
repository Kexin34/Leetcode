// Topological sorting (dfs) find cycle

class Solution {
    // states: 0 = unkonwn, 1 == visiting, 2 = visited
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // HashMap is slower than ArrayList in this problem.
        ArrayList<ArrayList<Integer>> adjacency = new ArrayList<>();
        int[] visited = new int[numCourses];
        
        // 每个node都有一个list of nodes
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<Integer>());

        //把edge pair转换成邻接表
        for (int i = 0; i < prerequisites.length; i++){
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            adjacency.get(pre).add(course); // pre -> course
        }
        // 把邻接表扔到DFS,检查是否是有cycle
        for (int i = 0; i < numCourses; i++)
            if (!dfs(i, adjacency, visited)) return false;
        return true;
    }
    
    public boolean dfs(int cur, ArrayList<ArrayList<Integer>> adjacency, int[] visited){
    	// 终止条件
        if (visited[cur] == 2) return true;// 说明当前访问节点已被其他节点启动的DFS访问，无需重复搜索
        if (visited[cur] == 1) return false; // visiting -> 本轮DFS第二次被访问 -> detect cycle
        
        visited[cur] = 1;
        for (int next : adjacency.get(cur))	// 递归访问当前节点所有邻接点
            if (!dfs(next, adjacency, visited)) return false;
        // 当前节点所有邻接点都已被访问，并未发现cycle，则把当前节点设为已访问
        visited[cur] = 2;
        return true;
    }
}
//faster than 99.66% of Java


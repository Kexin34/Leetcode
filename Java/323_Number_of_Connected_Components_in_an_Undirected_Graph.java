// DFS - 寻找全连接数量

class Solution {
    List<Integer>[] adj;
    boolean[] visited;
    
    public int countComponents(int n, int[][] edges) {
        adj = new List[n];
        visited = new boolean[n];
        int component = 0;
        
        /* 构建邻接链表Adjacency List */
        for(int i = 0; i < n; ++i) 
            adj[i] = new ArrayList();
        
        for(int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        // DFS
        for (int i = 0; i < n; i++){
            if (!visited[i]){       
                dfs(i);
                component++;
            }
        }
        return component;
    }
    
    public void dfs(int i){
        if (visited[i] == true) return;
        visited[i] = true;
        // 遍历这个node的所有连接nodes
        for (int j : adj[i]) {
            dfs(j);
        }
    } 
}
// faster than 72.38% of Java

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



// 简化版Union Find
// 先把count初始化为node数，每次union成功的话减小count
class Solution {
    int[] parent = null;
    int count;
    
    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++)
            parent[i] = i;
        
        for (int[] edge: edges){
            union(edge[0], edge[1]);
        }
        return count;
    }
    
    private int find(int x){
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }
    
    private void union(int a, int b){
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b){
            parent[root_a] = root_b;
            count--;
        }
    }
}
// Runtime: 1 ms, faster than 100.00% of Java
// Time: find是O(1), union在有路径压缩以后也是O(1)

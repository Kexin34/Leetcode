// DFS + 邻接表检查graph有无多个连通分量
class Solution {
    public boolean validTree(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);
        
        /* 建立邻接链 */
        // initialize vertices
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<Integer>());
        // add edges    
        for (int[] edge : edges){
            int u = edge[0], v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        // Dfs, make sure there's no cycle
        if (hasCycle(adjList, visited, 0, -1))
            return false;
        
        // make sure all vertices are connected，若有其余连通分量说明错
        for (int i = 0; i < n; i++)
            if (!visited[i])
                return false;
        
        return true;
    }
    
    // check if an undirected graph has cycle started from vertex u
    public boolean hasCycle(List<List<Integer>> adjList, 
                            boolean[] visited, int node, int parent){
        visited[node] = true;
        for(int i = 0; i < adjList.get(node).size(); i++){
            int v = adjList.get(node).get(i);
            if ((v != parent && visited[v]) || (!visited[v] 
                                                && hasCycle(adjList, visited, v, node)))
                return true;
        }
        return false;
    }
}
// faster than 54.41% of Java




// 解法二；Union Find
class Solution {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int m = edges.length;
        for(int i = 0; i < m; i++){
            int x = edges[i][0];
            int y = edges[i][1];
            // 对于一个 pair 的两个节点分别调用 find 函数，得到的值如果相同的话，则说明环存在
            if(uf.find(x, y)) return false; 
            uf.union(x, y);
        }
        return uf.size() == 1;           
    }
    
    class UnionFind {
        private int[] id, size;
        private int count;
        
        public UnionFind(int len) {
            id = new int[len];
            size = new int[len];
            for(int i = 0; i < len; i++) {
                id[i] = i;
                size[i] = 1;
            }
            count = len;
        }
        
        public int size() {return count;}
        
        private int root(int i) {
            while(i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }
        
        public boolean find(int p, int q) {
            return root(p) == root(q);
        }
        
        public void union(int p, int q) {
            int pi = root(p), qi = root(q);
            if(size[pi] < size[qi]) {
                id[pi] = qi;
                size[qi] += size[pi];
            } else {
                id[qi] = pi;
                size[pi] += size[qi];
            }
            count--;
        }
    }
}
// faster than 84.22% of Java


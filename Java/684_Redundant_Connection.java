// 解法一：DFS (非最优)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int MAX_EDGE_VAL = 1000;
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        Set<Integer> visited = new HashSet<>();
        
        for (int i = 0; i <= MAX_EDGE_VAL; i++) 
            graph[i] = new ArrayList();
        
        for (int[] edge : edges){
            visited.clear();
            int u = edge[0];
            int v = edge[1];
            if (!graph[u].isEmpty() && !graph[v].isEmpty() 
                && dfs(u, v, graph, visited))
                return edge;
            
            graph[u].add(v);
            graph[v].add(u);
        }
        throw new AssertionError();
    }
    
    public boolean dfs(int cur, int target, ArrayList<Integer>[] graph, 
        Set<Integer> visited){
        if (!visited.contains(cur)){
            visited.add(cur);
            if (cur == target) return true;
            for (int next : graph[cur])
                if (dfs(next, target, graph, visited))
                    return true;
        }
        return false;
    }
}


// 解法二：Union Find (有用路径压缩的，相对最优)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length; // #of edges
        UnionFindSet uf = new UnionFindSet(edges.length);
        for (int[] edge : edges){
            int u = edge[0], v = edge[1];
            if (!uf.Union(u, v))
                return edge;
        }
        return null;
    }
    
    private class UnionFindSet{
        private int[] parents;
        private int[] ranks;
        
        UnionFindSet(int n){
            parents = new int[n + 1];
            ranks = new int[n + 1];
            for (int i = 0; i < parents.length; i++){
                parents[i] = i;
                ranks[i] = 1;
            }
        }
        
        private boolean Union(int u, int v){
            int pu = Find(u);
            int pv = Find(v);
            if (pu == pv) return false;
            if (ranks[pv] > ranks[pu])
                parents[pu] = pv; // 当v的根深度比u根深度大，把u根接到v根上（路径压缩）
            else if (ranks[pu] > ranks[pv])
                parents[pv] = pu;
            else{               // uv的根一样深
                parents[pv] = pu;   //把v根接到u根上
                ranks[pu]++;         //u根深度加一
            }
            return true;
        }
        
        private int Find(int u){
            while (parents[u] != u){     //while每次把u接到祖母上去（find顺带也压缩）
                parents[u] = parents[parents[u]];
                u = parents[u];
            }
            return u;
        }
    }
}
//faster than 73.44% of Java 



// 同样是Union Find (无路径压缩的，作为上面改进之前的)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length; // # of edges
        UnionFind uf = new UnionFind(N);
        for(int[] e: edges){
            int u = e[0], v = e[1];
            if(uf.connect(u, v)){
                return e;
            }
            uf.union(u,v);
        }
        return new int[]{-1, -1};
    }
    
    private class UnionFind{
        private int[] id;
        // constructor, 并查集由一个整数型的数组和两个函数构成
        UnionFind(int N){
            id = new int[N + 1];
            for(int i = 0; i < id.length; i++){
                id[i] = i;
            }
        }
        
        void union(int u, int v){
            int uID = find(u);
            int vID = find(v);
            if(uID == vID)
                return;
            for(int i = 0; i < id.length; i++){
                if(id[i] == uID)
                    id[i] = vID;
            }
        }
        
        int find(int p) {return id[p];}
        
        boolean connect(int u, int v){
            return find(u) == find(v);
        }
    }
}
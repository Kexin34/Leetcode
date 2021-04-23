// Union Find找每次connected conponent的个数，每次要在新增点四周union
// Union Find套用九章强化笔记模板

class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null) return res;
        
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[m][n];
        
        UnionFind uf = new UnionFind(m, n);
        int count = 0;
        
        for (int i = 0; i < positions.length; i++){
            int x = positions[i][0];
            int y = positions[i][1];
            if (!visited[x][y]){  // 如果不是陆地，说明没访问过
                count++;
                visited[x][y] = true;
                int id = convertToId(x, y, n);
                // 遍历四周, 如果是同一个root，连起来并且减小count个数
                for (int[]dir: dirs){
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n && visited[nx][ny]){
                        int nid = convertToId(nx, ny, n);
                        int current_root = uf.compressed_find(id);
                        int neibour_root = uf.compressed_find(nid);
                        if (current_root != neibour_root){
                            count--;
                            uf.union(id, nid);
                        }
                    }
                }
            }
            res.add(count);
        }
        return res;
    }
    
    int convertToId(int x, int y, int n) {
        return x * n + y;
    }
    
    class UnionFind{
        int[] parent = null;
        // UnionFind初始化parent(把二维转成一维再处理)
        UnionFind(int m, int n){
            parent = new int[m * n];
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    int id = convertToId(i, j, n);
                    parent[id] = id;
                }
            }
        }
        
        int compressed_find(int x){
            if (parent[x] == x)
                return x;
            return parent[x] = compressed_find(parent[x]);
        }
        
        void union(int x, int y){
            int root_x = compressed_find(x);
            int root_y = compressed_find(y);
            if (root_x != root_y)
                parent[root_x] = root_y;
        }
    }
}
// Runtime: 9 ms, faster than 72.91% of Java 
// TIME：O(nm + k),初始查并集n*m，再加上k次（每次O(1))operation操作
// 如果没有路径压缩，每次find都是O(n * m)
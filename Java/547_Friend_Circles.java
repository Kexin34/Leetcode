//题目描述：好友关系可以看成是一个无向图，例如第 0 个人与第 1 个人是好友
// 那么 M[0][1] 和 M[1][0] 的值都为 1。
//DFS
class Solution {
    int n;
    public int findCircleNum(int[][] M) {
        n = M.length;
        int circleNum = 0;
        boolean[] visited = new boolean[n];
        

        for (int i = 0; i < n; i++){
            if (!visited[i]){       // 把有1的area都找出来, 再去对于没有遍历到的人在找其朋友圈的人
                dfs(M, i, visited);
                circleNum++;
            }
        }
        return circleNum;
    }
    
    public void dfs(int[][] M, int i, boolean[] visited){
        visited[i] = true;
        // 遍历这个人的好友
        for (int k = 0; k < n; k++){
            if (M[i][k] == 1 && !visited[k])
                dfs(M, k, visited);
        }
    }
}
// faster than 100.00% of Java
// Time complexity : O(n^2) The complete matrix of size n^2 is traversed.
// Space complexity : O(n). visited array of size n is used.



// Union find解法，
// union find是用了模板（用了路径压缩）
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j < n; j++){
                if (isConnected[i][j] == 1)
                    uf.union(i, j);
            }
        }
        return uf.count();
    }
    
    
    class UnionFind {
        private int count;             // 记录连通分量
        private int[] parent;          // 节点 x 的根节点是 parent[x]
        private int[] size;            // 新增一个数组记录树的“重量”, 小一些的树接到大一些的树下面

        /* 构造函数，n 为图的节点总数 */
        public UnionFind(int n){
            count = n;                // 一开始互不连通
            parent = new int[n];      // 父节点指针初始指向自己         
            size = new int[n];        // 最初每棵树只有一个节点, size重量应该初始化 1

            for(int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        /* union函数: 将 p 和 q 连接 */
        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ) 
                return;

            // 将两棵树合并为一棵, 小树接到大树下面，较平衡
            if(size[rootP] > size[rootQ]){//Q的parent变成P
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }else{
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--; // 两个分量合二为一
        }

        /* find函数: 返回某个节点 x 的根节点, 路径压缩过的写法 */
        private int find(int x){
            // 根节点的 parent[x] == x
            while(parent[x] != x){
                // 进行路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        /* 返回当前的连通分量个数 */
        public int count(){
            return count;
        }
    }
}
// Runtime: 2 ms, faster than 43.92% of Java
// Union-Find approach, the complexity could be O(n^2) if path compression is used. 
// Union-Find complexity with path compression is O(m), which m is operations (either union/find); 
// in this case, since m[i][j] = m[j][i], the union operation is at most n*(n-1)/2,
//  therefore it is O(n^2)




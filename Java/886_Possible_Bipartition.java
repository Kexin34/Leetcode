// DFS 递归 ：邻接表 + 染色法(时间非最快)
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] colors = new int[N];// 数组记录每个node染色情况，0未被染色，1黑 2白
        int[][] adj = new int[N][N];
        
        // 先建立邻接表
        for (int[] edge : dislikes){
            adj[edge[0] - 1][edge[1] - 1] = 1;//-1因为原本是从1开始
            adj[edge[1] - 1][edge[0] - 1] = 1;
        }
        
        // 遍历，使用染色法
        for (int i = 0; i < N; i++){
            //每次开始时要设lastColor为0（未染色）
            if (colors[i] == 0 && !dfs(adj, i, colors, 0))
                return false;
        }
        return true;
    }
    
    public boolean dfs(int[][] adj, int cur, int[] colors, int lastColor){
        // 首先看有无被染色，如果已被染色，检查和lastColor是否同色（同色说明错误）
        // 如果已被染色的就不要继续染色了（因为这是自底向上的，被染色的点，其相连的节点肯定被染色了）
        // 如果继续对被染色的节点染色，就会导致死循环
        if (colors[cur] != 0){
            return colors[cur] != lastColor;
        }
        // 未被染色，染成与相邻结点不同的颜色（lastColor为1时，就染成2）
        colors[cur] = lastColor == 1 ? 2 : 1;
        
        //对这个node的相邻的nodes进行DFS(遍历所有跟其合不来的人)
        for (int i = 0; i < adj.length; i++){
            if (adj[cur][i] == 1){
                if (colors[cur] == colors[i]) return false;
                if (colors[i] == 0 &&!dfs(adj, i, colors, colors[cur])) return false;
            }     
        }
        return true;
    }
}
// faster than 24.49% of Java 



// BFS 迭代， 染色法（时间最快）
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] colors = new int[N + 1];
        // 建立邻接链表
        List<List<Integer>> adj = new ArrayList<>(N + 1);
        for(int i = 0; i <= N; i++) 
            adj.add(new ArrayList<Integer>());
        
        for (int[] edge : dislikes){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        //开始遍历结点
        for (int i = 1; i <= N; i++){
            //若某个结点已经染过色了，跳过，否则就先给其染为1。
            if (colors[i] != 0) continue;
            colors[i] = 1;
            // 借助 queue 来进行 BFS 遍历，现将当前结点排入队列
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            // 然后开始循环队列，取出队首结点，然后遍历其所有相邻结点
            while (!queue.isEmpty()){
                int cur = queue.poll();
                for (int next : adj.get(cur)){
                    //如果两个颜色相同，直接返回 false，否则若其为白纸，则赋相反颜色，并且排入队列。
                    if (colors[next] == colors[cur])
                        return false;
                    if(colors[next] == 0) {
                        colors[next] = colors[cur] == 1 ? 2 : 1;
                        queue.offer(next);
                    }
                }
            }
        }
        return true;
    }
}
//faster than 94.93% of Java


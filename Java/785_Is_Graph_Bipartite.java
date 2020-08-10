// DFS（染色法判读二分图）
class Solution {
    public boolean isBipartite(int[][] graph) {
        int v = graph.length;   // 节点node数量
        int[] colors = new int[v];  // 数组记录每个node染色情况，0未被染色，1黑 2白
        
        // 要考虑非连通图, 所以要遍历每一个结点
        for (int i = 0; i < v; i++){
            //每次开始时要设lastColor为0（未染色）
            if(!dfs(graph, i, colors, 0))
                return false;
        }
        return true;
    }
    
    private boolean dfs(int[][] graph, int i, int[] colors, int lastColor){
        // 如果已被染色，检查和lastColor是否同色（同色说明错误）
        // 如果已被染色的就不要继续染色了（因为这是自底向上的，被染色的点，其相连的节点肯定被染色了）
        // 如果继续对被染色的节点染色，就会导致死循环
        if (colors[i] != 0)
            return colors[i] != lastColor;
            
        // 未被染色，染成与相邻结点不同的颜色（lastColor为1时，就染成2）
        colors[i] = lastColor == 1 ? 2 : 1;
        
        //对这个node的相邻的nodes进行DFS
        for (int j = 0; j < graph[i].length; j++){
            if (!dfs(graph, graph[i][j], colors, colors[i]))
                return false;
        }
        return true;
    }
}

//faster than 100.00% of Java
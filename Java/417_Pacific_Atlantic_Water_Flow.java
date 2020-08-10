//DFS
class Solution {
    int n, m;
    private int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
    private int [][] matrix;
        
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return ans;
        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;

        //分开尝试两条联通线路(visited map for each ocean)
        boolean[][] canReachP = new boolean [m][n];
        boolean[][] canReachA = new boolean [m][n];
        
        // step 1: 从边界开始DFS
        for (int i = 0; i < m; i++){ 
            dfs(i, 0, canReachP);           // 左边界，连接P
            dfs(i, n - 1, canReachA);        // 右边界，连接A
        }
        for (int j = 0; j < n; j++){
            dfs(0, j, canReachP);           //上边界，连接P
            dfs(m-1, j, canReachA);         //下边界，连接A
        }
        
        //step 3: 找到二者均为 true 的点
        for (int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(canReachP[i][j] && canReachA[i][j])
                    ans.add(Arrays.asList(i, j));
            }
        }
        return ans;
    }
    
    // step2：从边界开始DFS，找到连接路线
    private void dfs(int r, int c, boolean[][] canReach){
        if (canReach[r][c])
            return;
        canReach[r][c] = true;
        for (int[] d: direction){
            int nextR = r + d[0];
            int nextC = c + d[1];
            if(nextR < 0 || nextR >= m || nextC < 0 || nextC >= n 
                || matrix[r][c] > matrix[nextR][nextC])
                continue;
            dfs(nextR, nextC, canReach);
        }
    }
    // faster than 89.36% of Java
}


// 解法二：BFS
// 所有边缘点当作起点开始遍历搜索，然后标记能到达的点为 true，分别标记出 pacific 
// 和 atlantic 能到达的点，那么最终能返回的点就是二者均为 true 的点。
class Solution {
    int m, n;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
            return res;
        m = matrix.length;
        n = matrix[0].length;
        
        //分开尝试两条联通线路 (visited map for each ocean)
        boolean[][] canReachP = new boolean[m][n]; // 类似visited[][]
        boolean[][] canReachA = new boolean[m][n];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        // Step 1:把边上的点分别存入 queue 中，然后对应的 map 标记 true，
        for (int i = 0; i < m; i++){        //Vertical border
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, n - 1});
            canReachP[i][0] = true;
            canReachA[i][n - 1] = true;
        }
            for (int j = 0; j < n; j++){    //Horizontal border
            pQueue.offer(new int[]{0, j});
            aQueue.offer(new int[]{m - 1, j});
            canReachP[0][j] = true;
            canReachA[m - 1][j] = true;
        }
        // Step: 2 BFS 遍历
        bfs(matrix, pQueue, canReachP);
        bfs(matrix, aQueue, canReachA);
        //step 3: 找到二者均为 true 的点
        for (int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(canReachP[i][j] && canReachA[i][j])
                    res.add(Arrays.asList(i, j));
            }
        }
        return res;
    }
    
    public void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited){
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int[] dir : dirs){
                int nextR = cur[0] + dir[0];
                int nextC = cur[1] + dir[1];
                if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n
                   || visited[nextR][nextC] || matrix[nextR][nextC] < matrix[cur[0]][cur[1]])
                    continue;
                visited[nextR][nextC] = true;
                queue.offer(new int[]{nextR, nextC});
            }
        }
    }
}
// faster than 52.65% of Java

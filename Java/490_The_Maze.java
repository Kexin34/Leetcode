// DFS
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m, n;
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        return dfs(maze, start[0], start[1], destination[0], destination[1]);
    }
        
    public boolean dfs(int[][] maze, int si, int sj, int di, int dj){
        if (si == di && sj == dj) return true;
        
        boolean res = false;
        maze[si][sj] = -1;      // 将0改为-1来记录某个点是否被访问过
        for (int[]dir : dirs){
            int r = si;
            int c = sj;
            // 处理一直滚的情况:有了方向，只要一直在那个方向上往前走，
            // 每次判读是否越界了或者是否遇到墙了即可，然后对于新位置继续调用递归函数
            while (r >= 0 && r < m && c >= 0 && c < n && maze[r][c] != 1){
                r += dir[0];
                c += dir[1];
            }
            r -= dir[0]; // 因为上面while是到达了越界/碰墙的才break，要回退一格
            c -= dir[1]; 
            if (maze[r][c] != -1)      //若未访问过，对于新位置调用递归函数
                res = res | dfs(maze, r, c, di, dj);
        }
        return res;
    }
}
// faster than 89.57% of Java



// 同样思路的BFS解法
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1]});
        
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            visited[cur[0]][cur[1]] = true;
            
            if (cur[0] == destination[0] && cur[1] == destination[1])
                return true;        // 到达终点！
            
            for (int[] dir : dirs){
                int newR = cur[0];
                int newC = cur[1];
                
                //利用辅助函数isValid()，不需要rollback，提高可读性
                while (isValid(maze, newR + dir[0], newC + dir[1])){
                    newR += dir[0];
                    newC += dir[1];
                }
                if (!visited[newR][newC])
                    queue.offer(new int[]{newR, newC});
            }
        }
        return false;
    }
    
    public boolean isValid(int[][] maze, int x, int y){
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length 
            && maze[x][y] == 0;
    }
}
// faster than 14.09% of Java


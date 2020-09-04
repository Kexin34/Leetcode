// 下面都是同一种DFS解法，取决于是否把m,n,direction放在外面
// Time complexity: O(mn)
// Space complexity: O(mn)


class Solution {
    int m, n;
    int direction[][] = {
        {-1, 0}, // down
        {0, -1}, // left
        {0, 1}, // right
        {1, 0}  // up
    };
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        int numIslands = 0;
        
        for (int r = 0; r < m; r++){
            for (int c = 0; c < n; c++){
                if (grid[r][c] == '1'){    //用DFS把所有连接的1全部变成0
                    // Trigger DFS
                    numIslands++;
                    dfs(grid, r, c);
                }
            }
        }
        return numIslands;
    }
    
    public void dfs(char[][] grid, int r, int c){
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == '0') return;
        grid[r][c] = '0';
        for (int[] dir : direction)
            dfs(grid, r + dir[0], c + dir[1]);
    }
}
// faster than 99.95% of Java

//如果面试官不同意把m,n,direction放在外面的话
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int numIslands = 0;
        
        for (int r = 0; r < m; r++){
            for (int c = 0; c < n; c++){
                if (grid[r][c] == '1'){
                    // Trigger DFS
                    numIslands++;
                    dfs(grid, r, c);
                }
            }
        }
        return numIslands;
    }
    
    public void dfs(char[][] grid, int r, int c){
        int m = grid.length;
        int n = grid[0].length;
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == '0') return;
        
        grid[r][c] = '0';
        
        dfs(grid, r - 1, c); // up
        dfs(grid, r + 1, c); // down
        dfs(grid, r, c - 1); // left
        dfs(grid, r, c + 1); // right
    }
}
//faster than 99.95% of Java




// 解法： BFS
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int m, n;
    
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1' && !visited[i][j]){
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    bfs(grid, queue, visited);
                    count++;
                }
            }
        }
        
        return count; 
    }
    
    public void bfs(char[][] grid, Queue<int[]> queue, boolean[][] visited){
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            
            for (int[] dir : dirs){
                int newR = cur[0] + dir[0];
                int newC = cur[1] + dir[1];
                
                if (newR < 0 || newR >= m || newC < 0 || newC >= n 
                    || grid[newR][newC] == '0' || visited[newR][newC])
                    continue;
                visited[newR][newC] = true;;
                queue.offer(new int[] {newR, newC});
            }
        }
    }
}
// faster than 14.91% of Java 
// iterate the grid, cell by cell, one each cell, DFS or BFS at most will visit m*n cells. 
// but for the cells it visited, we marks it so the next visit in the for loop 
// in main function will be terminated immediately.

// both visit the whole grid twice. So it is O(m*n)

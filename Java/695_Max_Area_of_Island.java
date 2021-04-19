// DFS
class Solution {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        int maxArea = 0;
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1)
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
        }
        return maxArea;
    }
    
    public int dfs(int[][] grid, int r, int c){
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) return 0;
        grid[r][c] = 0;
        int area = 0; 
        
        for (int[] dir : dirs)
            area += dfs(grid, r + dir[0], c + dir[1]);
        return area + 1;// 把本cell算进去
    }
}
// faster than 99.73% of Java




// 自己重写DFS，更好理解
class Solution {
    int m, n;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int curArea;
    
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        int maxArea = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    // 重新开始计算新区域的area
                    curArea = 0;
                    dfs(grid, i , j);
                    // 本新区域area计算结束，和max对比并更新
                    maxArea = Math.max(maxArea, curArea);
                }
            }
        }  
        return maxArea;
    }
    
    private void dfs(int[][] grid, int i, int j){
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return;
        curArea++;
        grid[i][j] = 0;
        for (int[] dir : dirs){
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }
}
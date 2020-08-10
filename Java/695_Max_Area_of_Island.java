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
        int area = 1; // 把本cell算进去
        for (int[] dir : dirs)
            area += dfs(grid, r + dir[0], c + dir[1]);
        return area;
    }
}
// faster than 64.68% of Java

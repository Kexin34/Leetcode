// DFS
class Solution {
    int m, n;
    int dirs[][] = {{-1, 0}, {0, -1},{0, 1}, {1, 0}};
    boolean valid;
    
    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int answer = 0;
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1) continue;
                valid = true;
                dfs(grid, i, j);
                // 若所本次搜索没有碰到边界，那么valid还是true，全连接个数加一
                if (valid) answer++;
            }
        }
        return answer;
    }
    
    public void dfs(int[][] grid, int i, int j){
        // if it can reach the boundary then it’s not a closed island.
        if (i < 0 || j < 0 || i >= m || j >= n){
            valid = false;
            return;
        }
        if (grid[i][j] == 1) return; // 遇到1，停止这个方向搜索
        grid[i][j] = 1;     // 设为已访问过
        
        for (int[] dir : dirs)    
            dfs(grid, i + dir[0], j + dir[1]);
    }
}
// faster than 81.30% of Java
// Time complexity: O(n*m)
// Space complexity: O(n*m)
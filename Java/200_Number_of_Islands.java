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


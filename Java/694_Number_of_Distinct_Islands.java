// DFS
class Solution {
    int m, n;
    public int numDistinctIslands(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 0) continue;
                // 有1说明有岛，开始dfs，建立对应string
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, sb, "o");   // origin

                // 本每次dfs搜索到的全连通分量（岛）加入set
                set.add(sb.toString());
                //System.out.println ( sb.toString());
            }
        }
        return set.size();
    }
    
    public void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir){
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return;

        // 合法cell，属于本岛
        sb.append(dir);
        grid[i][j] = 0; // // flip -> 说明visited
        dfs(grid, i - 1, j, sb, "u");   // up
        dfs(grid, i + 1, j, sb, "d");   // down
        dfs(grid, i, j - 1, sb, "l");   // left
        dfs(grid, i, j + 1, sb, "r");   // right
        sb.append("b");                 // // back
    }
}
// faster than 94.32% of Java
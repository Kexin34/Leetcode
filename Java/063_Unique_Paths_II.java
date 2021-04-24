class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][]dp = new int[m][n];
        
        // 初始化起点
        dp[0][0] = obstacleGrid[0][0]==0 ? 1:0;
        if(obstacleGrid[0][0] == 1) return 0;
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                // 如果当前为障碍物，直接设为0
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                // 第一列和第一行取决于自己前一个是否可达
                else if (i == 0){
                    if (j > 0) 
                        dp[i][j] = dp[i][j - 1];
                } 
                else if (j == 0){
                    if (i > 0) 
                        dp[i][j] = dp[i - 1][j];
                }
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
                
        return dp[m - 1][n - 1];
    }
}
// Runtime: 0 ms, faster than 100.00% of Java 



// DP-二维数组做图的path

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] path = new int[m][n];
        
        // Filling the values for the first column
        // If cell is 1 (obstacle), set the value 
        // of that cell to 0. the rest are blocked. no need to continue.
        for (int i = 0; i < m; i++){
            if(obstacleGrid[i][0] == 1){
                path[i][0] = 0;
                break;
            }else
                path[i][0] = 1;
        }
        // Filling the values for the first row
        for (int j = 0; j < n; j++){
            if (obstacleGrid[0][j] == 1){
                path[0][j] = 0;
                break;  // the rest are blocked.
            }else
                path[0][j] = 1;
        }
        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if (obstacleGrid[i][j] == 1)
                    path[i][j] = 0;
                else 
                    path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
        }
        return path[m - 1][n - 1];
    }
}
// faster than 100.00% of Java o
//Time: O(M×N).


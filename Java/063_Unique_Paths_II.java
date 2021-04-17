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
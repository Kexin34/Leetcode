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


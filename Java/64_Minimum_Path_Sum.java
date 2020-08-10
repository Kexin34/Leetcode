// Recursion with memoization
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] sum = new int[m][n];
        for (int[] arr : sum)
            Arrays.fill(arr, 0);
        return minPathSum(grid, m - 1, n - 1, m, n, sum);
    }
    public int minPathSum(int[][] grid, int r, int c, int m, int n, int[][] sum){
        if (r == 0 && c == 0) return grid[r][c]; //递归到起点
        if (r < 0 || c < 0) return Integer.MAX_VALUE;
        if (sum[r][c] > 0) return sum[r][c];
        
        sum[r][c] = grid[r][c] + Math.min(minPathSum(grid, r - 1, c, n, m,sum),
                                         minPathSum(grid, r, c - 1, n, m,sum));
        return sum[r][c];
    }
}
// faster than 99.35% of Java
// Time complexity: O(mn)
// Space complexity: O(mn)



// 解法二：DP +二维数组
// 注意三个边界cases (起点，第一行，第一列)
class Solution {
    public int minPathSum(int[][] grid) {
     if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        
        for (int r = 0; r < m; r++){
            for (int c = 0; c < n; c++){
                if (r == 0 && c == 0) continue;
                if (r == 0)             //第一行每个只看同行左一个
                    grid[r][c] += grid[r][c - 1];
                else if (c == 0)        //第一列每个只看同列上一个
                    grid[r][c] += grid[r - 1][c];
                else
                    grid[r][c] += Math.min(grid[r - 1][c], grid[r][c - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
// faster than 89.84% of Java
// Time complexity: O(mn)


//【优化】DP：一维数组，滚动计算，减少memory空间消耗
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        
        for (int r = 0; r < m; r++){
            for (int c = 0; c < n; c++){
                if (c == 0)             // 只能从上侧走到该位置，垂直向下/向右走
                    dp[c] = dp[c];
                else if (r == 0)        //第一行每个只看同行左一个
                    dp[c] = dp[c - 1];
                else
                    dp[c] = Math.min(dp[c - 1], dp[c]);
                dp[c] += grid[r][c];
            }
        }
        return dp[n - 1];
    }
}
// faster than 89.84% of Java


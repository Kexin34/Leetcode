// 未优化DP，三维数组
class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        final int kMod = 1_000_000_007; //1e9 + 7;
        int [][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //dirs{-1, 0, 1, 0, -1};
        int[][][] dp = new int[N+1][m][n];
        
        for (int s = 1; s <= N; ++s){   // 初始为1这样N = 0也有答案
            for (int r = 0; r < m; r++)
                for (int c = 0; c < n; c++)
                    for (int[] dir : dirs){
                        int cur_r = r + dir[0];
                        int cur_c = c + dir[1];
                        if (cur_r < 0 || cur_c < 0 || cur_r >= m || cur_c >= n)
                            // 新位置越界，有一条out of boundary到[r][c]的path，所以+1
                            dp[s][r][c] += 1;
                        else
                            dp[s][r][c] = (dp[s][r][c] + dp[s-1][cur_r][cur_c]) % kMod;
                    }
        }
        return dp[N][i][j];
    }
}
// faster than 42.72% of Java
// Time complexity: O(m*n + N * m * n)
// Space complexity: O(N*m*n) 



// DP空间优化后 (滚动数组，2D)
class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        final int kMod = 1_000_000_007; //1e9 + 7;
        int [][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //dirs{-1, 0, 1, 0, -1};
        int[][] dp0 = new int[m][n];
        
        for (int s = 1; s <= N; ++s){   // 初始为1这样N = 0也有答案
            int[][] dp1 = new int[m][n];
            for (int r = 0; r < m; r++)
                for (int c = 0; c < n; c++)
                    for (int[] dir : dirs){
                        int cur_r = r + dir[0];
                        int cur_c = c + dir[1];
                        if (cur_r < 0 || cur_c < 0 || cur_r >= m || cur_c >= n)
                            // 新位置越界，有一条out of boundary到[r][c]的path，所以+1
                            dp1[r][c] += 1;
                        else
                            dp1[r][c] = (dp1[r][c] + dp0[cur_r][cur_c]) % kMod;
                    }
            dp0 = dp1;
        }
        return dp0[i][j];
    }
}
//  faster than 46.84% of Java
// Time complexity: O(m*n + N * m * n)
// Space complexity: O(m*n)



// 记忆化递归做法
// DFS Recursion with Memoization
    // memo[i][j][k]: the number of possible moves leading to a path 
    // out of the boundary if the current position is given by the 
    // indices (i, j) and number of moves left is k.
        
class Solution {
    final int kMod = 1_000_000_007; //1e9 + 7;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    public int findPaths(int m, int n, int N, int i, int j) {
        // use Long[][][] then the initial value will be null.
        Long[][][] memo = new Long[m][n][N + 1]; //N + 1为了让N = 1也有答案
        return (int) (findPaths(m, n, N, i, j, memo) % kMod);
    }
    
    public long findPaths(int m, int n, int N, int i, int j, Long[][][] memo){
        //check if out of boundary, if out could not move back
        if (i < 0 || i >= m || j < 0 || j >= n) return 1;
        if (N == 0) return 0;
        if (memo[i][j][N] != null)
            return memo[i][j][N];
        
        // 计算memo
        memo[i][j][N] = 0l;
        for (int dir[] : dirs){
            int new_r = i + dir[0];
            int new_c = j + dir[1];
            memo[i][j][N] = (memo[i][j][N] + 
                             findPaths(m, n, N - 1, new_r, new_c, memo) % kMod) % kMod;
        }
        return memo[i][j][N];
    }
}
//  faster than 54.43% of Java



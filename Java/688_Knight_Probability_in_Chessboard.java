// DP 2D (优化后，滚动数组， 3D->2D)

class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        double[][] dp0 = new double[N][N];
        int[][] dirs = new int[][] {{1, 2}, {-1, -2}, {1, -2}, {-1, 2},
                                    {2, 1}, {-2, -1}, {2, -1}, {-2, 1}};
        dp0[r][c] = 1.0;
        for (int k = 0; k < K; ++k){
            // 建立新的step dp
            double[][] dp1 = new double[N][N];
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    for (int[] dir : dirs){
                        int cur_r = i + dir[0];
                        int cur_c = j + dir[1];
                        if (cur_r < 0 || cur_c < 0 || cur_r >= N || cur_c >= N)
                            continue;
                        dp1[i][j] += dp0[cur_r][cur_c];
                    }
                }
            }
            dp0 = dp1;
        }
        double total = 0.0;
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                total += dp0[i][j];
        
        return total / Math.pow(8, K);
    }
}
// faster than 74.94% of Java 


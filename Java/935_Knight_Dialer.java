// DP 2D滚动数组, 按照688模板修改的
class Solution {
    public int knightDialer(int N) {
        final int kMod = 1_000_000_007; //1e9 + 7;
        int[][] dirs = new int[][] {{1, 2}, {-1, -2}, {1, -2}, {-1, 2},
                                    {2, 1}, {-2, -1}, {2, -1}, {-2, 1}};
        
        int[][] dp0 = new int[4][3];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++)
                dp0[i][j] = 1;
        dp0[3][0] = dp0[3][2] = 0;
        
        for (int k = 1; k < N; ++k){
            // 建立新的hop dp
            int[][] dp1 = new int[4][3];
            for (int i = 0; i < 4; ++i){
                for (int j = 0; j < 3; ++j){
                    if (i == 3 && j != 1) continue; //两个不可到达的cell
                    for (int[] dir : dirs){
                        int cur_r = i + dir[0];
                        int cur_c = j + dir[1];
                        if (cur_r < 0 || cur_c < 0 || cur_r >= 4 || cur_c >= 3)
                            continue;
                        dp1[i][j] = (dp1[i][j] + dp0[cur_r][cur_c]) % kMod;
                    }
                }
            }
            dp0 = dp1;
        }
        int total = 0;
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 3; ++j)
                total = (total + dp0[i][j]) % kMod;
        
        return total;
    }
}
// faster than 38.57% of Java 




// 牛逼的优化！！！
// 2D棋盘可以变成1d （0-9）每个格子都能知道它下一步可以hop到哪
// 不需要每次检查八个方向，存一个数组记录每个格子它下一步可以hop到哪
class Solution {
    public int knightDialer(int N) {
        final int kMod = 1_000_000_007; //1e9 + 7;
        int[][] moves = new int[][]{ //可以移动到的地方
            {4,6},{6,8},{7,9},{4,8},{3,9,0},
            {},{1,7,0},{2,6},{1,3},{2,4}};
        
        // dp0: #of ways to dial and the last key is i after k steps
        int[]dp0 = new int[10];
        Arrays.fill(dp0, 1);
        
        for (int k = 1; k < N; ++k){
            int[]dp1 = new int[10];
            for (int i = 0; i < 10; ++i){
                for (int next : moves[i])
                    // transition: dp[k][i] = sum(dp[k-1][j]) that j can move to i
                    dp1[next] = (dp1[next] + dp0[i]) % kMod;
            }
            dp0 = dp1;
        }

        int total = 0;
        for (int i = 0; i < 10; ++i)
            total = (total + dp0[i]) % kMod;
        
        return total;
    }
} 
// faster than 97.68% of Java
// Time complexity: O(k * 10) = O(k)
// Space complexity: O(10) = O(1)
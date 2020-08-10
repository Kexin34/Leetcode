/* As similar to the traversal problems in a tree structure, 
  there are generally two approaches in terms of solution: DFS and BFS */

// Method #1 : DFS solution
// 用DFS找出所有连接边界的cell是O的（并连接里面O的cell），所有标记为E
// for 循环遍历棋盘的四边，用 DFS 算法把那些与边界相连的 O 换成一个特殊字符，比如 #；
// 然后再遍历整个棋盘，把剩下的 O 换成 X，把 # 恢复成 O。这样就能完成题目的要求，时间复杂度 O(MN)。
class Solution {
    private int m, n;
    private int [][] direction = {{0, 1}, {-1, 0},{0, -1},{1, 0}};//顺序为右下左上顺时针
    
    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;
        m = board.length;
        n = board[0].length;
        
        // Step 1). construct the list of border cells
        for (int i = 0; i < m; i++){
            dfs(board, i, 0);       //每行[0]，即左边界
            dfs(board, i, n - 1);     //每行[末尾]，即右边界
        }
        for (int j = 0; j < n; j++){
            dfs(board, 0, j);           //每列第一个，即上边界
            dfs(board, m - 1, j);       //每列最后一个，即下边界
        }
        
        // Step 3). flip the cells to their correct final states
        for (int r = 0; r < m; r++){
            for (int c = 0; c < n; c++){
                if (board[r][c] == 'E')
                    board[r][c] = 'O';
                else if (board[r][c] == 'O')
                    board[r][c] = 'X';
            }
        }    
    }
    
    //Step 2). mark the escaped cells，找出所有和外界有链接的O，flip成E
    // 为了让无法变成X 的O 和四面被 X 围住的 O 有所区别
    private void dfs(char[][] board, int r, int c){
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'O')
            return;
        board[r][c] = 'E';
        for (int[] d : direction){
            dfs(board, r + d[0], c + d[1]);
        }
    }
}
// 99% time,最优解


// Method #2 Union-Find 解法
class Solution {
    public void solve(char[][] board) {
        if (board.length == 0) return;

        int m = board.length;
        int n = board[0].length;

        UF uf = new UF(m * n + 1); // 给 dummy 留一个额外位置
        int dummy = m * n;         // index 为最后一个

        // 将首列和末列的 O 与 dummy 连通
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                uf.union(i * n, dummy);    // 二维坐标 (x,y) 可以转换成 x * n + y，UF是一维数组
            if (board[i][n - 1] == 'O')
                uf.union(i * n + n - 1, dummy);
        }
        // 将首行和末行的 O 与 dummy 连通
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                uf.union(j, dummy);
            if (board[m - 1][j] == 'O')
                uf.union(n * (m - 1) + j, dummy);
        }
        // 遍历除最外圈以外所有cell,如果里面有O且连接着外圈O，union她们
        // （方向数组 d 是上下左右搜索的常用手法
        int[][] d = new int[][]{{1,0}, {0,1}, {0,-1}, {-1,0}};
        for (int i = 1; i < m - 1; i++) 
            for (int j = 1; j < n - 1; j++) 
                if (board[i][j] == 'O')
                    // 将此 O 与上下左右的 O 连通,如果跟边界O相连，也会和dummy相连
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if (board[x][y] == 'O')
                            uf.union(x * n + y, i * n + j);
                    }
        // 遍历除最外圈以外所有cell, 所有不和 dummy 连通的 O，都要被替换
        for (int i = 1; i < m - 1; i++) 
            for (int j = 1; j < n - 1; j++) 
                if (!uf.connected(dummy, i * n + j))
                    board[i][j] = 'X';
    }
}
// faster than 14.80% of Java








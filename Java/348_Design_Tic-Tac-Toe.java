// 解法一：暴力解
class TicTacToe {
    int[][] board;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
    }
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        int i = 0, j = 0, n = board.length;
        
        // 检查第row行
        for (j = 1; j < n; ++j) 
            if (board[row][j] != board[row][j - 1]) break;
        if (j == n) return player;
        
        // 检查第col列
        for (i = 1; i < n; i++)
            if (board[i][col] != board[i - 1][col]) break;
        if (i == n) return player;
        
        //检查正对脚线
        if (row == col){ //只有在本次放入位置属于对脚线，才需要检查
            for (i = 1; i < n; i++)
                if (board[i][i] != board[i - 1][i - 1]) break;
            if (i == n) return player;
        }
        
        // 检查逆对脚线
        if (row + col == n - 1){//r+c = n-1是逆对角线位置判定前提
            for (i = 1; i < n; i++)
                if (board[n - 1 - i][i] != board[n - i][i - 1]) break;
            if (i == n) return player;
        }
        return 0;
    }
}
// faster than 100.00% of Java



// 解法二（优化），满足followup
class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;
    
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;//对数组，p1是+1，p2是-1操作
        int size = rows.length;
        
        rows[row] += toAdd;
        cols[col] += toAdd;
        if (row == col) diagonal += toAdd;//正对角线
        if (row + col == size - 1) antiDiagonal += toAdd;//逆对角线
        
        // 若任何一个满足 绝对值等于n，game结束，返回玩家
        if (Math.abs(rows[row]) == size ||
           Math.abs(cols[col]) == size ||
           Math.abs(diagonal) == size || 
           Math.abs(antiDiagonal) == size)
            return player;
        return 0;
    }
}
//  faster than 100.00% of Java
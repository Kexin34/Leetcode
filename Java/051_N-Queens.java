// Search + Backtracking
// O(N^(N+1))

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        char[][] board = new char[n][n];
        
        for (int i = 0; i < n; i++) {  // 初始化棋盘
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        }
        backtrack(board, 0, res);
        return res;
    }
    // 路径：board中小于row的那些行已经成功放置了queen
    // 选择列表：第row行的所有列都是放置queen的选择
    // 结束条件：row超过了board的最后一行
    private void backtrack(char[][] board, int row, List<List<String>> res) {
        int n = board.length;
        if (row == n) {
            // convert the board into result
            res.add(boardToString(board));
            return;
        }
        // Try every valid column
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) continue;
            
            board[row][col]= 'Q';    // 做选择
            backtrack(board, row + 1, res);  // 本行放置了Q，去下一行search
            
            board[row][col]= '.'; // 回溯
        }
    }
    
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // 检查本列有无冲突，上下检查
        for (int i = 0; i < n; i++){
            if (board[i][col] == 'Q') 
                return false;
        }
        // 检查右上对角线有无冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') 
                return false;
        }
        // 检查左上对角线有无冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') 
                return false;
        }
        return true;
    }
    
    // convert the board数组 into result（list of string）
    private List<String> boardToString(char[][] board) {
        List<String> temp = new ArrayList<>();
        int n = board.length;
        for (int i = 0; i < n; i++) {  // 遍历每一行并添加
            String t = new String(board[i]);
            temp.add(t);
        }
        return temp;
    }
}
// Runtime: 2 ms, faster than 93.70% of Java
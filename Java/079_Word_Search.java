// DFS

class Solution {
    int m, n;
    
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (dfs(board, i, j, word, 0))
                    return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, String word, int index){
        // 易错点：先判断是否已经到达递归底部(落到word外面），然后再慢慢检查boundary
        if (index == word.length()) return true;  
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(index)) 
            return false;
        
        // mark the current cell as visited，之后要回溯复原的
        char temp = board[i][j];
        board[i][j] = '#';
        
        // 这里不用dirs反而更快， 更直接一些
        boolean found = dfs(board, i + 1, j, word, index + 1) 
            || dfs(board,i - 1, j, word, index + 1) 
            || dfs(board,i, j + 1, word, index + 1) 
            || dfs(board,i, j - 1, word, index + 1) ;
        
        board[i][j] = temp;
        return found;
    }
}
// Runtime: 56 ms, faster than 72.54% of Java
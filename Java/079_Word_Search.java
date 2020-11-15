//已经改成我自己的代码，不是抄袭的
class Solution {
    private char[][] board;
    private int ROWS;
    private int COLS;
    
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;
        
        for (int row = 0; row < this.ROWS; ++row)
            for (int col = 0; col < this.COLS; ++col)
                if (this.backtrack(row, col, word, 0))
                    return true;
        return false;
    }
    public boolean backtrack(int row, int col, String word, int index){
        // chack if reach bottom case of the recursion
        if (index >= word.length()) return true;
        // check if the current state is invalid
        if (row < 0 || row >= this.ROWS || col <0 || col >= this.COLS 
            || this.board[row][col] != word.charAt(index))
            return false;
        
        // Explore the neighbors in DFS + backtracking
        // 1. mark the current cell as visited
        char cur = board[row][col];
        this.board[row][col] = '#';

        // 2. iterate through the four possible directions
        boolean found = this.backtrack(row + 1, col, word, index + 1) 
            || this.backtrack(row - 1, col, word, index + 1) 
            || this.backtrack(row, col + 1, word, index + 1) 
            || this.backtrack(row, col - 1, word, index + 1) ;

        // 3. end of the exploration, revert the cell back to its original state.
        this.board[row][col] = cur;
        return found;
    }
}
// faster than 55.21% of Java
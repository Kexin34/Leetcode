// DFS + backtracking
class Solution {
    int[][] rows = new int[9][10];
    int[][] cols = new int[9][10];
    int[][] boxes = new int[9][10];
    
    public void solveSudoku(char[][] board) {
        // 初始化，把现有的输入到三个数组中
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.'){
                    int n = c - '0';
                    int bx = j / 3; // 通过ij找出对应的盒子
                    int by = i / 3;
                    
                    rows[i][n] = 1;  // 在i行的数字n现在有元素
                    cols[j][n] = 1;  // 在j列的数字n现在有元素
                    boxes[by * 3 + bx][n] = 1; // 第..个盒子的数字n现在有元素
                }
            }
        }
        fill(board, 0, 0);   // 从左上角开始
    }
    
    
    private boolean fill(char[][]board, int x, int y) {//x这里是列位置，y是行位置
        if (y == 9) return true; // 过了边界，返回
        // 记录下一个坐标x 和y，从左到右，从上到下
        int nx = (x + 1) % 9; // 从左到右
        int ny = (nx == 0) ? y + 1 : y;//nx为0说明满了要跳到下一行
        
        // 如果当前是数字，那我们跳过当前，直接去操作下一个元素
        if (board[y][x] != '.') return fill(board, nx, ny);
        // 处理当前位置，遍历所有可能性dfs
        for (int i = 1; i <= 9; i++) {
            int bx = x / 3; // 通过ij找出对应的盒子
            int by = y / 3;
            int box_key = by * 3 + bx;
            
            if (rows[y][i] == 0 && cols[x][i] == 0 && boxes[box_key][i] == 0) {
                // 如果三个限制数组都没有当先枚举的这个数字，可以尝试添入，dfs，再回溯
                rows[y][i] = 1;
                cols[x][i] = 1;
                boxes[box_key][i] = 1;
                board[y][x] = Character.forDigit(i, 10);  // 变成char
                
                if (fill(board, nx, ny)) return true; // dfs
                
                // 回溯
                board[y][x] = '.';
                rows[y][i] = 0;
                cols[x][i] = 0;
                boxes[box_key][i] = 0;
            }
        }
        return false;
    }
}
// Runtime: 3 ms, faster than 93.58% of Java 







class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }
    
    boolean backtrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            // 穷举到最后一列的话就换到下一行重新开始。
            return backtrack(board, i + 1, 0);
        }
        if (i == m) {
            // 找到一个可行解，触发 base case
            return true;
        }

        if (board[i][j] != '.') {
            // 如果有预设数字，不用我们穷举
            return backtrack(board, i, j + 1);
        } 

        for (char ch = '1'; ch <= '9'; ch++) {
            // 如果遇到不合法的数字，就跳过
            if (!isValid(board, i, j, ch))
                continue;

            board[i][j] = ch;
            // 如果找到一个可行解，立即结束
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        // 穷举完 1~9，依然没有找到可行解，此路不通
        return false;
    }
    
    // 判断 board[i][j] 是否可以填入 n
    boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == n) return false;
            // 判断列是否存在重复
            if (board[i][c] == n) return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }
}
// Runtime: 15 ms, faster than 39.65% of Java

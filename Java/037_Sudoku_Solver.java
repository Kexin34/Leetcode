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



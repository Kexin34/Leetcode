// 解法 ： BFS
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n * n + 1];
        queue.offer(1);
        int steps = 0;
        
        while (!queue.isEmpty()){
            // 本层层序遍历
            int size = queue.size();
            for (int s = 0; s < size; s++){
                int num = queue.poll();
                if (visited[num]) continue;
                visited[num] = true;
                
                if (num == n * n) return steps; // 到达！直接返回结果
                
                // 遍历所有可能，把合法的放入queue下一次遍历
                for (int i = 1; i <= 6 && num + i <= n * n; i++){
                    int next = num + i; // 下一个的数字标号
                    int value = getPosition(board, next);//数字标号转为二维坐标位置
                    
                    //如果是ladder/snake（即value不是-1时),下一个位置数字标号是value值而非num + i
                    if (value != -1) next = value;
                    if (!visited[next]) queue.offer(next);
                }
            }
            steps++;
        }
        return -1;
    }
    
    /* 将数字标号转为二维坐标位置,再提取value的子函数：
     * 首先应将数字标号减1，因为这里是从1开始的，而代码中的二维坐标是从0开始的
     * 然后除以n得到横坐标，对n取余得到纵坐标。
    */
    private int getPosition(int[][] board, int num){
        int n = board.length;
        int oldRow = (num - 1) / n;
        //因为代码中的二维数组起点位置在左上角，行数需要上下翻转，即用 n-1 减去当前行数
        int row = (n - 1) - oldRow;
        
        int oldCol = (num - 1) % n;
        // 当行号是奇数的时候，列数需要翻转一下，即用 n-1 减去当前列数
        int col = oldRow % 2 == 0 ? oldCol : (n - 1) - oldCol;
        return board[row][col];
    }
}
// faster than 85.24% of Java


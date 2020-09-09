// BFS
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0)
                    queue.offer(new int[]{i, j});
                else
                    matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        while (!queue.isEmpty()){
            int[] cell = queue.poll();
            // 遍历四个方向邻居
            for (int[] dir : directions){
                int r = cell[0] + dir[0];
                int c = cell[1] + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n ||
                   matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) continue;
                // 新邻居value（ifinity）比本cell大，说明新邻居是1/有1相邻，
                // 因为本node（很有可能）是0，所以要更新新邻居为本cell value + 1(距离)
                queue.add(new int[] {r, c}); 
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        return matrix;
    }
}
// faster than 71.52% of Java 



// 解法二：DP最优解
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dist = new int[m][n];
        int MAX_TEMP = Integer.MAX_VALUE / 2;   //除以2防止溢出
        
        // 1. DP array初始化
        // 如果 (i, j) 的元素为 0，那么距离为 0，否则设置成一个很大的数 MAX_TEMP
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0)
                    dist[i][j] = 0;
                else 
                    dist[i][j] = MAX_TEMP;
            }
        }
        
        // 2. 水平向右移动 和 竖直向下移动
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i - 1 >= 0)         // 根据upper cell计算
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                if (j - 1 >= 0)         // 根据左边的cell计算
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
            }
        }
            
        // 3. 水平向左移动 和 竖直向上移动
        for (int i = m - 1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j--){
                if (i + 1 < m)          // 根据下面的cell计算
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                if (j + 1 < n)          // 根据右边的cell计算
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
            }
        }
        return dist;
    }
}
// faster than 97.04% of Java
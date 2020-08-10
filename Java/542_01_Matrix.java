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
// faster than 15.68% of Jav

// DP最优解（copy过来的，还没看）
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        int[][] dis = new int[matrix.length][matrix[0].length];
        int range = matrix.length * matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    dis[i][j] = 0;
                } else {
                    int upCell = (i > 0) ? dis[i - 1][j] : range;
                    int leftCell = (j > 0) ? dis[i][j - 1] : range;
                    dis[i][j] = Math.min(upCell, leftCell) + 1;
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    dis[i][j] = 0;
                } else {
                    int downCell = (i < matrix.length - 1) ? dis[i + 1][j] : range;
                    int rightCell = (j < matrix[0].length - 1) ? dis[i][j + 1] : range;
                    dis[i][j] = Math.min(Math.min(downCell, rightCell) + 1, dis[i][j]);
                }
            }
        }

        return dis;
    }
}
// faster than 97.11% of Java 
